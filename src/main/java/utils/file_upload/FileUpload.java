package utils.file_upload;

import org.openqa.selenium.WebDriver;
import utils.TestConfig;

public class FileUpload
{
    public static IFileUpload New(WebDriver driver, TestConfig config)
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
            case "firefox":
            case "chrome":
                return new DesktopFileUpload(config.getRemoteExec(), driver);
            default:
                throw new IllegalStateException("Unexpected value: " + config.getBrowser());
        }
    }
}
