package utils.file_upload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;

public class DesktopFileUpload implements IFileUpload
{
    private WebDriver driver;
    private String remoteExec;

    public DesktopFileUpload(String remoteExec, WebDriver driver)
    {
        this.remoteExec = remoteExec;
        this.driver = driver;
    }

    @Override
    public void uploadFile(By locator, String filePath)
    {
        if (remoteExec.equals("true"))
        {
            WebElement addFile = driver.findElement(locator);
            ((RemoteWebElement)addFile).setFileDetector(new LocalFileDetector());
            addFile.sendKeys(filePath);
        }
        else
        {
            driver.findElement(locator).sendKeys(filePath);
        }
    }
}
