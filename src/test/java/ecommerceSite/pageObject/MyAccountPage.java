package ecommerceSite.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?mylogout=']")
    public WebElement logoutBtn;

    @FindBy(tagName = "h1")
    public WebElement myAccountH1;

    @FindBy (xpath = "//*[@href='http://automationpractice.com/index.php?controller=my-account']")
    public WebElement userNameBtn;

    public WebDriver driver;
    public MyAccountPage (WebDriver remoteDriver){
        this.driver = remoteDriver;
        PageFactory.initElements(driver, this);
    }

    public AuthenticationPage clickLogoutBtn (){
        //log out...
        logoutBtn.click();
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        return authenticationPage;
    }

    public String getMyAccountH1Test(){
        return myAccountH1.getText();
    }

    public String getMyAccountUserNameBtnTest(){
        return userNameBtn.getText();
    }

}
