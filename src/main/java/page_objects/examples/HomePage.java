package page_objects.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestContext;

public class HomePage
{
    private WebDriver driver;
    private TestContext context;
    private By formAuthenticationLink = By.linkText("Form Authentication");
    private By fileUploadLink = By.linkText("File Upload");

    public HomePage(TestContext context, WebDriver driver)
    {
        this.context = context;
        this.driver = driver;
    }

    public LoginPage clickFormAuthenticationLink()
    {
        driver.findElement(formAuthenticationLink).click();
        return new LoginPage(context, driver);
    }

    public FileUploadPage clickFileUploadLink()
    {
        driver.findElement(fileUploadLink).click();
        return new FileUploadPage(context, driver);
    }

    public HomePage go()
    {
        driver.get(context.getConfig().getBaseUrl());
        return this;
    }
}