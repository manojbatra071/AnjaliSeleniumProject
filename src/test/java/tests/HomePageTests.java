package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.ProductListPage;

public class HomePageTests extends Base {
    @Test
    public void shouldOpenLaptopsCategory() {
        ProductListPage productList = new HomePage(driver, wait).openCategory("laptops");
        Assert.assertTrue(productList.getHeading().toUpperCase().contains("LAPTOP"));
    }

    @Test
    public void shouldOpenMiceCategory() {
        ProductListPage productList = new HomePage(driver, wait).openCategory("mice");
        Assert.assertTrue(productList.getProductCount() > 0);
    }

    @Test
    public void cartShouldBeEmptyForNewBrowserSession() {
        Assert.assertEquals(new HomePage(driver, wait).openCart().getNumberOfProducts(), 0);
    }
}
