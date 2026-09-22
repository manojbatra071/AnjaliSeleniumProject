package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Utility;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Utility utility;

    @FindBy(id = "menuUserLink") private WebElement userMenu;
    @FindBy(id = "menuSearch") private WebElement searchIcon;
    @FindBy(id = "autoComplete") private WebElement searchBox;
    @FindBy(id = "shoppingCartLink") private WebElement cartLink;
    @FindBy(id = "speakersImg") private WebElement speakers;
    @FindBy(id = "tabletsImg") private WebElement tablets;
    @FindBy(id = "laptopsImg") private WebElement laptops;
    @FindBy(id = "miceImg") private WebElement mice;
    @FindBy(id = "headphonesImg") private WebElement headphones;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        utility = new Utility(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public void openLogin() {
        utility.click(userMenu);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
    }

    public ProductListPage searchForProduct(String productName) {
        utility.click(searchIcon);
        utility.type(searchBox, productName);
        utility.click(driver.findElement(By.cssSelector("div.autoCompleteCover div:last-child")));
        return new ProductListPage(driver, wait);
    }

    public ProductListPage openCategory(String category) {
        WebElement selectedCategory;
        switch (category.toLowerCase()) {
            case "speakers": selectedCategory = speakers; break;
            case "tablets": selectedCategory = tablets; break;
            case "laptops": selectedCategory = laptops; break;
            case "headphones": selectedCategory = headphones; break;
            default: selectedCategory = mice;
        }
        utility.click(selectedCategory);
        return new ProductListPage(driver, wait);
    }

    public AddToCartPage openCart() {
        utility.click(cartLink);
        return new AddToCartPage(driver, wait);
    }

    public MyAccountPage openMyAccount() {
        utility.click(userMenu);
        utility.click(driver.findElement(By.xpath("//label[normalize-space()='My account']")));
        return new MyAccountPage(driver, wait);
    }

    public boolean isUserLoggedIn() {
        return !userMenu.getText().trim().isEmpty();
    }

    public String getLoggedInUsername() {
        return userMenu.getText().trim();
    }

    public void logout() {
        utility.click(userMenu);
        utility.click(driver.findElement(By.xpath("//label[normalize-space()='Sign out']")));
    }
}
