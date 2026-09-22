package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

public class RegistrationPageTests extends Base {
    private RegistrationPage registrationPage;

    @BeforeMethod
    public void openRegistrationPage() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.openLogin();
        registrationPage = new LoginPage(driver, wait).openRegistration();
    }

    @Test
    public void registerButtonShouldBeDisabledWhenFormIsEmpty() {
        Assert.assertFalse(registrationPage.isRegisterButtonEnabled());
    }

    @Test
    public void shouldShowErrorForInvalidEmail() {
        registrationPage.enterAccountDetails("anjali123", "wrong-email", "Test@123", "Test@123");
        Assert.assertFalse(registrationPage.isRegisterButtonEnabled());
    }

    @Test
    public void shouldNotRegisterWhenPasswordsDoNotMatch() {
        registrationPage.enterAccountDetails("anjali123", "anjali123@example.com", "Test@123", "Test@456");
        Assert.assertFalse(registrationPage.isRegisterButtonEnabled());
    }
}
