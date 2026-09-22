package base;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import config.Config;
import pages.HomePage;
import pages.LoginPage;

public class BaseLoginLogout extends Base {
    protected HomePage homePage;

    @BeforeMethod(dependsOnMethods = "openBrowser")
    public void loginBeforeTest() {
        String username = Config.get("username");
        String password = Config.get("password");

        if (username.isBlank() || password.isBlank()) {
            throw new SkipException("Add username and password in config.properties to run login tests");
        }

        homePage = new HomePage(driver, wait);
        homePage.openLogin();
        new LoginPage(driver, wait).login(username, password);
    }

    @AfterMethod(alwaysRun = true)
    public void logoutAfterTest() {
        if (driver != null && homePage != null && homePage.isUserLoggedIn()) {
            homePage.logout();
        }
    }
}
