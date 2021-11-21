package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.DriverFactory;
import utils.TestConfig;
import utils.TestContext;

import java.lang.reflect.Method;

public class BaseTest
{
    protected static ThreadLocal<TestContext> testContext = new ThreadLocal<TestContext>();
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    @Parameters({"browser", "remote"})
    @BeforeTest(alwaysRun = true)
    public void initialiseTest(@Optional("default") String browser, @Optional("false") String remoteExec)
    {
        TestConfig config = TestConfig.getInstance()
                                      .setRemoteExec(remoteExec)
                                      .setBrowser(browser)
                                      .setBuildName();

        testContext.set(new TestContext()
                .setTestConfig(config));

        driver.set(DriverFactory.initialiseDriver(getContext()));

        getContext().setFileUploader(driver.get());
    }

    @AfterTest
    public void teardown()
    {
        getDriver().quit();
    }

    protected TestContext getContext()
    {
        return testContext.get();
    }

    protected WebDriver getDriver()
    {
        return driver.get();
    }
}