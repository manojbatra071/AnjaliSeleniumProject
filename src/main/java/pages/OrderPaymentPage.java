package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Utility;

public class OrderPaymentPage {
    private final Utility utility;

    @FindBy(css = "h2.roboto-regular") private WebElement message;
    @FindBy(id = "orderNumberLabel") private WebElement orderNumber;
    @FindBy(linkText = "CONTINUE SHOPPING") private WebElement continueShopping;

    public OrderPaymentPage(WebDriver driver, WebDriverWait wait) {
        utility = new Utility(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public String getConfirmationMessage() {
        return utility.getText(message);
    }

    public String getOrderNumber() {
        return utility.getText(orderNumber);
    }

    public void continueShopping() {
        utility.click(continueShopping);
    }
}
