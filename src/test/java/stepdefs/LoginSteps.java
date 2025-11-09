package stepdefs;


import dataProvider.ConfigFileReader;
import io.cucumber.java.hu.Akkor;
import io.cucumber.java.hu.Amikor;
import io.cucumber.java.hu.És;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage;

import static stepdefs.Hooks.driver;

public class LoginSteps {

    @Amikor("Bejelentkezek korábban létrehozott felhasználóval")
    public void bejelentkezekKorábbanLétrehozottFelhasználóval() {
        LoginPage loginPage = new LoginPage(driver);
        ConfigFileReader configFileReader = new ConfigFileReader();
        String savedUser = configFileReader.getConfigValue("savedUser");
        String pw = configFileReader.getConfigValue("password");
        loginPage.login(savedUser, pw);
    }

    @Amikor("Bejelentkezek {string} és {string} adatokkal")
    public void bejelentkezekÉsAdatokkal(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    @Akkor("{string} szöveg jelenik meg")
    public void szövegJelenikMeg(String errorMessage) {
        WebElement message;
        if (errorMessage.equals("Kérjük, ellenőrizd a megadott adatokat.")){
            message = driver.findElement(By.cssSelector("div.global-alert-message.error"));
        } else {
            message = driver.findElement(By.cssSelector("span.form-control-errors"));
        }
        Assertions.assertEquals(errorMessage, message.getText().trim());
    }

    @És("A bejelentkezés oldalon maradok")
    public void aBejelentkezésOldalonMaradok() {
        LoginPage loginPage = new LoginPage(driver);
        Assertions.assertTrue(loginPage.loginButton.isDisplayed());
    }
}
