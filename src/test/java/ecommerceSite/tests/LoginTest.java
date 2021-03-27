package ecommerceSite.tests;

import com.github.javafaker.Faker;
import ecommerceSite.Constants;
import ecommerceSite.pageObject.AuthenticationPage;
import ecommerceSite.pageObject.LandingPage;
import ecommerceSite.pageObject.MyAccountPage;
import ecommerceSite.pageObject.NewAccountPage;
import ecommerceSite.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


    @Test
    public void registrationTest() throws InterruptedException {
        Faker fake = new Faker();
        //Thread.sleep(5000);

        String  fakeFirstName = (fake.name().firstName());
        String  fakeLastName = (fake.name().lastName());
        String myEmail = "maria85" + Math.random() + "@test.com"; // tenia emailString

        myPersonalInformationBtn.click();

        String obtainedName = firstNameInputField.getAttribute("value");
        String obtainedLastName = lastNameInputField.getAttribute("value");
        String obtainedEmail = emailInputField.getAttribute("value");

        Assert.assertEquals(fakeFirstName, obtainedName); //está correcto, la info q envié está en los campos de la persona
        Assert.assertEquals(fakeLastName, obtainedLastName);
        Assert.assertEquals(myEmail, obtainedEmail);  // tenia emailString
    }

    @Test
    public void loginTest() throws InterruptedException {
        Faker fake = new Faker();
        Thread.sleep(5000); //si quito la espera me da error
        String  fakeFirstName = (fake.name().firstName());
        String  fakeLastName = (fake.name().lastName());
        String myEmail = "maria85" + Math.random() + "@test.com";

        MyAccountPage myAccountPage = registerAnUser(myEmail, fakeFirstName, fakeLastName); //se hace un registro pasandole estos datos

        Thread.sleep(5000);
        AuthenticationPage authenticationPage = myAccountPage.clickLogoutBtn(); //me deslogueo

        myAccountPage = authenticationPage.tryToLogin(myEmail); //me vuelvo a loguear

        Assert.assertTrue(authenticationPage.getCurrentUrl().contains("my-account"));

        Assert.assertTrue(myAccountPage.getMyAccountUserNameBtnTest().contains(fakeFirstName));
        Assert.assertTrue(myAccountPage.getMyAccountUserNameBtnTest().contains(fakeLastName));

        Assert.assertEquals(myAccountPage.getMyAccountH1Test(),"MY ACCOUNT");
    }

    @Test
    public void checkUsernameIsUnique() throws InterruptedException {
        Faker fake = new Faker();
        String  fakeFirstName = (fake.name().firstName());
        String  fakeLastName = (fake.name().lastName());
        String myEmail = "maria85" + Math.random() + "@test.com";

        MyAccountPage myAccountPage = registerAnUser(myEmail, fakeFirstName, fakeLastName); //me registro

        AuthenticationPage authenticationPage = myAccountPage.clickLogoutBtn(); //me deslogueo

        //tengo que quitar estos driver.find
        driver.findElement(By.id("email_create")).sendKeys(myEmail);
        driver.findElement(By.id("SubmitCreate")).click();

        Thread.sleep(4000);
        WebElement errMsg = driver.findElement(By.xpath("//*[@id='create_account_error']/ol/li"));
        System.out.println(errMsg.getText());

        Assert.assertEquals(errMsg.getText(), "An account using this email address has already been registered. Please enter a valid password or request a new one.");

    }

    private MyAccountPage registerAnUser(String myEmail, String fakeFirstName, String fakeLastName) throws InterruptedException {

        LandingPage landingPage = new LandingPage(driver);
        AuthenticationPage authenticationPage = landingPage.clickOnLoginBtn();
        authenticationPage.validateAuthPage();

        NewAccountPage newAccountPage = authenticationPage.createEmailOfTheAccount(myEmail);

        Thread.sleep(3000);
        String email = newAccountPage.getRegistrationEmail();
        System.out.println("Email obtenido es: " + email);

        Thread.sleep(3000);
        Assert.assertEquals(myEmail, email);//esta variable email es: registratedEmail del profe

        return newAccountPage.fillingPersonalInfoForm(fakeFirstName, fakeLastName);
    }

}
