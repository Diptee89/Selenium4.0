package seleniumSession;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadFiles_UsingRobotClass {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/selenium/upload/");

		// Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("uploadframe")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("terms")));
		driver.findElement(By.name("terms")).click();
		// open upload window
		driver.findElement(By.name("uploadfile_0")).click();
		
		uploadFile("path to the file");
		Thread.sleep(2000);
	}

	public static void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static void uploadFile(String fileLocation) throws InterruptedException {
		try {
			// Setting clipboard with file location
			setClipboardData(fileLocation);
			// native key strokes for CTRL, V and ENTER keys
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER); //Constant for the ENTER virtual key.
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);  // Constant for the "V" key.
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL); //Constant for the CONTROL virtual key. 
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(90);
			robot.keyRelease(KeyEvent.VK_ENTER);

//			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
