package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import config.Config;
import pages.HomePage;
import pages.LoginPage;

public class LoginPageTests extends Base {
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void openLoginWindow() {
        homePage = new HomePage(driver, wait);
        homePage.openLogin();
        loginPage = new LoginPage(driver, wait);
    }

    @Test
    public void signInButtonShouldBeDisabledWhenFieldsAreEmpty() {
        Assert.assertFalse(loginPage.isSignInButtonEnabled());
    }

    @Test
    public void shouldRejectInvalidLogin() {
        loginPage.login("wrongUser123", "Wrong@123");
        Assert.assertTrue(loginPage.getErrorMessage().toLowerCase().contains("incorrect"));
    }

    @Test
    public void shouldLoginWithValidCredentials() {
        if (Config.get("username").isBlank()) {
            throw new org.testng.SkipException("Add credentials in config.properties");
        }
        loginPage.login(Config.get("username"), Config.get("password"));
        Assert.assertTrue(homePage.isUserLoggedIn());
    }
}
