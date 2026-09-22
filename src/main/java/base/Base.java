package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import config.Config;
import utility.Utility;

public class Base {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Utility utility;

    @BeforeMethod
    public void openBrowser() {
        String browser = Config.get("browser").toLowerCase();

        if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            driver = new EdgeDriver();
        } else {
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.getInt("implicitWait")));
        wait = new WebDriverWait(driver, Duration.ofSeconds(Config.getInt("explicitWait")));
        utility = new Utility(driver, wait);
        driver.get(Config.get("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(ITestResult result) {
        if (driver != null) {
            if (ITestResult.FAILURE == result.getStatus()) {
                System.out.println(utility.takeScreenshot(result.getName()));
            }
            driver.quit();
        }
    }
}
