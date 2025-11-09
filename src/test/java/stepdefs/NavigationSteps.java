package stepdefs;

import dataProvider.ConfigFileReader;
import io.cucumber.java.hu.Adott;
import io.cucumber.java.hu.Amennyiben;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import pages.RegistrationPage;
import pages.StartPage;

import static stepdefs.Hooks.driver;

public class NavigationSteps {

    @Adott("A webalkalmazás kezdő oldalán állok")
    public void aWebalkalmazásKezdőOldalánÁllok() {
        navigateToHome();
        acceptCookies();
        StartPage startPage = new StartPage(driver);
        Assertions.assertTrue(startPage.logo.isDisplayed());
    }

    @Amennyiben("A Regisztráció oldalra navigálok")
    public void aRegisztrációOldalraNavigálok() {
        navigateToHome();
        StartPage startPage = new StartPage(driver);
        startPage.headerTopbar.clickRegisterMenu();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        Assertions.assertTrue(registrationPage.registerButton.isDisplayed());
    }

    @Amennyiben("A Bejelentkezés oldalra navigálok")
    public void aBejelentkezésOldalraNavigálok() {
        navigateToHome();
        StartPage startPage = new StartPage(driver);
        startPage.headerTopbar.clickLoginMenu();
        LoginPage loginPage = new LoginPage(driver);
        Assertions.assertTrue(loginPage.loginButton.isDisplayed());
    }

    private void navigateToHome(){
        ConfigFileReader configFileReader = new ConfigFileReader();
        String HOME_URL = configFileReader.getConfigValue("homeUrl");
        driver.navigate().to(HOME_URL);
    }

    private void acceptCookies(){
        try{
            WebElement okButton = driver.findElement(By.id("cmpwrapper")).getShadowRoot().findElement(By.cssSelector("span#cmpwelcomebtnyes"));
            okButton.click();
        } catch (NoSuchElementException e) {
            System.out.println("Accept cookies box is not present on start page.");
        }
    }
}
