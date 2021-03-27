package practico2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class airbnbTest {
    String URL = "https://www.airbnb.com";
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void busquedaAirbnb() throws InterruptedException {
        //placeholder="Where are you going?"
        Thread.sleep(7000);
        driver.findElement(By.xpath("//*[@placeholder='Where are you going?']")).sendKeys("Budapest");
        Thread.sleep(7000);

        WebElement calendarioIni = driver.findElement(By.xpath("//div[@data-testid='structured-search-input-field-split-dates-0']/div/div[2]"));
        calendarioIni.click();

        Thread.sleep(2000);  //por el tiempo por si da error

        WebElement fechaInicio = driver.findElement(By.xpath("//*[@data-testid='datepicker-day-2021-03-27']"));
        fechaInicio.click();

        Thread.sleep(2000);
        WebElement fechaFin = driver.findElement(By.xpath("//*[@data-testid='datepicker-day-2021-03-28']"));
        fechaFin.click();

        WebElement huespedes = driver.findElement(By.xpath("//*[@data-testid='structured-search-input-field-guests-button']"));
        huespedes.click();

        /* como lo haría yo:
        WebElement incrementarAdults = driver.findElement(By.xpath("//*[@data-testid='stepper-adults-increase-button']"));
        incrementarAdults.click();
        incrementarAdults.click();
        WebElement incrementarChildren = driver.findElement(By.xpath("//*[@data-testid='stepper-children-increase-button']"));
        incrementarChildren.click();
        WebElement incrementarInfants = driver.findElement(By.xpath("//*[@data-testid='stepper-infants-increase-button']"));
        incrementarInfants.click(); */

        WebElement adultosElement = driver.findElement(By.xpath("(//*[@aria-label='increase value'])[1]"));
        adultosElement.click();
        adultosElement.click();

        WebElement niñosElement = driver.findElement(By.xpath("(//*[@aria-label='increase value'])[2]"));
        niñosElement.click();

        WebElement bebesElement = driver.findElement(By.xpath("(//*[@aria-label='increase value'])[3]"));
        bebesElement.click();

        WebElement searchBtn = driver.findElement(By.xpath("//*[@data-testid='structured-search-input-search-button']"));
        searchBtn.click();

        //explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 4);
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(text(),'3 guests')]"))));

        Assert.assertTrue(driver.getCurrentUrl().contains("Budapest"));
        List<WebElement> tresHuespedesList = driver.findElements(By.xpath("//*[contains(text(),'3 guests')]"));
        Assert.assertNotEquals(0,tresHuespedesList.size());

        boolean encontroElemento = false;
        for (WebElement element: tresHuespedesList){
            if (element.getText().contains("300+ stays")){
                encontroElemento = true;
            }
        }
        //Thread.sleep(2000);
        Assert.assertTrue(encontroElemento);

    }
}
