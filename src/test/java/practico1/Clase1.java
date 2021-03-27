package practico1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Clase1 {
    String URL = "https://www.booking.com";
    //String URL = "https://www.facebook.com";
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        //driver.get(URL);
    }

    //Ej 1: Obtener el webelement cuya className sea qqqqqq
    //Obtener la lista de elementos cuya className sea qqqqqq
    @Test
    public void primerTest(){
        System.out.println("---->"+ driver.getTitle());
        //WebElement Hx = driver.findElement(By.className("qqqqqq"));
        List<WebElement> Hx = driver.findElements(By.className("qqqqqq"));
        Assert.assertNotEquals(Hx.size(), 0); //la lista está vacía, por eso falla el test
    }

    //Ej 2: Obtener todos los elementos cuya tagName sea “h112”.
    //Agregar asserts para validar que la lista se encuentre vacía.
    @Test
    public void elemTagName(){
        List<WebElement> listElem = driver.findElements(By.tagName("h112"));
        System.out.println("los elementos d la lista son: " +listElem);

        Assert.assertTrue(listElem.isEmpty() == true);
        /*isEmpty arroja True, si la lista esta vacia...
        si esta tiene elementos, isEmpty() es false...
        falso == falso Por lo tanto, la condicion es verdadera, por lo que el
        assert no fallará... pq estoy acertando en True, es decir, esperando que la
        condicion sea verdadera
        */

        //Assert.assertFalse( listElem.isEmpty() == true);
        /*si la lista esta vacia... isEmpty == true... y true == true
        El test va a fallar pq estoy acertando que la condicion sea falsa, si la
        condicion no es falsa, entonces, el assert fallará...*/

        //Assert.assertEquals (0 , listElem.size());

    }

    //Buscar un tagname que no exista utilizando el h1.
    //Validar que la lista no sea vacía
    @Test
    public void elemTagNameH1(){
        List<WebElement> ulList = driver.findElements(By.tagName("h1"));
        Assert.assertFalse(ulList.isEmpty()==false);
        Assert.assertTrue(ulList.isEmpty() == false);
        Assert.assertNotEquals(0,ulList.size()); //la lista está vacía
    }

    @Test
    public WebElement getBtnByText (String btnText){
        List <WebElement> btnList = driver.findElements(By.className ("bui-button__text"));
        Assert.assertNotEquals (btnList.size(), 0 );
        for (WebElement btn : btnList ){
            if (btn.getText().equals(btnText)){
                return btn;

            }
           // btn.click();
        }
        Assert.assertFalse (true , "No se encontro el elemento" );
        return null;
    }


    @Test
    public void bbc (){
        driver.get("https://www.bbc.com");

        //WebElement divElement = driver.findElement(By.tagName("div"));

        List<WebElement> divElements = driver.findElements(By.tagName("div"));

        for (WebElement div : divElements) {

            List<WebElement> parrafos = div.findElements(By.tagName("p"));
            List<WebElement> imgs = div.findElements(By.tagName("img"));

            System.out.println("Imgs --> " + imgs.size());

            if (parrafos.size() > 0) {
                System.out.println("Se encontraron parrafos: " +parrafos.size());
            }
        }
    }

    //Objetivo: buscar WebElements dentro de un WebElement:
    @Test
    public void buscarElemDentroDeUnWebElement() {
        driver.get("https://www.bbc.com");

        WebElement element = driver.findElement(By.tagName("div"));

        List<WebElement> elements = element.findElements(By.tagName("p"));
        for(WebElement e : elements){
            System.out.println("Los elementos son: " + e.getText());
        }

    }


    //Obtener todos los divs y contar la cantidad de divs que existan que contengan id
    @Test
    public void testApple(){
        driver.get("https://www.apple.com");
        int cont = 0;
        List<WebElement> divs = driver.findElements(By.tagName("div"));
        for(WebElement div : divs){
            if(div.getAttribute("id").isEmpty() == false){
                cont++;
            }
        }
        System.out.println("La cantidad de divs con id es " + cont);
    }

    @Test
    public void getAttribute(){

        driver.get("https://mundomac.com");

        List<WebElement> listaImagenes = driver.findElements(By.tagName("img"));
        Assert.assertNotEquals(listaImagenes.size(), 0);
        int contador = 0;

        for (WebElement img: listaImagenes){
            String altAttr = img.getAttribute("alt");

            if (altAttr.isEmpty() || altAttr.equals("") ) {
                String imgSrc = img.getAttribute("src");
                System.out.println("****** " + imgSrc);
                contador++;
            }
        }

        System.out.println("Se encontraron " + contador + " imagenes sin alt");
    }





    @AfterMethod
    public void tearDown() throws InterruptedException {
        System.out.println("Se ha terminado de ejecutar un test...");
        Thread.sleep(15000);
        driver.close();
    }
}
