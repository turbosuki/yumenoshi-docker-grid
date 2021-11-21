package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SeleniumHelper
{
    private WebDriver driver;
    private final int WAIT_TIMEOUT = 30;
    private WebDriverWait wait;

    public SeleniumHelper(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, WAIT_TIMEOUT);
    }

    public void waitForElementToBeInvisible(By locator)
    {
        if (!driver.findElements(locator).isEmpty())
        {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        }
    }

    public void waitForElementToBePresent(By locator)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(By locator)
    {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForPageLoad()
    {
        wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public void waitForTextNotToBe(By locator, String text)
    {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
    }

    public void waitForTextToBe(By locator, String text)
    {
        wait.until(ExpectedConditions.textToBe(locator, text));
    }

    public boolean isElementPresent(By locator)
    {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        boolean isPresent = !driver.findElements(locator).isEmpty();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        return isPresent;
    }
}
