package ecommerceSite.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AuthenticationPage {

    @FindBy(id = "SubmitLogin")
    public WebElement signInBtn;


    public WebDriver driver;
    public AuthenticationPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
        PageFactory.initElements(driver, this);
    }

    public void validateAuthPage(){
        WebElement authenticationElem = driver.findElement(By.tagName("h1"));
        Assert.assertEquals(authenticationElem.getText(), "AUTHENTICATION");
        Assert.assertTrue(driver.getTitle().contains("Login"));
    }

    public NewAccountPage createEmailOfTheAccount(String myEmail){
        driver.findElement(By.id("email_create")).sendKeys(myEmail);
        driver.findElement(By.name("SubmitCreate")).click();

        NewAccountPage newAccountPage = new NewAccountPage(driver);
        return newAccountPage;
    }

    public MyAccountPage tryToLogin(String myEmail) throws InterruptedException {

        //try to login...
        WebElement emailLoginField = driver.findElement(By.id("email"));
        WebElement passwordLoginField = driver.findElement(By.id("passwd"));

        emailLoginField.sendKeys(myEmail);  //tenia email
        passwordLoginField.sendKeys("holamundo");
        System.out.println("before login..."+driver.getTitle());
        //WebElement signInBtn = driver.findElement(By.id("SubmitLogin"));
        Thread.sleep(5000);

        signInBtn.click();

        MyAccountPage myAccountPage = new MyAccountPage (driver);
        return myAccountPage;
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }


}
