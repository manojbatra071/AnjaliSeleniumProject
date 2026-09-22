package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseLoginLogout;
import pages.AddToCartPage;
import pages.OrderPaymentPage;
import pages.PaymentMethodPage;
import pages.ProductDetailsPage;
import pages.ShippingDetailsPage;

public class EndToEndTests extends BaseLoginLogout {
    @Test(groups = "order")
    public void loginSearchAddToCartAndPlaceOrder() {
        ProductDetailsPage product = homePage.searchForProduct("HP USB 3 Button Optical Mouse")
                .selectFirstProduct();
        product.chooseColor(0);
        product.changeQuantity(1);

        AddToCartPage cart = product.addToCart();
        Assert.assertEquals(cart.getNumberOfProducts(), 1);

        ShippingDetailsPage shipping = cart.checkout();
        shipping.enterShippingAddress("Anjali", "Test", "9876543210",
                "India", "Hyderabad", "1 Main Road", "Telangana", "500001");

        PaymentMethodPage payment = shipping.continueToPayment();
        payment.enterMasterCardDetails("5555555555554444", "123", "01", "2030", "Anjali Test");

        OrderPaymentPage order = payment.payNow();
        Assert.assertTrue(order.getConfirmationMessage().toUpperCase().contains("THANK YOU"));
    }
}
