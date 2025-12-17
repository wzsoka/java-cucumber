package pages;

import dataProvider.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.components.HeaderTopbar;

import java.time.Duration;

public class StartPage {

    @FindBy(xpath = "//img[@alt='logo-deichmann-px']")
    public WebElement logo;

    @FindBy(id = "cmpbox")
    public WebElement acceptCookiesBox;

    @FindBy(id = "cmpwelcomebtnyes")
    public WebElement okButton;

    public HeaderTopbar headerTopbar;

    protected WebDriver driver;

    public StartPage(WebDriver driver) {
        this.driver = driver;
        ConfigFileReader configFileReader = new ConfigFileReader();
        String HOME_URL = configFileReader.getConfigValue("homeUrl");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe(HOME_URL));
        this.headerTopbar = new HeaderTopbar(this.driver);
        PageFactory.initElements(this.driver, this);
    }
}
