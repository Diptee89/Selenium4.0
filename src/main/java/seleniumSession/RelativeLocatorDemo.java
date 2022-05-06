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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class RelativeLocatorDemo {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get("https://demo.guru99.com/test/newtours/");

		driver.findElement(RelativeLocator.with(By.tagName("input")).above(By.name("password")))
				.sendKeys("Alex MD");

	}

}
