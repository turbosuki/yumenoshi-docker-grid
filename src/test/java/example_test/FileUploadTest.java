package example_test;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_objects.examples.FileUploadPage;
import page_objects.examples.HomePage;
import utils.file_upload.GetFilePath;

import static org.testng.AssertJUnit.assertEquals;

public class FileUploadTest extends BaseTest
{
    private HomePage homepage;
    private FileUploadPage fileUploadPage;
    private GetFilePath getFilePath = new GetFilePath();

    @Test
    public void userAccessesFileUploadPageAndUploadsFile()
    {
        homepage = new HomePage(getContext(), getDriver());
        fileUploadPage = new FileUploadPage(getContext(), getDriver());

        homepage.go()
                .clickFileUploadLink()
                .uploadFile(getFilePath.forTestImage())
                .clickSubmitButton();

        assertEquals("File Uploaded!", fileUploadPage.getPageTitle());
    }
}
