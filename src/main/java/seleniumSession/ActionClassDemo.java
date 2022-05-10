package seleniumSession;

import java.time.Duration;

import org.openqa.selenium.By;
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
		dragAndDrop();
	}

	public static void mouseOver() throws InterruptedException {
		driver.get("https://www.browserstack.com/");

		WebElement eleMenu = driver.findElement(By.xpath("//button[contains(@class, 'developers-dropdown')]"));
		WebElement eleSubMenu=driver.findElement(By.xpath("//ul[contains(@class, 'dropdown-menu developers')]//li[3]//a"));
		//Mouse over in Developers menu then click on Status sub menu.
		act.moveToElement(eleMenu).moveToElement(eleSubMenu).click().perform();
		driver.navigate().back();
	}
	public static void dragAndDrop() {
//		Drag And Drop Using Actions Class:
		driver.get("http://jqueryui.com/droppable");
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iFrame.demo-frame"))); //Or cssSelector(".demo-frame")
		
		WebElement eleDrag=driver.findElement(By.cssSelector("div#draggable")); //Or cssSelector("#draggable")
		WebElement eleDrop=driver.findElement(By.cssSelector("#droppable"));
		
		act.dragAndDrop(eleDrag, eleDrop).build().perform();
		
		
	}
	

}
