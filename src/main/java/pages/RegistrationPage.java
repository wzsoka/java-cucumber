package pages;

import dataProvider.ConfigFileReader;
import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.components.HeaderTopbar;

import java.time.Duration;

public class RegistrationPage {

    private final String DASHBOARD_URL;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    @FindBy(xpath = "//input[@id='gender_f']/parent::button")
    private WebElement womanRB;

    @FindBy(xpath = "//input[@id='gender_m']/parent::button")
    private WebElement manRB;

    @FindBy(xpath = "//input[starts-with(@id,'first_name')]")
    private WebElement firstNameIF;

    @FindBy(xpath = "//input[starts-with(@id,'last_name')]")
    private WebElement lastNameIF;

    @FindBy(xpath = "//input[starts-with(@id,'email')]")
    private WebElement emailIF;

    @FindBy(xpath = "//input[starts-with(@id,'password')]")
    private WebElement passwordIF;

    @FindBy(tagName = "deich-lib-checkbox")
    public WebElement privacyPolicyCB;

    @FindBy(xpath = "//deich-lib-hydra-button[@data-testid='button-register']")
    public WebElement registerButton;

    @FindBy(xpath = "//input[starts-with(@id,'email')]/following-sibling::span[@class='errordisplay']")
    public WebElement errorMessage;

    public HeaderTopbar headerTopbar;

    protected WebDriver driver;


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.headerTopbar = new HeaderTopbar(this.driver);
        ConfigFileReader configFileReader = new ConfigFileReader();
        String REGISTER_URL = configFileReader.getConfigValue("registerUrl");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe(REGISTER_URL));
        PageFactory.initElements(this.driver, this);
        this.firstName = generateRandomName();
        this.lastName = generateRandomName();
        this.email = this.lastName + "@gmail.com";
        this.password = configFileReader.getConfigValue("password");
        this.DASHBOARD_URL = configFileReader.getConfigValue("dashboardUrl");
    }

    public void registerNewUser(){
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(manRB)).click();
        firstNameIF.sendKeys(firstName);
        lastNameIF.sendKeys(lastName);
        emailIF.sendKeys(email);
        passwordIF.sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(privacyPolicyCB)).click();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(registerButton)).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(DASHBOARD_URL));
    }

    public void registerIncomplete() {
        firstNameIF.sendKeys(firstName);
        lastNameIF.sendKeys(lastName);
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(registerButton)).click();
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(errorMessage));
    }

    public void registerWithOccupiedEmail(){
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(manRB)).click();
        firstNameIF.sendKeys(firstName);
        lastNameIF.sendKeys(lastName);
        ConfigFileReader configFileReader = new ConfigFileReader();
        String savedUser = configFileReader.getConfigValue("savedUser");
        emailIF.sendKeys(savedUser);
        passwordIF.sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(privacyPolicyCB)).click();
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    private String generateRandomName(){
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z').get();
        return generator.generate(7);
    }
}
