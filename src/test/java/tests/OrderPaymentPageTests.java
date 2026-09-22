package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseLoginLogout;
import pages.OrderPaymentPage;
import pages.PaymentMethodPage;
import pages.ShippingDetailsPage;

public class OrderPaymentPageTests extends BaseLoginLogout {
    @Test(groups = "order")
    public void shouldDisplayOrderConfirmationAfterPayment() {
        ShippingDetailsPage shipping = homePage.openCategory("mice")
                .selectFirstProduct()
                .addToCart()
                .checkout();

        shipping.enterShippingAddress("Anjali", "Test", "9876543210",
                "India", "Hyderabad", "1 Main Road", "Telangana", "500001");
        PaymentMethodPage payment = shipping.continueToPayment();
        payment.enterMasterCardDetails("5555555555554444", "123", "01", "2030", "Anjali Test");
        OrderPaymentPage order = payment.payNow();

        Assert.assertTrue(order.getConfirmationMessage().toUpperCase().contains("THANK YOU"));
        Assert.assertFalse(order.getOrderNumber().isBlank());
    }
}
