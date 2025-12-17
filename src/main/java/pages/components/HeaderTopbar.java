package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderTopbar {

    @FindBy(tagName = "deich-lib-shortcuts")
    public WebElement headerTopbar;

    @FindBy(xpath = "//deich-lib-hydra-icon-button[@icon='account']")
    public WebElement accountButton;

    protected WebDriver driver;

    public HeaderTopbar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(headerTopbar));
    }
}
