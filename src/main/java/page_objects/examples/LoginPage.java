package page_objects.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestContext;

public class LoginPage
{
    private TestContext context;
    private WebDriver driver;
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("#login button");

    public LoginPage(TestContext context, WebDriver driver)
    {
        this.context = context;
        this.driver = driver;
    }

    public LoginPage setUsername(String username)
    {
        driver.findElement(usernameField).sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password)
    {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public SecureAreaPage clickLoginButton()
    {
        driver.findElement(loginButton).click();
        return new SecureAreaPage(context, driver);
    }
}