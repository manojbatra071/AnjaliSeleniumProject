package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Utility;

public class AddToCartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Utility utility;

    @FindBy(css = "table tbody tr") private List<WebElement> cartRows;
    @FindBy(css = "table tbody tr td:nth-child(2) h3") private List<WebElement> productNames;
    @FindBy(css = "a.remove") private List<WebElement> removeLinks;
    @FindBy(id = "checkOutButton") private WebElement checkoutButton;
    @FindBy(css = "span.totalValue") private WebElement total;

    public AddToCartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        utility = new Utility(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public int getNumberOfProducts() {
        return cartRows.size();
    }

    public String getFirstProductName() {
        return productNames.isEmpty() ? "" : productNames.get(0).getText().trim();
    }

    public String getTotal() {
        return utility.getText(total);
    }

    public void removeFirstProduct() {
        if (!removeLinks.isEmpty()) {
            utility.click(removeLinks.get(0));
        }
    }

    public ShippingDetailsPage checkout() {
        utility.click(checkoutButton);
        return new ShippingDetailsPage(driver, wait);
    }
}
