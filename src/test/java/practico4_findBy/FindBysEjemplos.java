package practico4_findBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindBysEjemplos {

    String URL = "https://www.netflix.com";
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    /*Ingresar a netflix
    Mostrar la cantidad de párrafos
    Mostrar la cantidad de links
    Imprimir los links que tienen texto
    Mostrar el texto de todos los botones*/

    @FindBy (tagName = "p")
    public List<WebElement> listaParrafos;

    @FindBy (tagName = "a")
    public List<WebElement> listaLinks;

    @FindBy (tagName = "button")
    public List<WebElement> listaBtns;

    @Test
    public void ejercicio1(){
        Assert.assertNotEquals(listaParrafos.size(), 0); //para asegurarnos q la lista tenga elementos
        Assert.assertNotEquals(listaLinks.size(), 0);
        Assert.assertNotEquals(listaBtns.size(), 0);

        System.out.println("La cant de parrafos es " + listaParrafos.size());
        System.out.println("La cant de links es " + listaLinks.size());

        for (WebElement link : listaLinks){
            if (link.getText().isEmpty() == false){
                System.out.println("-->" + link.getText());
            }
        }

        System.out.println("Los botones:");
        for (WebElement btn : listaBtns){
            if (btn.getText().isEmpty() == false){
                System.out.println("-->" + btn.getText());
            }
        }
    }

    @Test
    public void searchLoginBtn(){
        for(WebElement btn : listaBtns){
            if(btn.getText().equals("Login")){
                //...
            }
        }
    }

    @Test
    public void searchLinks(){
        //Olvide mi contraseña
        //Login

        for(WebElement link : listaLinks){
            if(link.getText().equals("Olvide mi contraseña")){
                link.click();
            }
            if(link.getText().contains("contraseña")){
                link.click();
            }
        }
    }

    @FindAll({
            @FindBy(how = How.TAG_NAME, using = "h1"),
            @FindBy(how = How.TAG_NAME, using = "h2"),
            @FindBy(how = How.TAG_NAME, using = "h3")

    })
    public  List<WebElement> getAllHs;

}
