package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Utility;

public class ProductListPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Utility utility;

    @FindBy(css = "h3.roboto-regular") private WebElement pageHeading;
    @FindBy(css = "div.categoryRight ul li") private List<WebElement> products;

    public ProductListPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        utility = new Utility(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public int getProductCount() {
        return products.size();
    }

    public String getHeading() {
        return utility.getText(pageHeading);
    }

    public ProductDetailsPage selectFirstProduct() {
        WebElement firstProduct = driver.findElement(By.cssSelector("div.categoryRight ul li:first-child"));
        utility.click(firstProduct);
        return new ProductDetailsPage(driver, wait);
    }
}
