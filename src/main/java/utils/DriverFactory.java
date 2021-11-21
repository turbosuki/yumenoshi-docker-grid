package utils;

import enums.RemoteBrowsers;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory
{
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static WebDriver initialiseDriver(TestContext context)
    {
        if (context.getConfig().getRemoteExec().equals("true"))
        {
            DesiredCapabilities caps = RemoteBrowsers.valueOf(context.getConfig().getBrowser().toUpperCase()).getDesiredCapabilities();
            caps.setCapability("build", context.getConfig().getBuildName());
            try
            {
                driver.set(DriverFactory.createRemoteDriver(context.getConfig(), caps));
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            driver.set(DriverFactory.createLocalDriver(context.getConfig()));
        }

        return driver.get();
    }

    private static WebDriver createRemoteDriver(TestConfig config, DesiredCapabilities caps) throws MalformedURLException
    {
        switch (config.getBrowser())
        {
            case "chrome":
            case "firefox":
                driver.set(new RemoteWebDriver(new URL(config.getSeleniumHubUrl()), caps));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + config.getBrowser());
        }

        driver.get().manage().timeouts().implicitlyWait(Integer.parseInt(config.getImplicitWait()),
                TimeUnit.SECONDS);

        driver.get().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        return driver.get();
    }

    private static WebDriver createLocalDriver(TestConfig config)
    {
        String browser;

        if (config.getBrowser().equals("default"))
        {
            browser = config.getDefaultLocalBrowser();
        }
        else
        {
            browser = config.getBrowser();
        }

        switch (browser)
        {
            case "chrome":
                driver.set(setUpChrome());
                break;
            case "firefox":
                driver.set(setUpFirefox());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }

        driver.get().manage().timeouts().implicitlyWait(Integer.parseInt(config.getImplicitWait()),
                TimeUnit.SECONDS);

        driver.get().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        return driver.get();
    }

    private static WebDriver setUpChrome()
    {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver setUpFirefox()
    {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    public static WebDriver getDriver()
    {
        return driver.get();
    }
}
