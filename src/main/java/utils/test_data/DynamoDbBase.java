/*
package utils.test_data;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;


public class DynamoDbBase
{
    private String tableName;
    private AmazonDynamoDB client;
    private DynamoDB dynamoDB;
    protected Table table;

    protected void initialiseWithTable(String table)
    {
        setTable(table);
        setUpClient();
    }

    private void setUpClient()
    {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.EU_WEST_2)
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        table = dynamoDB.getTable(tableName);
    }

    private void setTable(String tableName)
    {
        this.tableName = tableName;
    }

    public void createItemFromTestDataObject(BaseTestData data)
    {
        try
        {
            PutItemOutcome outcome = table.putItem(Item.fromJSON(data.asJsonString()));
        }
        catch (Exception e)
        {
            System.err.println("Unable to add item");
            System.err.println(e.getMessage());
        }
    }
}
*/
