package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import pages.AddToCartPage;
import pages.HomePage;

public class AddToCartPageTests extends Base {
    @Test
    public void shouldDisplayAddedProductInCart() {
        AddToCartPage cart = new HomePage(driver, wait)
                .openCategory("mice")
                .selectFirstProduct()
                .addToCart();

        Assert.assertEquals(cart.getNumberOfProducts(), 1);
        Assert.assertFalse(cart.getFirstProductName().isBlank());
    }

    @Test
    public void shouldRemoveProductFromCart() {
        AddToCartPage cart = new HomePage(driver, wait)
                .openCategory("headphones")
                .selectFirstProduct()
                .addToCart();

        cart.removeFirstProduct();
        Assert.assertEquals(cart.getNumberOfProducts(), 0);
    }
}
