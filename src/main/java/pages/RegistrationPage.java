package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Utility;

public class RegistrationPage {
    private final Utility utility;

    @FindBy(name = "usernameRegisterPage") private WebElement username;
    @FindBy(name = "emailRegisterPage") private WebElement email;
    @FindBy(name = "passwordRegisterPage") private WebElement password;
    @FindBy(name = "confirm_passwordRegisterPage") private WebElement confirmPassword;
    @FindBy(name = "first_nameRegisterPage") private WebElement firstName;
    @FindBy(name = "last_nameRegisterPage") private WebElement lastName;
    @FindBy(name = "phone_numberRegisterPage") private WebElement phone;
    @FindBy(name = "countryListboxRegisterPage") private WebElement country;
    @FindBy(name = "cityRegisterPage") private WebElement city;
    @FindBy(name = "addressRegisterPage") private WebElement address;
    @FindBy(name = "state_/_province_/_regionRegisterPage") private WebElement state;
    @FindBy(name = "postal_codeRegisterPage") private WebElement postalCode;
    @FindBy(name = "i_agree") private WebElement agree;
    @FindBy(id = "register_btn") private WebElement registerButton;
    @FindBy(css = "label.invalid") private WebElement validationMessage;

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        utility = new Utility(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public void enterAccountDetails(String user, String mail, String pass, String confirmPass) {
        utility.type(username, user);
        utility.type(email, mail);
        utility.type(password, pass);
        utility.type(confirmPassword, confirmPass);
    }

    public void enterPersonalDetails(String first, String last, String phoneNumber) {
        utility.type(firstName, first);
        utility.type(lastName, last);
        utility.type(phone, phoneNumber);
    }

    public void enterAddress(String countryName, String cityName, String street,
            String stateName, String pin) {
        new Select(country).selectByVisibleText(countryName);
        utility.type(city, cityName);
        utility.type(address, street);
        utility.type(state, stateName);
        utility.type(postalCode, pin);
    }

    public void acceptTermsAndRegister() {
        utility.click(agree);
        utility.click(registerButton);
    }

    public boolean isRegisterButtonEnabled() {
        return registerButton.isEnabled();
    }

    public String getValidationMessage() {
        return utility.getText(validationMessage);
    }
}
