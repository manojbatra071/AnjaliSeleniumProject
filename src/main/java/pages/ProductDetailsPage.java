package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Utility;

public class ProductDetailsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Utility utility;

    @FindBy(css = "#Description h1") private WebElement productName;
    @FindBy(css = "#Description h2") private WebElement productPrice;
    @FindBy(name = "quantity") private WebElement quantity;
    @FindBy(name = "save_to_cart") private WebElement addToCartButton;
    @FindBy(css = "div.colors span") private List<WebElement> colors;

    public ProductDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        utility = new Utility(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        return utility.getText(productName);
    }

    public String getProductPrice() {
        return utility.getText(productPrice);
    }

    public void chooseColor(int colorNumber) {
        if (!colors.isEmpty() && colorNumber < colors.size()) {
            utility.click(colors.get(colorNumber));
        }
    }

    public void changeQuantity(int number) {
        utility.type(quantity, String.valueOf(number));
    }

    public AddToCartPage addToCart() {
        utility.click(addToCartButton);
        utility.click(driver.findElement(By.id("shoppingCartLink")));
        return new AddToCartPage(driver, wait);
    }
}
