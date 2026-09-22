package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseLoginLogout;
import pages.ShippingDetailsPage;

public class ShippingDetailsPageTests extends BaseLoginLogout {
    private ShippingDetailsPage shippingPage;

    @BeforeMethod(dependsOnMethods = "loginBeforeTest")
    public void openShippingPage() {
        shippingPage = homePage.openCategory("mice")
                .selectFirstProduct()
                .addToCart()
                .checkout();
    }

    @Test
    public void nextButtonShouldBeDisabledForEmptyAddress() {
        Assert.assertFalse(shippingPage.isNextButtonEnabled());
    }

    @Test
    public void shouldAcceptCompleteShippingAddress() {
        shippingPage.enterShippingAddress("Anjali", "Test", "9876543210",
                "India", "Hyderabad", "1 Main Road", "Telangana", "500001");
        Assert.assertTrue(shippingPage.isNextButtonEnabled());
    }
}
