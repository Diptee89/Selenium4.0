package seleniumSession;

/*The following is a list of all 5 Relative Locator methods in alphabetical order
 * above() – finds an element or elements located above a fixed element.
 * below() – finds an element or elements located below a fixed element.
 * near() – finds an element or elements located near a fixed element.
 * toLeftOf() – finds an element or elements located to the left of a fixed element.
 * toRightOf() – finds an element or elements located to the right of a fixed element.
 */
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class RelativeLocatorDemo {
	public static WebDriver driver;

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get("https://demo.guru99.com/test/newtours/");

		driver.findElement(RelativeLocator.with(By.tagName("input")).above(By.name("password"))).sendKeys("Alex MD");

	}

	public static void relativeLocator() {
	/*
		// Assume element1 is above element2
		WebElement element1;
		element1 = driver.findElement(By.id("1234"));
		String element2 = driver.findElement(withTagName("ABC").below(element1)).getText();
		System.out.println("Element below element1 is " + element2);

		// Assume element0 is above element1
//		WebElement element1;
		element1 = driver.findElement(By.id("1234"));
		String element0 = driver.findElement(withTagName("ABC").above(element1)).getText();
		System.out.println("Element above element1 is " + element0);

		// Assume element1 is on the left side of element3
//		WebElement element1;
		element1 = driver.findElement(withTagName("ABC").toLeftOf(element3)).getText();
		System.out.println(" Left of element 3 is : " + element1);

		// Assume element3 is on the right side of element1
		WebElement element3;
		element3 = driver.findElement(withTagName("ABC").toRightOf(element1)).getText();
		System.out.println(" Right of element 1 is : " + element3);
		
		*/
	}

}
