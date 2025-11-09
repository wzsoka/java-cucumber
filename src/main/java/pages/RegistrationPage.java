package pages;

import dataProvider.ConfigFileReader;
import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.components.HeaderTopbar;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class RegistrationPage {

    private final String DASHBOARD_URL;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String password;
    private final String street;
    private final String number;
    private final String postalCode;
    private final String city;

    @FindBy(id = "delivery-firstName")
    private WebElement firstNameIF;

    @FindBy(id = "delivery-lastName")
    private WebElement lastNameIF;

    @FindBy(id = "delivery-email")
    private WebElement emailIF;

    @FindBy(id = "delivery-phone")
    private WebElement phoneIF;

    @FindBy(id = "delivery-password")
    private WebElement passwordIF;

    @FindBy(id = "delivery-line1")
    private WebElement streetIF;

    @FindBy(id = "delivery-line2")
    private WebElement houseNumberIF;

    @FindBy(id = "delivery-postalCode")
    private WebElement postalCodeIF;

    @FindBy(id = "delivery-town")
    private WebElement cityIF;

    @FindBy(css = "div.privacy-label")
    public WebElement privacyPolicyCB;

    @FindBy(xpath = "//button[@data-key='register.component.register']")
    public WebElement registerButton;

    @FindBy(className = "form-control-errors")
    public List<WebElement> errorMessages;

    public HeaderTopbar headerTopbar;

    protected WebDriver driver;


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.headerTopbar = new HeaderTopbar(this.driver);
        ConfigFileReader configFileReader = new ConfigFileReader();
        String REGISTER_URL = configFileReader.getConfigValue("registerUrl");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(REGISTER_URL));
        PageFactory.initElements(this.driver, this);
        this.lastName = generateRandomName();
        this.email = this.lastName + "@gmail.com";
        this.phone = this.generateRandomPhoneNumber();
        this.firstName = configFileReader.getConfigValue("regFirstName");
        this.street = configFileReader.getConfigValue("regStreet");
        this.number = configFileReader.getConfigValue("regHNumber");
        this.postalCode = configFileReader.getConfigValue("regPostalCode");
        this.city = configFileReader.getConfigValue("regCity");
        this.password = configFileReader.getConfigValue("password");
        this.DASHBOARD_URL = configFileReader.getConfigValue("dashboardUrl");
    }

    public void registerAsPerson(){
        firstNameIF.sendKeys(firstName);
        lastNameIF.sendKeys(lastName);
        emailIF.sendKeys(email);
        phoneIF.sendKeys(phone);
        passwordIF.sendKeys(password);
        streetIF.sendKeys(street);
        streetIF.sendKeys(Keys.ENTER);
        houseNumberIF.sendKeys(number);
        postalCodeIF.sendKeys(postalCode);
        cityIF.sendKeys(city);
        cityIF.sendKeys(Keys.TAB);
        privacyPolicyCB.click();
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(DASHBOARD_URL));
    }

    public void registerIncomplete() {
        firstNameIF.sendKeys(firstName);
        lastNameIF.sendKeys(lastName);
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(errorMessages.get(0)));
    }

    public void registerWithSavedUser(){
        firstNameIF.sendKeys(firstName);
        lastNameIF.sendKeys(lastName);
        ConfigFileReader configFileReader = new ConfigFileReader();
        String savedUser = configFileReader.getConfigValue("savedUser");
        emailIF.sendKeys(savedUser);
        phoneIF.sendKeys(phone);
        passwordIF.sendKeys(password);
        streetIF.sendKeys(street);
        streetIF.sendKeys(Keys.ENTER);
        houseNumberIF.sendKeys(number);
        postalCodeIF.sendKeys(postalCode);
        cityIF.sendKeys(city);
        cityIF.sendKeys(Keys.TAB);
        privacyPolicyCB.click();
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
    }

    private String generateRandomName(){
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z').build();
        return generator.generate(7);
    }

    private String generateRandomPhoneNumber(){
        Random random = new Random();
        int min = 1000000;
        int max = 9999999;
        String phoneNumber = String.valueOf(random.nextInt( max - min) + min);
        return "+3620" + phoneNumber;
    }
}
