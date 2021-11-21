package example_test;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_objects.examples.HomePage;
import page_objects.examples.SecureAreaPage;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest
{
    @Test
    public void testSuccessfulLogin()
    {
        HomePage homePage = new HomePage(getContext(), getDriver());
        SecureAreaPage secureAreaPage = new SecureAreaPage(getContext(), getDriver());

        homePage.go()
                .clickFormAuthenticationLink()
                .setUsername("tomsmith")
                .setPassword("SuperSecretPassword!")
                .clickLoginButton();

        assertTrue(secureAreaPage.getAlertText().contains("You logged into a secure area!"),
                "Alert text was: " + secureAreaPage.getAlertText());
    }
    @Test
    public void testSuccessfulLogin1()
    {
        HomePage homePage = new HomePage(getContext(), getDriver());
        SecureAreaPage secureAreaPage = new SecureAreaPage(getContext(), getDriver());

        homePage.go()
                .clickFormAuthenticationLink()
                .setUsername("tomsmith")
                .setPassword("SuperSecretPassword!")
                .clickLoginButton();

        assertTrue(secureAreaPage.getAlertText().contains("You logged into a secure area!"),
                "Alert text was: " + secureAreaPage.getAlertText());
    }
    @Test
    public void testSuccessfulLogin2()
    {
        HomePage homePage = new HomePage(getContext(), getDriver());
        SecureAreaPage secureAreaPage = new SecureAreaPage(getContext(), getDriver());

        homePage.go()
                .clickFormAuthenticationLink()
                .setUsername("tomsmith")
                .setPassword("SuperSecretPassword!")
                .clickLoginButton();

        assertTrue(secureAreaPage.getAlertText().contains("You logged into a secure area!"),
                "Alert text was: " + secureAreaPage.getAlertText());
    }
    @Test
    public void testSuccessfulLogin3()
    {
        HomePage homePage = new HomePage(getContext(), getDriver());
        SecureAreaPage secureAreaPage = new SecureAreaPage(getContext(), getDriver());

        homePage.go()
                .clickFormAuthenticationLink()
                .setUsername("tomsmith")
                .setPassword("SuperSecretPassword!")
                .clickLoginButton();

        assertTrue(secureAreaPage.getAlertText().contains("You logged into a secure area!"),
                "Alert text was: " + secureAreaPage.getAlertText());
    }
}