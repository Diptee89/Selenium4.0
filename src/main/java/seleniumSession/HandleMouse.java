package seleniumSession;

/*Mouse Actions 
 *  doubleClick();
 *  clickAndHold()
 *  dragAndDrop()
 *  moveToElement()
 *  contextClick()
 */

/*Keyboard Actions
 * sendKeys()
 * keyUp()
 * keyDown()
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class HandleMouse {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Actions act = new Actions(driver);

//		Perform Mouse Hover Action on the Web Element
	/*	driver.get("https://demoqa.com/tool-tips");

		WebElement eleHome = driver.findElement(By.cssSelector("button#toolTipButton"));
		WebElement eleChiled = driver.findElement(By.cssSelector("input#toolTipTextField"));

		
		Action mouseOverHome = act.moveToElement(eleHome).build();
//				The build() method is always the final method used so that all the listed actions will be compiled into a single step.

		mouseOverHome.perform();
//		Use the perform() method when executing the Action object

		Action mouseOverChild = act.moveToElement(eleChiled).build();
//		The build() method is always the final method used so that all the listed actions will be compiled into a single step.

		mouseOverChild.perform();
//Use the perform() method when executing the Action object
		*/
		
//		Perform Click Action on the Web Element
		driver.navigate().to("https://www.browserstack.com/");
		WebElement eleMainItem2=driver.findElement(By.linkText("Get started free"));
		
		act.moveToElement(eleMainItem2).click().build().perform();
		
	}

}
