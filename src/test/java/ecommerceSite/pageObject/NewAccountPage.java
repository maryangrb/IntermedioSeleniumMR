package ecommerceSite.pageObject;

import ecommerceSite.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NewAccountPage {

    public WebDriver driver;
    public NewAccountPage (WebDriver remoteDriver){
        this.driver = remoteDriver;
    }

    public String getRegistrationEmail(){
        /*WebElement email = driver.findElement(By.id("email"));
        String registratedEmail = email.getAttribute("value");*/
        String email = driver.findElement(By.id("email")).getAttribute("value");
        return email;
    }

    public MyAccountPage fillingPersonalInfoForm(String fakeFirstName, String fakeLastName) throws InterruptedException {

        //Hacer los FindBy de estos:
        WebElement femaleRadioButton = driver.findElement(By.id("id_gender2"));
        WebElement firstName = driver.findElement(By.id("customer_firstname"));
        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        WebElement password = driver.findElement(By.id("passwd"));
        WebElement daySelector = driver.findElement(By.id("days"));
        WebElement monthSelector = driver.findElement(By.id("months"));
        WebElement yearSelector = driver.findElement(By.id("years"));
        WebElement company = driver.findElement(By.id("company"));
        WebElement address = driver.findElement(By.id("address1"));
        WebElement address2 = driver.findElement(By.id("address2"));
        WebElement city = driver.findElement(By.id("city"));
        WebElement stateSelector = driver.findElement(By.id("id_state"));
        WebElement postalCode = driver.findElement(By.id("postcode"));
        WebElement countrySelector = driver.findElement(By.id("id_country"));
        WebElement additionalInfo = driver.findElement(By.id("other"));
        WebElement homePhone = driver.findElement(By.id("phone"));
        WebElement mobilePhone = driver.findElement(By.id("phone_mobile"));
        WebElement alias = driver.findElement(By.id("alias"));
        WebElement registerButton = driver.findElement(By.id("submitAccount"));

        femaleRadioButton.click();
        firstName.sendKeys(fakeFirstName);
        lastName.sendKeys(fakeLastName);
        //password.sendKeys(fake.internet().password()); //no puedo poner la pass randomica
        password.sendKeys(Constants.PASSWORD);

        Select selectDays = new Select(daySelector);
        selectDays.selectByValue(Constants.BIRTH_DAY);
        Select selectMonths = new Select(monthSelector);
        selectMonths.selectByValue(Constants.BIRTH_MONTH);
        Select selectYears = new Select(yearSelector);
        selectYears.selectByValue(Constants.BIRTH_YEAR);
        company.sendKeys("MyCompany");
        address.sendKeys("My address Nr1");
        address2.sendKeys("My address Nr2");
        city.sendKeys(Constants.CITY);
        Select selectState = new Select(stateSelector);
        selectState.selectByValue("2");
        postalCode.sendKeys("10000");
        Select selectCountry = new Select(countrySelector);
        selectCountry.selectByValue("21");
        additionalInfo.sendKeys("Additional information");
        homePhone.sendKeys("123456");
        mobilePhone.sendKeys("123456789");
        alias.clear();
        alias.sendKeys("MyAlias");

        Thread.sleep(2000);

        //clicking on register button
        registerButton.click();

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        return myAccountPage;
    }


}
