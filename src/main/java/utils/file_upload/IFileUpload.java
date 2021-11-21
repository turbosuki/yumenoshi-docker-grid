package utils.file_upload;

import org.openqa.selenium.By;

public interface IFileUpload
{
    void uploadFile(By locator, String filePath);
}
