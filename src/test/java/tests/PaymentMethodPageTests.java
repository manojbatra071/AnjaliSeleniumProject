package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseLoginLogout;
import pages.PaymentMethodPage;
import pages.ShippingDetailsPage;

public class PaymentMethodPageTests extends BaseLoginLogout {
    private PaymentMethodPage paymentPage;

    @BeforeMethod(dependsOnMethods = "loginBeforeTest")
    public void openPaymentPage() {
        ShippingDetailsPage shipping = homePage.openCategory("mice")
                .selectFirstProduct()
                .addToCart()
                .checkout();
        shipping.enterShippingAddress("Anjali", "Test", "9876543210",
                "India", "Hyderabad", "1 Main Road", "Telangana", "500001");
        paymentPage = shipping.continueToPayment();
    }

    @Test
    public void payButtonShouldBeDisabledWithoutCardDetails() {
        Assert.assertFalse(paymentPage.isPayNowButtonEnabled());
    }

    @Test
    public void invalidCardShouldNotEnablePayButton() {
        paymentPage.enterMasterCardDetails("1234", "1", "01", "2030", "Anjali Test");
        Assert.assertFalse(paymentPage.isPayNowButtonEnabled());
    }
}
