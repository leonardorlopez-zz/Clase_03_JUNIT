package POM;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercuryTours_AutomatedTest {

	private WebDriver driver;
	// 1. HACEMOS CLICK EN REGISTER
	By registerLinkLocator = By.linkText("REGISTER");
                             
	// 2. Le indicamos a Selenium WebDriver que nos encontramos en la pagina de
	// REGISTER. Buscamos la imagen de REGISTER que se encuentra con una llave al
	// final.
	// Si existe esa llave, los campos se encuentran disponibles para ser
	// completados.
	By registerPageLocator = By.xpath(
			"/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/img");

	// 5. Localizamos el campo User Name , el campo Password , y el campo confirm
	// Password
	By usernameLocator = By.id("email");
	By passwordLocator = By.name("password");
	By confirmPasswordLocator = By.cssSelector("input[name='confirmPassword']");

	// 6. Localizamos el boton submit para enviar los datos
	By submitBtnLocator = By.name("submit");

	// Creamos el localizador para loguearnos (User) para pto 8
	By userLocator = By.name("userName");
	// Hacemos lo mismo para el campo password para pto 8
	By passLocator = By.name("password");
	// Boton signIn
	By signInBtnLocator = By.name("submit");

	By homePageLocator = By.xpath(
			"/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td/h3");

@Before
public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("http://demo.guru99.com/test/newtours/");
}

@After
public void tearDown() throws Exception {
	driver.close();
}

@Test
public void registerUser() throws InterruptedException {

	// 3. le damos click al localizador REGISTER
	driver.findElement(registerLinkLocator).click();

	// Usamos el temporizador
	Thread.sleep(2000);

	// 4. Confirmamos si la imagen (con la llave final)
	// REGISTER existe al dar click en el punto previo
	if (driver.findElement(registerPageLocator).isDisplayed()) {
		driver.findElement(usernameLocator).sendKeys("qualityadmin");
		driver.findElement(passwordLocator).sendKeys("pass1");
		driver.findElement(confirmPasswordLocator).sendKeys("pass1");
		driver.findElement(submitBtnLocator).click();
	} else {
		System.out.println("Register Page was not found");
	}

	// 7. AssertEquals() =>Utilizamos el texto "Note: Your user name is
	// qualityadmin." que se encuentra
	// en la ultima pagina como confirmacion de que hemos registrado el usuario de
	// forma exitosa.

	WebElement cheqPostReg = driver.findElement(By.xpath(
			"/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[3]/font/b"));

	assertEquals("Note: Your user name is qualityadmin.", cheqPostReg.getText());

// Creamos una Lista de WebElement para font porque el texto se encuentra en el tag <b>.
// Recorremos la lista chequea si nos pudimos registrar con el AssertEquals.
	List<WebElement> fonts = driver.findElements(By.tagName("b"));

	for(WebElement w1 : fonts) {
		System.out.println(w1.getText());
		String aux = "Note: Your user name is qualityadmin.";
		if(w1.getText().equalsIgnoreCase(aux)) {
			assertEquals(aux, w1.getText());
			System.out.println("Registro exitoso");
		}
	}

}

// 8. Vamos a la pagina Home y nos autenticamos con el user y password que
// creamos.
@Test
public void signIn() throws InterruptedException {
	// chequeamos si esta disponible el campo de userName para loguearnos, si es asi
	// enviamos las credenciales
	if (driver.findElement(userLocator).isDisplayed()) {
		driver.findElement(userLocator).sendKeys("qualityadmin");
		driver.findElement(passLocator).sendKeys("pass1");
		driver.findElement(signInBtnLocator).click();
		Thread.sleep(2000);
		// si no esta el texto de "Login Successfully" el test va a fallar.
		assertTrue(driver.findElement(homePageLocator).isDisplayed());

	} else {
		System.out.println("UserName textbox is not present");
	}
}

}
