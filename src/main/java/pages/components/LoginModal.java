package pages.components;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginModal {

    @FindBy(tagName = "deich-lib-modal-login")
    private WebElement modal;

    @FindBy(xpath = "//input[starts-with(@id,'username')]")
    public WebElement usernameIF;

    @FindBy(xpath = "//input[starts-with(@id,'password')]")
    private WebElement passwordIF;

    @FindBy(xpath = "//*[@dataid='login-button']")
    public WebElement loginButton;

    @FindBy(css = "deich-lib-hydra-button.register-button")
    public WebElement registerButton;

    @FindBy(xpath = "//div[@class='icon-wrapper close-area']")
    private WebElement closeArea;

    WebDriver driver;


    public LoginModal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(modal));
    }

    public void login(String username, String password){
        usernameIF.sendKeys(username);
        passwordIF.sendKeys(password);
        passwordIF.sendKeys(Keys.TAB);
        // negatív scenariok egy részénél nem lesz kattintható
        if (loginButton.isEnabled()) {
            loginButton.click();
        }
    }

    public void closeLoginModal(){
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(closeArea)).click();
    }
}
