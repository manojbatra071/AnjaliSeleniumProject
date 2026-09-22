package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.ProductListPage;

public class ProductListPageTests extends Base {
    @Test
    public void categoryShouldDisplayProducts() {
        ProductListPage products = new HomePage(driver, wait).openCategory("tablets");
        Assert.assertTrue(products.getProductCount() > 0);
    }

    @Test
    public void shouldOpenFirstProductFromList() {
        ProductListPage products = new HomePage(driver, wait).openCategory("speakers");
        ProductDetailsPage details = products.selectFirstProduct();
        Assert.assertFalse(details.getProductName().isBlank());
        Assert.assertTrue(details.getProductPrice().contains("$"));
    }
}
