package seleniumSession;

import java.time.Duration;

import org.openqa.selenium.By;
/*
<div id="modal">
  <iframe id="buttonframe" name="myframe"  src="https://seleniumhq.github.io">
   <button>Click here</button>
 </iframe>
</div>
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class frameHandel {
	static WebDriver driver = null;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("hhtps://");

	}

	public static void usingWebElement() {
		// Store the web element
		WebElement iframe = driver.findElement(By.cssSelector("#modal>iframe"));

		// Switch to the frame
		driver.switchTo().frame(iframe);

		// Now we can click the button
		driver.findElement(By.tagName("button")).click();
	}

	public static void usingName_ID() {
		// Using the ID
		driver.switchTo().frame("buttonframe");

		// Or using the name instead
		driver.switchTo().frame("myframe");

		// Now we can click the button
		driver.findElement(By.tagName("button")).click();

	}

	public static void usingIndex() {
		// Switches to the second frame
		driver.switchTo().frame(1);
		// Now we can click the button
		driver.findElement(By.tagName("button")).click();
	}

	public static void backTODefaultContent() {
		// Return to the top level
		driver.switchTo().defaultContent();
	}

	public static void usingWaitAndSwitch() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("buttonframe")));

	}

}
