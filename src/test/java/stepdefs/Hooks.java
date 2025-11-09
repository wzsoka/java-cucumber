package stepdefs;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;

import java.time.Duration;

public class Hooks {

    public static WebDriver driver = BrowserFactory.createWebDriver();

    @BeforeAll
    public static void before_all() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void after_all() {
        if (driver != null) {
            driver.quit();
        }
    }
}
