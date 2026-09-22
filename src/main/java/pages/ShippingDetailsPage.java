package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Utility;

public class ShippingDetailsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Utility utility;

    @FindBy(name = "first_name") private WebElement firstName;
    @FindBy(name = "last_name") private WebElement lastName;
    @FindBy(name = "phone_number") private WebElement phone;
    @FindBy(name = "countryListbox") private WebElement country;
    @FindBy(name = "city") private WebElement city;
    @FindBy(name = "address") private WebElement address;
    @FindBy(name = "state_/_province_/_region") private WebElement state;
    @FindBy(name = "postal_code") private WebElement postalCode;
    @FindBy(id = "next_btn") private WebElement nextButton;

    public ShippingDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        utility = new Utility(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public void enterShippingAddress(String first, String last, String phoneNumber,
            String countryName, String cityName, String street, String stateName, String pin) {
        utility.type(firstName, first);
        utility.type(lastName, last);
        utility.type(phone, phoneNumber);
        new Select(country).selectByVisibleText(countryName);
        utility.type(city, cityName);
        utility.type(address, street);
        utility.type(state, stateName);
        utility.type(postalCode, pin);
    }

    public PaymentMethodPage continueToPayment() {
        utility.click(nextButton);
        return new PaymentMethodPage(driver, wait);
    }

    public boolean isNextButtonEnabled() {
        return nextButton.isEnabled();
    }
}
