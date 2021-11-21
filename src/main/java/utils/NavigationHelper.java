package utils;

import org.openqa.selenium.WebDriver;

public class NavigationHelper
{
    private TestContext context;
    private WebDriver driver;

    public NavigationHelper(TestContext context, WebDriver driver)
    {
        this.context = context;
        this.driver = driver;
    }

    // methods which navigate to given flows can live here

    public void goToBaseUrl()
    {
        driver.get(context.getConfig().getBaseUrl());
    }
}
