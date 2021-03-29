import ecommerceSite.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Utilities {

    WebDriver driver;
    public  Utilities(WebDriver remoteDriver){
        driver = remoteDriver;
    }

    public void registerAnUser(String myEmail, String fakeFirstName, String fakeLastName) throws InterruptedException {


        driver.findElement(By.xpath("//*[@href='http://automationpractice.com/index.php?controller=my-account']")).click();
        WebElement authenticationElem = driver.findElement(By.tagName("h1"));

        Assert.assertEquals(authenticationElem.getText(), "AUTHENTICATION");
        Assert.assertTrue(driver.getTitle().contains("Login"));

        driver.findElement(By.id("email_create")).sendKeys(myEmail); // tenia emailString
        driver.findElement(By.name("SubmitCreate")).click();

        Thread.sleep(3000);
        String email = driver.findElement(By.id("email")).getAttribute("value");
        System.out.println(email);
        Assert.assertEquals(myEmail, email); // tenia emailString

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

        //clicking on register button
        registerButton.click();
    }
}
