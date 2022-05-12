package seleniumSession;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionClassDemo {
	public static WebDriver driver;
	public static Actions act;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		act = new Actions(driver);
//		mouseOver();
//		drag();
//		dragAndDrop();
//		scroll();
//		contextClickdemo();
//		doubleClickDemo();
		keyBoard();
		
	}

	public static void mouseOver() throws InterruptedException {
		driver.get("https://www.browserstack.com/");

		WebElement eleMenu = driver.findElement(By.xpath("//button[contains(@class, 'developers-dropdown')]"));
		WebElement eleSubMenu = driver
				.findElement(By.xpath("//ul[contains(@class, 'dropdown-menu developers')]//li[3]//a"));
		// Mouse over in Developers menu then click on Status sub menu.
		act.moveToElement(eleMenu).moveToElement(eleSubMenu).click().perform();
		driver.navigate().back();
	}

	public static void drag() {
		driver.get("https://jqueryui.com/draggable/");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(".demo-frame")));

		WebElement eleDrag = driver.findElement(By.cssSelector("#draggable"));
//		act.moveToElement().moveByOffset(32, 32).build().perform();

		act.dragAndDropBy(eleDrag, 199, 84).build().perform();
		act.clickAndHold(eleDrag).perform();
	}

	public static void dragAndDrop() {
//		Drag And Drop Using Actions Class:
		driver.get("http://jqueryui.com/droppable");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iFrame.demo-frame"))); // Or
																												// cssSelector(".demo-frame")

		WebElement eleDrag = driver.findElement(By.cssSelector("div#draggable")); // Or cssSelector("#draggable")
		WebElement eleDrop = driver.findElement(By.cssSelector("#droppable"));

		act.dragAndDrop(eleDrag, eleDrop).build().perform();

	}

	public static void scroll() throws InterruptedException {
		driver.get("https://jqueryui.com/");

		// SCROLL DOWN
		act.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(3000);

		// SCROLL UP
		act.sendKeys(Keys.PAGE_UP).build().perform();
	}

	public static void contextClickdemo() {

		/*
		 * Launch the web browser and open the application 
		 * Find the required element and do right click on the element 
		 * Go to the options ‘copy’ and get the text of it and print it 
		 * Close the browser to end the program
		 */

		//Open the required URL
				driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
				
				WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.context-menu-one")));
				
				WebElement ele=driver.findElement(By.cssSelector("span.context-menu-one"));
				act.contextClick(ele).build().perform();
				
				WebElement eleCopy=driver.findElement(By.cssSelector("li.context-menu-icon-copy"));
				System.out.println(eleCopy.getText());
	}

	public static void doubleClickDemo() {
//		Launch the web browser and open the application
//		Find the required element and do double click on the element
//		Close the browser to end the program
		//Find the targeted element
		WebElement ele = driver.findElement(By.cssSelector("html>body>div"));
		
		                //Here I used JavascriptExecutor interface to scroll down to the targeted element
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
		                //used doubleClick(element) method to do double click action
		act.doubleClick(ele).build().perform();
	}

	public static void keyBoard() {
		driver.get("http://www.facebook.com/");
		
		WebElement txtUsername = driver.findElement(By.id("email"));

		act.moveToElement(txtUsername)
		.click()
		.keyDown(txtUsername, Keys.SHIFT)
		.sendKeys(txtUsername, "hello")
		.keyUp(txtUsername, Keys.SHIFT)
		.doubleClick(txtUsername)
		.contextClick()
		.build()
		.perform();
	}


}
