package seleniumSession;

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
