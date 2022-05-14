package seleniumSession;

/*
 *  Can Selenium handle window pop-ups?
 *  Selenium does not support handling pop-ups. Alert is used to display a warning message. It is a pop-up window that comes up on the screen.

A few methods using which this can be achieved:

Void dismiss(): This method is called when the ‘Cancel’ button is clicked in the alert box.
Void accept(): This method is called when you click on the ‘OK’ button of the alert.
String getText(): This method is called to capture the alert message.
Void sendKeys(String stringToSed): This is called when you want to send some data to alert box.
 */
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsTest {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("http://demo.guru99.com/test/facebook.html");
		
		driver.findElement(By.id("email")).sendKeys("Demo@gmail.com");
		driver.findElement(By.name("pass")).sendKeys("Pass1234");
		driver.findElement(By.linkText("New Tours")).click();
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		//Store the alert text in a variable
		System.out.println(alert.getText());
		//Press the OK button
		alert.accept();
		
		
	}

}
