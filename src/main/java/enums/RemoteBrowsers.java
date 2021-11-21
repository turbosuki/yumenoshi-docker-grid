package enums;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public enum RemoteBrowsers
{
    FIREFOX(getFirefoxCaps()),
    CHROME(getChromeCaps());

    private DesiredCapabilities caps;

    RemoteBrowsers(DesiredCapabilities caps)
    {
        this.caps = caps;
    }

    public DesiredCapabilities getDesiredCapabilities()
    {
        return this.caps;
    }

    private static DesiredCapabilities getChromeCaps()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "86.0");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return capabilities;
    }

    private static DesiredCapabilities getFirefoxCaps()
    {
        return DesiredCapabilities.firefox();
    }
}
