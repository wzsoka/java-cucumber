package pages.components;

import dataProvider.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class HeaderTopbar {

    @FindBy(tagName = "deich-lib-shortcuts")
    public WebElement headerTopbar;

    @FindBy(xpath = "//button[@aria-label='login']")
    public WebElement accountButton;

    @FindBy(xpath = "//button[@aria-label='Login']")
    public WebElement loginButton;

    @FindBy(xpath = "//button[@aria-label='register']")
    public WebElement registerButton;

    @FindBy(xpath = "//a[@data-key='core.component.header.userInfo.logoutHint']")
    public WebElement logoutLink;

    protected WebDriver driver;

    public HeaderTopbar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(headerTopbar));
    }

    public void clickLoginMenu(){
        new Actions(driver).moveToElement(accountButton).moveToElement(loginButton).click().perform();
        ConfigFileReader configFileReader = new ConfigFileReader();
        String loginUrl = configFileReader.getConfigValue("loginUrl");
        if (!Objects.equals(driver.getCurrentUrl(), loginUrl)){
            driver.navigate().to(loginUrl);
        }
    }

    public void clickRegisterMenu(){
        new Actions(driver).moveToElement(accountButton).moveToElement(registerButton).click().build().perform();
        ConfigFileReader configFileReader = new ConfigFileReader();
        String registerUrl = configFileReader.getConfigValue("registerUrl");
        if (!Objects.equals(driver.getCurrentUrl(), registerUrl)){
            driver.navigate().to(registerUrl);
        }
    }

    public void logout(){
        new Actions(driver).moveToElement(accountButton).moveToElement(logoutLink).click().build().perform();
        ConfigFileReader configFileReader = new ConfigFileReader();
        String logoutUrl = configFileReader.getConfigValue("logoutUrl");
        if (!Objects.equals(driver.getCurrentUrl(), logoutUrl)){
            driver.navigate().to(logoutUrl);
        }
    }
}
