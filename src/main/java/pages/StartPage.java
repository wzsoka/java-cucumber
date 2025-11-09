package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.components.HeaderTopbar;

public class StartPage {

    @FindBy(xpath = "//img[@alt='logo-deichmann-px']")
    public WebElement logo;

    @FindBy(id = "cmpbox")
    public WebElement acceptCookiesBox;

    @FindBy(id = "cmpwelcomebtnyes")
    public WebElement okButton;

    public HeaderTopbar headerTopbar;

    protected WebDriver driver;

    public StartPage(WebDriver driver) {
        this.driver = driver;
        this.headerTopbar = new HeaderTopbar(this.driver);
        PageFactory.initElements(this.driver, this);
    }
}
