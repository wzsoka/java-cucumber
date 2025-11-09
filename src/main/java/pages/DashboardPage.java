package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.components.HeaderTopbar;

public class DashboardPage {

    @FindBy(xpath = "//h1[text()[contains(.,'Fiókom')]]")
    public WebElement newAccountHeader;

    @FindBy(xpath = "//strong[text()[contains(.,'Fiókom')]]")
    public WebElement accountMenuTitle;

    public HeaderTopbar headerTopbar;


    public DashboardPage(WebDriver driver) {
        this.headerTopbar = new HeaderTopbar(driver);
        PageFactory.initElements(driver, this);
    }


}
