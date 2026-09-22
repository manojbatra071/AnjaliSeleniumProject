package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.ProductDetailsPage;

public class ProductDetailsPageTests extends Base {
    private ProductDetailsPage detailsPage;

    @BeforeMethod
    public void openProduct() {
        detailsPage = new HomePage(driver, wait).openCategory("mice").selectFirstProduct();
    }

    @Test
    public void productShouldHaveNameAndPrice() {
        Assert.assertFalse(detailsPage.getProductName().isBlank());
        Assert.assertTrue(detailsPage.getProductPrice().startsWith("$"));
    }

    @Test
    public void shouldAddTwoItemsToCart() {
        detailsPage.chooseColor(0);
        detailsPage.changeQuantity(2);
        Assert.assertEquals(detailsPage.addToCart().getNumberOfProducts(), 1);
    }
}
