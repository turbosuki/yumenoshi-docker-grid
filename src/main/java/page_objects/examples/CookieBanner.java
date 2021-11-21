package page_objects.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.SeleniumHelper;
import utils.TestContext;

public class CookieBanner<T>
{
    private final WebDriver driver;
    private final SeleniumHelper helper;
    private final TestContext context;
    private T originatingPage;
    private final By title = By.cssSelector("h4");
    private final By acceptAllCookiesButton = By.id("accept");
    private final String titleText = "Cookies";

    public CookieBanner(WebDriver driver, T originatingPage, TestContext context)
    {
        this.driver = driver;
        this.context = context;
        this.helper = new SeleniumHelper(driver);
        this.originatingPage = originatingPage;
    }

    public T clickAcceptAllCookiesButton()
    {
        helper.waitForPageLoad();
        helper.waitForTextToBe(title, titleText);
        driver.findElement(acceptAllCookiesButton).click();
        helper.waitForTextNotToBe(title, titleText);
        return originatingPage;
    }
}
