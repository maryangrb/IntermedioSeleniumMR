package ecommerceSite.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    //WebDriverWait wait;
    String siteURL = "http://automationpractice.com/index.php"; //going to the site
    public WebDriver driver;

    @FindBy(id = "firstname")
    public WebElement firstNameInputField;

    @FindBy(id = "lastname")
    public WebElement lastNameInputField;

    @FindBy(id = "email")
    public WebElement emailInputField;

    @FindBy(id = "email")
    public WebElement emailLoginField;

    @FindBy(id = "passwd")
    public WebElement passwordLoginField;

    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?controller=identity']")
    public WebElement myPersonalInformationBtn;

    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?mylogout=']")
    public WebElement logoutBtn;  //estas 2 lineas no deberian ir aqui, se van a quedar por ahora pq sino da error

    @FindBy(id = "SubmitLogin")
    public WebElement signInBtn; //estas 2 lineas no deberian ir aqui, debe ir en la de AuthPage

    @FindBy (xpath = "//*[@href='http://automationpractice.com/index.php?controller=my-account']")
    public WebElement userNameBtn;

    @FindBy(tagName = "h1")
    public WebElement myAccountH1; //estas 2 lineas no deberian ir aqui, debe ir en la de MyAccountPage


    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(siteURL);
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
        //wait = (WebDriverWait) new WebDriverWait(driver, 15);
        //driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void tearDown(){
        //driver.close();
    }


}
