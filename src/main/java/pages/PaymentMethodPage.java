package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Utility;

public class PaymentMethodPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Utility utility;

    @FindBy(name = "masterCredit") private WebElement masterCredit;
    @FindBy(id = "creditCard") private WebElement cardNumber;
    @FindBy(name = "cvv_number") private WebElement cvv;
    @FindBy(name = "mmListbox") private WebElement month;
    @FindBy(name = "yyyyListbox") private WebElement year;
    @FindBy(name = "cardholder_name") private WebElement cardholderName;
    @FindBy(id = "pay_now_btn_ManualPayment") private WebElement payNowButton;

    public PaymentMethodPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        utility = new Utility(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public void enterMasterCardDetails(String number, String securityCode,
            String expiryMonth, String expiryYear, String name) {
        utility.click(masterCredit);
        utility.type(cardNumber, number);
        utility.type(cvv, securityCode);
        new Select(month).selectByVisibleText(expiryMonth);
        new Select(year).selectByVisibleText(expiryYear);
        utility.type(cardholderName, name);
    }

    public boolean isPayNowButtonEnabled() {
        return payNowButton.isEnabled();
    }

    public OrderPaymentPage payNow() {
        utility.click(payNowButton);
        return new OrderPaymentPage(driver, wait);
    }
}
