package seleniumSession;
/*https://www.softwaretestingmaterial.com/javascriptexecutor-selenium-webdriver/
 * JavaScript executor is an interface provided by Selenium .
 * It provides two methods such as “executeScript” & “executeAsyncScript” .
 * Sometimes these default Selenium locators may not work. Here comes the JavaScriptExecutor in the picture.
 * JavascriptExecutor in Selenium enables the WebDriver to interact with HTML DOM within the browser.
 * JavascriptExecutor js = (JavascriptExecutor) driver;
 * js.executeScript(Script,Arguments);
 * Script – The JavaScript to execute
 * Arguments – The arguments to the script(Optional). May be empty.
 * Returns – One of Boolean, Long, List, String, List, Boolean, WebElement, or null.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavascriptExecutorDemo {
	public static WebDriver driver;
	public static JavascriptExecutor js;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.softwaretestingmaterial.com");

		// To declare and set the start time
		long startTime = System.currentTimeMillis();
		// Calling executeAsyncScript() method to wait for 10 seconds
		js.executeAsyncScript("window.setTimeout(arguments&#91;arguments.length - 1], 10000);");
		// To get the difference current time and start time
		System.out.println("Wait time: " + (System.currentTimeMillis() - startTime));

//		js = (JavascriptExecutor) driver;

//		scrollByJS();
//		scrollToBottom();
	}

	public static void scroll() {
		driver.get("http://api.jquery.com/dblclick/");
		driver.switchTo().frame(0);

		WebElement ele = driver.findElement(By.cssSelector("html>body>div"));

		// Here I used JavascriptExecutor interface to scroll down to the targeted
		// element
//		How to scroll down to a particular element?
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
	}

	public static void scrollByJS() throws InterruptedException {
		// To Scroll Web page Down using Selenium WebDriver:

//		How to scroll down a page using JavaScript in Selenium?
		js.executeScript("window.scrollBy(0, 1000)", "");
		/*
		 * JavaScript scrollBy() method scrolls the document by the specified number of
		 * pixels. Syntax: window.scrollBy(xnum, ynum) xnum is a Number x-axis
		 * (horizontal). Positive values will scroll to the right, while negative values
		 * will scroll to the left ynum is a Number y-axis (vertical). Positive values
		 * will scroll down, while negative values scroll up
		 */
		Thread.sleep(2000);

//		To Scroll Web page Up using Sleneium WebDriver
		js.executeScript("window.scrollBy(0, -1000)", "");
	}

	public static void scrollToBottom() {

//		To scroll to the Bottom of the Web Page using Selenium WebDriver
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)", "");

	}

	public static void eleHeighlighter() {
		 WebElement elem = driver.findElement(By.className(""));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// use executeScript() method and pass the arguments
		// Here i pass values based on css style. Yellow background color with solid red
		// color border.
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", elem);

	}
	// Draws a red border around the found element. Does not set it back anyhow.
	public WebElement findElement(By by) {
	    WebElement elem = driver.findElement(by);
	    // draw a border around the found element
	    if (driver instanceof JavascriptExecutor) {
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", elem);
	    }
	    return elem;
	}
	public static void txtText() {
//		https://www.softwaretestingmaterial.com/javascriptexecutor-selenium-webdriver/

//		Scenario #1: To Type Text in a Text Box
//		To type Text in Selenium WebDriver without using sendKeys() method
		js.executeScript("document.getElementById('Email').value='SoftwareTestingMaterial.com';");
//		Scenario #2: To Click on a Button
		js.executeScript("document.getElementById('btnSubmit').click();");
		// or
//		js.executeScript("arguments&#91;0].click();", loginButton);
//		Scenario #3: To Handle Checkbox
		js.executeScript("document.getElementById('enter element id').checked=false;");

	}
}
