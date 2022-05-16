package seleniumSession;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class ElementsDemo {
	static ChromeDriver driver;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();

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
		driver.findElement(By.cssSelector("input.inputtext[type='password']")).sendKeys("Pass@12345678");

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

	public static void locators() {
		/*
		 * The recommendation is to keep your locators as compact and readable as
		 * possible. class name contains the search value (compound class names are not
		 * permitted) linkText whose visible text matches the search value
		 * partialLinkText whose visible text contains the search value. If multiple
		 * elements are matching, only the first one will be selected.
		 * 
		 * To improve the performance slightly, we can use either CSS or XPath to find
		 * this element in a single command
		 */
		By.id("uName");
		By.name("UserName");
		By.className("smalltext smtb");
		By.tagName("a");
		By.linkText("Volume MG");
		By.partialLinkText("Volume");

		By.cssSelector("input#email"); // using ID ("#email")
		By.cssSelector("input.form-control"); // Using class (.from-control)
		By.cssSelector("input[name='email_id']");// Using Attribute
		By.cssSelector("button:contains('Log In')");// Using Inner text
		By.cssSelector("input#email[name='email_id'][placeholder='Email']");// Using ID/class & Attribute
		By.cssSelector("input#password[name*='word']");// Using Sub String
		By.cssSelector("input#password[name^='pass']");// Using Sub string Prefix
		By.cssSelector("input#password[name$='ord']");// Using Sub string suffix

		By.xpath("//*[@id='uname']/div[1]/div[2]/a"); // absolute path
		By.xpath("//<HTML tag>[@attribute_name='attribute_value']");
		By.xpath("//<HTML tag>[@attribute_name1='attribute_value1'][@attribute_name2='attribute_value2]");
		By.xpath("//<HTML tag>[@attribute_name1='attribute_value1' and @attribute_name2='attribute_value2]");
		By.xpath("//<HTML tag>[@attribute_name1='attribute_value1' or @attribute_name2='attribute_value2]");
		By.xpath("//<HTML tag>[contains(@attribute_name,'attribute_value')]");
		By.xpath("//<HTML tag>[starts-with(@attribute_name,'attribute_value')]");
		By.xpath("//<HTML tag>[text()='Submit']");
		By.xpath("(//input[@type='text'])[last()]");
		By.xpath("(//input[@type='text'])[position()=2]");
		By.xpath("//label[2]");
		By.xpath("//*[@id='FirstName']/following::input[@type='text']");
		By.xpath("//*[@id='LastName']//preceding::input[@type='text']");
		By.xpath("/html/tbody/tr[1]/td[1]"); // Full path

	}

	public static void relativLocators() {
		By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("password"));
		By passwordLocator = RelativeLocator.with(By.tagName("input")).below(By.id("email"));
		By cancelLocator = RelativeLocator.with(By.tagName("button")).toLeftOf(By.id("submit"));
		By submitLocator = RelativeLocator.with(By.tagName("button")).toRightOf(By.id("cancel"));
		By emailLocator1 = RelativeLocator.with(By.tagName("input")).near(By.id("lbl-email"));
		By submitLocator1 = RelativeLocator.with(By.tagName("button")).below(By.id("email")).toRightOf(By.id("cancel"));
		
	}

}
