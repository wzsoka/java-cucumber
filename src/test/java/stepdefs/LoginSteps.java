package stepdefs;


import dataProvider.ConfigFileReader;
import io.cucumber.java.hu.Akkor;
import io.cucumber.java.hu.Amikor;
import io.cucumber.java.hu.És;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.StartPage;
import pages.components.LoginModal;

import static stepdefs.Hooks.driver;

public class LoginSteps {

    @Amikor("Bejelentkezek korábban létrehozott felhasználóval")
    public void bejelentkezekKorábbanLétrehozottFelhasználóval() {
        LoginModal loginModal = new LoginModal(driver);
        ConfigFileReader configFileReader = new ConfigFileReader();
        String savedUser = configFileReader.getConfigValue("savedUser");
        String pw = configFileReader.getConfigValue("password");
        loginModal.login(savedUser, pw);
    }

    @Amikor("Bejelentkezek {string} és {string} adatokkal")
    public void bejelentkezekÉsAdatokkal(String username, String password) {
        LoginModal loginModal = new LoginModal(driver);
        loginModal.login(username, password);
    }

    @Akkor("{string} szöveg jelenik meg")
    public void szövegJelenikMeg(String errorMessage) {
        WebElement message;
        if (errorMessage.equals("A bejelentkezés sikertelen. Kérjük, ellenőrizd bejelentkezési adataidat, és próbáld meg újra.")){
            message = driver.findElement(By.cssSelector("div.message"));
        } else {
            message = driver.findElement(By.xpath("//label[text()='Jelszó']/following-sibling::span[@class='errordisplay']"));
        }
        Assertions.assertEquals(errorMessage, message.getText().trim());
    }

    @És("A bejelentkezés oldalon maradok, amit bezárok")
    public void aBejelentkezésOldalonMaradokAmitBezárok() {
        LoginModal loginModal = new LoginModal(driver);
        Assertions.assertTrue(loginModal.loginButton.isDisplayed());
        loginModal.closeLoginModal();
        StartPage startPage = new StartPage(driver);
        Assertions.assertTrue(startPage.headerTopbar.accountButton.isEnabled());
    }
}
