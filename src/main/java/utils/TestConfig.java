package utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestConfig
{
    private String implicitWait;
    private String baseUrl;
    private String seleniumHubUrl;
    private Config config;
    private String defaultLocalBrowser;
    private String remoteExec;
    private String browser;
    private static TestConfig testConfig;
    private String buildName;

    public TestConfig setBuildName()
    {
        if (buildName == null)
        {
            Date date = new Date();
            DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy-HHmmss");
            this.buildName = "test_run-" + dateFormatter.format(date);
        }
        return this;
    }

    public String getBuildName()
    {
        return buildName;
    }

    private TestConfig()
    {

    }

    public static TestConfig getInstance()
    {
        if (testConfig == null)
        {
            testConfig = new TestConfig().initialiseTestConfigFromPropertiesFile();
        }

        return testConfig;
    }

    public TestConfig initialiseTestConfigFromPropertiesFile()
    {
        config = ConfigFactory.load();
        setBaseUrl();
        setSeleniumHubUrl();
        setImplicitWait();
        setDefaultLocalBrowser();
        return this;
    }

    private TestConfig setBaseUrl()
    {
        baseUrl = config.getString("baseUrl");
        return this;
    }

    private TestConfig setDefaultLocalBrowser()
    {
        if (config.hasPath("defaultLocalBrowser"))
        {
            defaultLocalBrowser = config.getString("defaultLocalBrowser");
        }
        return this;
    }

    private TestConfig setSeleniumHubUrl()
    {
        seleniumHubUrl = config.getString("seleniumHubUrl");
        return this;
    }

    private TestConfig setImplicitWait()
    {
        implicitWait = config.getString("implicitWait");
        return this;
    }

    public TestConfig setRemoteExec(String remote)
    {
        remoteExec = remote;
        return this;
    }

    public TestConfig setDefaultLocalBrowser(String browser)
    {
        defaultLocalBrowser = browser;
        return this;
    }

    public TestConfig setBrowser(String browser)
    {
        this.browser = browser;
        return this;
    }

    public String getBaseUrl()
    {
        return baseUrl;
    }

    public String getSeleniumHubUrl()
    {
        return seleniumHubUrl;
    }

    public String getImplicitWait()
    {
        return implicitWait;
    }

    public String getDefaultLocalBrowser()
    {
        return defaultLocalBrowser;
    }

    public String getBrowser()
    {
        return browser;
    }

    public String getRemoteExec()
    {
        return remoteExec;
    }
}
