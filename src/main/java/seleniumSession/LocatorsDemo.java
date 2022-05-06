package seleniumSession;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class LocatorsDemo {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("http://demo.guru99.com/test/facebook.html");
		
		
		/*findElement
		 * Returns the first most web element if there are multiple web elements found with the same locator
		 * Throws exception NoSuchElementException if there are no elements matching the locator strategy
		 * Find element by XPath will only find one web element
		 */
		driver.findElement(By.id("email")).sendKeys("Demo@gmail.com");
		driver.findElement(By.name("pass")).sendKeys("Pass1234");
		driver.findElement(By.linkText("New Tours")).click();
		driver.navigate().back();
//		driver.navigate().forward();
		driver.findElement(By.cssSelector("input#email")).clear(); //CSS Selector – Tag and ID input#email
		driver.findElement(By.cssSelector("input.inputtext")).sendKeys("CssTagClass@gmail.com");//CSS Selector – Tag and Class  input.inputtext
		driver.findElement(By.cssSelector("input[name='pass']")).clear();//CSS Selector – Tag and Attribute input[name=lastName] square brackets within which a specific attribute 
		driver.findElement(By.cssSelector("input.inputtext[type='password']")).sendKeys("Pass@12345678");  //CSS Selector – tag, class, and attribute> input.inputtext[tabindex=1]

		driver.navigate().refresh();
//		driver.findElement(By.linkText("Login"));
		
		/*findElements
		 * Returns a list of web elements
		 * Returns an empty list if there are no web elements matching the locator strategy
		 * It will find a collection of elements whose match the locator strategy.
		 * Each Web element is indexed with a number starting from 0 just like an array
		 */
		List<WebElement> listOfElements = driver.findElements(By.xpath("//div"));
		for(WebElement value:listOfElements) {
			System.out.println(value.getText());
		}
		
	}

}
