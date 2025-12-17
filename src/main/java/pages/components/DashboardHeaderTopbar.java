package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardHeaderTopbar extends HeaderTopbar{

    @FindBy(xpath = "//deich-lib-shortcut[@aria='account']")
    public WebElement accountButton;

    @FindBy(xpath = "//a[@href='/hu-hu/my-account/orders']")
    public WebElement menuItemOrders;

    public DashboardHeaderTopbar(WebDriver driver) {
        super(driver);
    }
}
