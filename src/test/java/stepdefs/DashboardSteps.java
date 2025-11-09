package stepdefs;

import io.cucumber.java.hu.Akkor;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.DashboardPage;
import pages.StartPage;

import static stepdefs.Hooks.driver;

public class DashboardSteps {

    @Akkor("A fiókomba jutok")
    public void aFiókombaJutok() {
        assertLoggedIn();
        DashboardPage dashboardPage = new DashboardPage(driver);
        new Actions(driver).moveToElement(dashboardPage.headerTopbar.accountIcon).build().perform();
        Assertions.assertTrue(dashboardPage.accountMenuTitle.isDisplayed());
        dashboardPage.headerTopbar.logout();
        assertLoggedOut();
    }

    @Akkor("A fiókom létrejön")
    public void aFiókomLétrejön() {
        assertRegistered();
        DashboardPage dashboardPage = new DashboardPage(driver);
        new Actions(driver).moveToElement(dashboardPage.headerTopbar.accountIcon).build().perform();
        Assertions.assertTrue(dashboardPage.newAccountHeader.isDisplayed());
        dashboardPage.headerTopbar.logout();
        assertLoggedOut();
    }

    private void assertRegistered(){
        WebElement message = driver.findElement(By.cssSelector("div.global-alert-message.success"));
        Assertions.assertEquals("Sikeres regisztráció.", message.getText().trim());
    }

    private void assertLoggedIn(){
        WebElement message = driver.findElement(By.cssSelector("div.global-alert-message.success"));
        Assertions.assertEquals("Sikeres bejelentkezés.", message.getText().trim());
    }

    private void assertLoggedOut(){
        StartPage startPage = new StartPage(driver);
        new Actions(driver).moveToElement(startPage.headerTopbar.accountIcon).build().perform();
        Assertions.assertTrue(startPage.headerTopbar.menuItemLogin.isEnabled());
    }
}
