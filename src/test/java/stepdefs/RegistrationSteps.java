package stepdefs;

import io.cucumber.java.hu.Akkor;
import io.cucumber.java.hu.Amikor;
import io.cucumber.java.hu.És;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.RegistrationPage;

import static stepdefs.Hooks.driver;

public class RegistrationSteps {

    @Amikor("Teljesen kitöltöm és beküldöm a regisztrációt")
    public void teljesenKitöltömÉsBeküldömARegisztrációt() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerAsPerson();
    }

    @Amikor("Hiányosan kitöltöm és beküldöm a regisztrációt")
    public void hiányosanKitöltömÉsBeküldömARegisztrációt() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerIncomplete();
    }

    @Amikor("Korábban regisztrált email címmel próbálok regisztrálni")
    public void korábbanRegisztráltEmailCímmelPróbálokRegisztrálni() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerWithSavedUser();
    }

    @Akkor("Hibaüzenet jelzi a kitöltendő mezőt")
    public void hibaüzenetJelziAKitöltendőMezőt() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        String errorText = "A mező kitöltése kötelező.";
        Assertions.assertEquals(errorText, registrationPage.errorMessages.get(0).getText().trim());
    }

    @És("A Regisztráció oldalon maradok")
    public void aRegisztrációOldalonMaradok() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        Assertions.assertTrue(registrationPage.registerButton.isDisplayed());
    }

    @Akkor("Hibaüzenet jelzi hogy már létezik fiók az email címmel")
    public void hibaüzenetJelziHogyMárLétezikFiókAzEmailCímmel() {
        WebElement message = driver.findElement(By.cssSelector("span.form-control-errors"));
        String errorText = "Ezzel az e-mail címmel már létezik felhasználói fiók.";
        Assertions.assertEquals(errorText, message.getText().trim());
    }
}
