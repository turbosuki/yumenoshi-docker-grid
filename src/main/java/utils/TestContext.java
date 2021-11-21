package utils;

import org.openqa.selenium.WebDriver;
import utils.file_upload.FileUpload;
import utils.file_upload.IFileUpload;

public class TestContext
{
    private TestConfig config;
    private IFileUpload fileUploader;
    private String testMethodName;

    public TestContext setTestConfig(TestConfig config)
    {
        this.config = config;
        return this;
    }

    public TestContext setTestMethodName(String testMethodName)
    {
        this.testMethodName = testMethodName;
        return this;
    }

    public TestContext setFileUploader(WebDriver driver)
    {
        fileUploader = FileUpload.New(driver, config);
        return this;
    }

    public TestConfig getConfig()
    {
        return config;
    }

    public IFileUpload getFileUploader()
    {
        return fileUploader;
    }
}