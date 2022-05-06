package seleniumSession;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImplicitWaitConcept {
	public static WebDriver driver;
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		testImplicitlyWait();
		testExplicitlyWait();
		testFluentWait();
		
	}
	public static void testImplicitlyWait() {
		// Imp wait:
				// is only for webelements
				// its a global wait - will be applied for all the web elements by default
				// its not applicable for non webelements -- title, alert, url
		/*
		 * Selenium 3 Implicit wait will accept 2 parameters, the first parameter will
		 * accept the time as an integer value the second parameter will accept the time
		 * measurement in terms of SECONDS, MINUTES, MILISECOND, MICROSECONDS,
		 * NANOSECONDS, DAYS, HOURS, etc.
		 */
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 

		/*
		 * Selenium 4 has replaced the TimeUnit with Duration. The Duration class can be
		 * imported from java.time package and has methods to represent time duration in
		 * nano, millis, seconds, minutes, hours, days and so on. The method
		 * implicitlyWait(long, TimeUnit) from the type WebDriver.Timeouts are also
		 * deprecated. In Selenium 4, implicitlyWait method takes only one parameter.
		 */
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://google.com");
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Selenium WebDriver Interview questions");
		element.sendKeys(Keys.ENTER);

		List<WebElement> optionsList = driver.findElements(By.xpath("//div[@class='v7W49e']"));
		System.out.println(optionsList.size());

		
	}

//Explicit Wait
	/* replace the Thread.sleep with Explicit wait always
	 * Intelligent wait for certain Condition
	 * It provides better way to handle dynamic Ajex element 
	 * Element not visible; If element not found 
	 * Selenium 3 Error:the constructor webdriverwait(webdriver duration) is undefined
	 * Selenium 4  we need to pass the timeout as a Duration class
	 */

	public static void testExplicitlyWait() {
//		WebDriverWait wait = new WebDriverWait(driver, 20); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  //WebDriverWait() Will return webDriver Element 
																				
		driver.get("https://google.com");
		driver.findElement(By.name("q")).sendKeys("Selenium"+ Keys.ENTER);
		
		WebElement element=driver.findElement(By.xpath("//a/h3"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		System.out.println(element.getText());
		
		
		//Print all links 
		List<WebElement> optionsList = driver.findElements(By.xpath("//div[@class='v7W49e']"));
		System.out.println(optionsList.size());
		for(WebElement value:optionsList) {
			System.out.println(value.getText());
		}
	}

//	Fluent Wait -set default to 250 ms
	/*1. Its is a class and more smarter than the Explicitly Wait
	 * Excetion: NoSuchElementException / ElementNotVisibleException
	 * 2. It's Implementation on Wait Interface
	 * 3. Each fluent wait define maximum time as well as the frequency to wait for a condition. It checks for the web
	 * element at regular intervals until the object is found or timeout happens.
	 * Polling means: lets total time is 30s and frequency given 5s; now system will check every 5s until until the object is found or timeout happens.
	 * Frequency: Setting up a repeat cycle with the time frame to verify/check the condition at the regular interval of time
	 */
	
	public static void testFluentWait() {
		
		driver.get("https://google.com");
		driver.findElement(By.name("q")).sendKeys("Cheese"+ Keys.ENTER);
		// Waiting 30 seconds for an element to be present on the page, checking
		// for its presence once every 5 seconds.
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
		  public WebElement apply(WebDriver driver) {
		    return driver.findElement(By.id("foo"));
		  }
		});
		  
	}
}
