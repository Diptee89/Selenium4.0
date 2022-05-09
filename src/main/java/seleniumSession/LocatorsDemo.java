package seleniumSession;
/* Xpath
 * //<HTML tag>[@attribute_name='attribute_value']
 * //<HTML tag>[@attribute_name1='attribute_value1'][@attribute_name2='attribute_value2]
 * //<HTML tag>[@attribute_name1='attribute_value1' and @attribute_name2='attribute_value2]
 * //<HTML tag>[@attribute_name1='attribute_value1' or @attribute_name2='attribute_value2]
 * //<HTML tag>[contains(@attribute_name,'attribute_value')]
 * //<HTML tag>[starts-with(@attribute_name,'attribute_value')]
 * //<HTML tag>[text()='New look for sign-in coming soon']
 * findElement(By.xpath("(//input[@type='text'])[last()]"))
 * findElement(By.xpath("(//input[@type='text'])[position()=2]"))
 * findElement(By.xpath("//label[2]"))
 * //*[@id='FirstName']/following::input[@type='text']
 * //*[@id='LastName']//preceding::input[@type='text']"
 */

/*Types of CSS Selectors 
 * 
driver.findElement(By.cssSelector("input.login"))
ID> css=input#email
class> css=input.form-control
Attribute> css=input[name='email_id']
ID/Class and Attribute>>css=input#email[name='email_id'][placeholder='Email']
css=input#Passwd[type='password'][name='Passwd'].
Sub-string>> css=input#password[name^='pass'] | Prefix
css=input#password[name$='ord'] | Suffix
css=input#password[name*='word'] |sub string
Inner text > css=button:contains("Log In")
 */

/*
findElement(By.partialLinkText("Volume"))
driver.findElements(By.linkText("Volume MG"))
findElements(By.tagName("tr")
driver.findElements(By.className("smalltext smtb"))
driver.findElement(By.id("cdensity"))

 */

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class LocatorsDemo {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get("http://demo.guru99.com/test/facebook.html");

		/*
		 * findElement Returns the first most web element if there are multiple web
		 * elements found with the same locator Throws exception NoSuchElementException
		 * if there are no elements matching the locator strategy Find element by XPath
		 * will only find one web element
		 */
		driver.findElement(By.id("email")).sendKeys("Demo@gmail.com");
		driver.findElement(By.name("pass")).sendKeys("Pass1234");
		driver.findElement(By.linkText("New Tours")).click();
		driver.navigate().back();
//		driver.navigate().forward();
		driver.findElement(By.cssSelector("input#email")).clear(); // CSS Selector – Tag and ID input#email
		driver.findElement(By.cssSelector("input.inputtext")).sendKeys("CssTagClass@gmail.com");// CSS Selector – Tag
																								// and Class
																								// input.inputtext
		driver.findElement(By.cssSelector("input[name='pass']")).clear();// CSS Selector – Tag and Attribute
																			// input[name=lastName] square brackets
																			// within which a specific attribute
		driver.findElement(By.cssSelector("input.inputtext[type='password']")).sendKeys("Pass@12345678"); // CSS
																											// Selector
																											// – tag,
																											// class,
																											// and
																											// attribute>
																											// input.inputtext[tabindex=1]

		driver.navigate().refresh();
//		driver.findElement(By.linkText("Login"));

		/*
		 * findElements Returns a list of web elements Returns an empty list if there
		 * are no web elements matching the locator strategy It will find a collection
		 * of elements whose match the locator strategy. Each Web element is indexed
		 * with a number starting from 0 just like an array
		 */
		List<WebElement> listOfElements = driver.findElements(By.xpath("//div"));
		for (WebElement value : listOfElements) {
			System.out.println(value.getText());
		}

	}

}
