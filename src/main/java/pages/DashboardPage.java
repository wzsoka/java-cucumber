public class DashboardPage {

    @FindBy(xpath = "//span[text()='Ügyfélszám']")
    public WebElement customerNumber;

    @FindBy(xpath = "//a[@href='/hu-hu/my-account/orders']")
    public WebElement ordersButton;

    @FindBy(xpath = "//button[@data-testid='logout']")
    public WebElement logoutButton;

    public DashboardHeaderTopbar headerTopbar;


    public DashboardPage(WebDriver driver) {
        this.headerTopbar = new DashboardHeaderTopbar(driver);
        ConfigFileReader configFileReader = new ConfigFileReader();
        String DASHBOARD_URL = configFileReader.getConfigValue("dashboardUrl");
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.urlToBe(DASHBOARD_URL));
        PageFactory.initElements(driver, this);
    }

}
