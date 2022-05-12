package seleniumSession;
/*
 * the WebDriver always has the focus on the main browser window.
 * whenever an alert/popup appears, it opens up a new window.So, for handling the Alerts using Selenium WebDriver, 
 * the focus need to be shifted to the child windows opened by the Alerts. 
 * To switch the control from the parent window to the Alert window, the Selenium WebDriver  provides the following command: 
 * driver.switchTo( ).alert( );
 * Alert Interface to perform various required actions.
 * For example, accepting, dismissing, getting the text from the alert window,and writing some text on the alert window
 */

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserBasedAlerts {
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/alerts");
		
		simpleAlert();
		promptAlert();
		confirmationAlert();
	}

	/*
	 * These alerts are just informational alerts and have an OK button on them.
	 * Users can click on the OK button after reading the message displayed on the
	 * alert box.
	 */
	public static void simpleAlert() {

		driver.findElement(By.cssSelector("button#alertButton")).click();
		driver.switchTo().alert().accept();

		/*
		 * Reference variable is creates for Alert class which references to the alert
		 * by Alert simpleAlert = driver.switchTo().alert();. Alert
		 * simpleAlert=driver.switchTo().alert(); simpleAlert.accept();
		 */
	}

	/*
	 * In Prompt alerts, some input requirement is there from, where the user can
	 * enter his/her username and press the OK button or Cancel the alert box
	 * without entering any details .
	 */
	public static void promptAlert() {

		driver.findElement(By.id("promtButton")).click();
		Alert promptAlert = driver.switchTo().alert();
		System.out.println(promptAlert.getText());
		promptAlert.sendKeys("Hi This Diptee.");
		promptAlert.accept();
	}

	/*
	 * These alerts get some confirmation from the user in the form of accepting or
	 * dismissing the message box.
	 */
	public static void confirmationAlert() throws InterruptedException {

		driver.findElement(By.xpath("//button[@id= 'confirmButton' and @class= 'btn btn-primary']")).click();
		Alert confirmationAlert=driver.switchTo().alert();
		System.out.println(confirmationAlert.getText());
		confirmationAlert.accept();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@id= 'confirmButton' and @class= 'btn btn-primary']")).click();
		confirmationAlert.dismiss();

	}

}
