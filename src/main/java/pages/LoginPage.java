package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Utility;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Utility utility;

    @FindBy(name = "username") private WebElement username;
    @FindBy(name = "password") private WebElement password;
    @FindBy(id = "sign_in_btn") private WebElement signInButton;
    @FindBy(id = "signInResultMessage") private WebElement errorMessage;
    @FindBy(linkText = "CREATE NEW ACCOUNT") private WebElement createAccount;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        utility = new Utility(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public void login(String user, String pass) {
        utility.type(username, user);
        utility.type(password, pass);
        utility.click(signInButton);
    }

    public RegistrationPage openRegistration() {
        utility.click(createAccount);
        return new RegistrationPage(driver, wait);
    }

    public String getErrorMessage() {
        return utility.getText(errorMessage);
    }

    public boolean isSignInButtonEnabled() {
        return signInButton.isEnabled();
    }
}
