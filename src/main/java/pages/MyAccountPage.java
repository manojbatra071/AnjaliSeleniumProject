package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Utility;

public class MyAccountPage {
    private final Utility utility;

    @FindBy(css = "h3.roboto-regular") private WebElement heading;
    @FindBy(css = "div.myAccount div.option") private List<WebElement> accountOptions;

    public MyAccountPage(WebDriver driver, WebDriverWait wait) {
        utility = new Utility(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public String getHeading() {
        return utility.getText(heading);
    }

    public int getAccountOptionCount() {
        return accountOptions.size();
    }
}
