package utils.file_upload;

import java.util.Objects;

public class GetFilePath
{
    public String forTestImage()
    {
        return Objects.requireNonNull(getClass().getClassLoader()
                .getResource("files/upload.jpg")).getPath();
    }
}
