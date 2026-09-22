package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseLoginLogout;
import pages.MyAccountPage;

public class MyAccountPageTests extends BaseLoginLogout {
    @Test
    public void shouldOpenMyAccountPage() {
        MyAccountPage accountPage = homePage.openMyAccount();
        Assert.assertTrue(accountPage.getHeading().toUpperCase().contains("ACCOUNT"));
    }

    @Test
    public void accountPageShouldDisplayOptions() {
        MyAccountPage accountPage = homePage.openMyAccount();
        Assert.assertTrue(accountPage.getAccountOptionCount() > 0);
    }
}
