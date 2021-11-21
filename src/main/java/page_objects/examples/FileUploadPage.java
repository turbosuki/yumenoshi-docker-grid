package page_objects.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.SeleniumHelper;
import utils.TestContext;

public class FileUploadPage
{
    private TestContext context;
    private WebDriver driver;
    private SeleniumHelper seleniumHelper;
    private By uploadButton = By.id("file-upload");
    private By submitButton = By.id("file-submit");
    private By uploadedFilesText = By.id("uploaded-files");
    private By title = By.cssSelector("h3");

    public FileUploadPage(TestContext context, WebDriver driver)
    {
        this.context = context;
        this.driver = driver;
        this.seleniumHelper = new SeleniumHelper(driver);
    }

    public FileUploadPage uploadFile(String filepath)
    {
        context.getFileUploader().uploadFile(uploadButton, filepath);
        return this;
    }

    public FileUploadPage clickSubmitButton()
    {
        driver.findElement(submitButton).click();
        return this;
    }

    public String getPageTitle()
    {
        return driver.findElement(title).getText();
    }
}