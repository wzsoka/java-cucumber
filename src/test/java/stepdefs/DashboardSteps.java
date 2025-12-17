package stepdefs;

import io.cucumber.java.hu.Akkor;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.StartPage;

import java.time.Duration;

import static stepdefs.Hooks.driver;

public class DashboardSteps {

    @Akkor("A fiókomba jutok")
    public void aFiókombaJutok() {
        assertLoggedIn();
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assertions.assertTrue(dashboardPage.headerTopbar.accountButton.isDisplayed());
        Assertions.assertTrue(dashboardPage.ordersButton.isDisplayed());
        new Actions(driver).moveToElement(dashboardPage.logoutButton).build().perform();
        logout();
    }

    @Akkor("A fiókom létrejön")
    public void aFiókomLétrejön() {
        assertLoggedIn();
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assertions.assertTrue(dashboardPage.headerTopbar.accountButton.isDisplayed());
        Assertions.assertTrue(dashboardPage.ordersButton.isDisplayed());
        new Actions(driver).moveToElement(dashboardPage.customerNumber).build().perform();
        logout();
    }

    private void assertLoggedIn(){
        WebElement message = driver.findElement(By.cssSelector("div.toast-inner"));
        Assertions.assertEquals("Sikeres bejelentkezés", message.getText().trim());
    }

    private void logout(){
        DashboardPage dashboardPage = new DashboardPage(driver);
        WebElement element = driver.findElement(By.cssSelector("button.logout"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(dashboardPage.logoutButton)).click();
        StartPage startPage = new StartPage(driver);
        new Actions(driver).moveToElement(startPage.headerTopbar.accountButton).build().perform();
        Assertions.assertTrue(startPage.headerTopbar.accountButton.isEnabled());
    }
}
