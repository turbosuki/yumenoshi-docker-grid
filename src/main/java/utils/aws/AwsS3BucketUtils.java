package utils.aws;

import lombok.Cleanup;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.util.StreamUtils;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AwsS3BucketUtils
{
    private static final String TEST_BUCKET_NAME = "test-bucket-name";

    public static String sampleGetObjectFromBucketUsingPath(String objectVersion) throws TimeoutException, IOException
    {
        String filePath = "built up file path";
        GetObjectRequest request = buildGetObjectRequestAndWaitForObjectToExist(Region.EU_WEST_2, TEST_BUCKET_NAME, filePath);

        @Cleanup S3Client client = openClientWithRegion(Region.EU_WEST_2);
        @Cleanup ResponseInputStream<GetObjectResponse> bucketResponseIs = null;
        bucketResponseIs = client.getObject(request);
        GetObjectResponse bucketResponse = bucketResponseIs.response();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        while (bucketResponse.versionId().equals(objectVersion))
        {
            if (stopWatch.getTime(TimeUnit.MILLISECONDS) > 3000)
            {
                throw new TimeoutException();
            }
            bucketResponseIs = client.getObject(request);
            bucketResponse = bucketResponseIs.response();
        }
        stopWatch.stop();

        return readContentFromBucketObjectResponseAsString(request, Region.EU_WEST_2);
    }

    private static GetObjectRequest buildGetObjectRequestAndWaitForObjectToExist(Region region, String bucketName, String filePath)
    {
        @Cleanup S3Client client = openClientWithRegion(region);
        @Cleanup S3Waiter waiter = client.waiter();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(filePath)
                .build();

        HeadObjectRequest bucketRequestWait = HeadObjectRequest.builder()
                .key(filePath)
                .bucket(bucketName)
                .build();

        WaiterResponse<HeadObjectResponse> waiterResponse = waiter.waitUntilObjectExists(bucketRequestWait);
        waiterResponse.matched().response().ifPresent(System.out::println);

        return getObjectRequest;
    }

    private static void deleteAllObjectsFromBucketFolder(String bucketName, String folderPath, Region region)
    {
        @Cleanup S3Client client = openClientWithRegion(region);

        ListObjectsRequest listObjects = ListObjectsRequest.builder()
                .bucket(bucketName)
                .prefix(folderPath)
                .build();

        ListObjectsResponse res = client.listObjects(listObjects);
        List<S3Object> objects = res.contents();

        for (S3Object object : objects)
        {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(object.key())
                    .build();

            client.deleteObject(deleteObjectRequest);
        }
    }

    private static String readContentFromBucketObjectResponseAsString(GetObjectRequest request, Region region) throws IOException
    {
        @Cleanup S3Client client = openClientWithRegion(region);
        @Cleanup ResponseInputStream<GetObjectResponse> object = null;
        object = client.getObject(request);
        String result = null;

        try
        {
            result = StreamUtils.copyToString(object, StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    private static S3Client openClientWithRegion(Region region)
    {
        return S3Client.builder()
                .region(region)
                .build();
    }
}