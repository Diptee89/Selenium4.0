package seleniumSession;

/*
 * Robot class is used to (generate native system input events) take the control of mouse and keyboard.
 * keyPress - takes keyCode as Parameter and Presses here a given key.
 * keyrelease - takes keyCode as Parameterand Releases a given key
 * Both the above methods Throws - IllegalArgumentException, if keycode is not a valid key.
 */
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadFiles {

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

	public static void uploadFile(String fileLocation) throws InterruptedException {
		try {
			// Setting clipboard with file location
			setClipboardData(fileLocation);
			// native key strokes for CTRL, V and ENTER keys

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER); // Constant for the ENTER virtual key.
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V); // Constant for the "V" key.
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL); // Constant for the CONTROL virtual key.
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(90);
			robot.keyRelease(KeyEvent.VK_ENTER);

//			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static void sendKeys_ToUpload() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		ChromeDriver d = new ChromeDriver();
		d.manage().window().maximize(); // always write wait code after this
		d.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10)); // for page load
		d.get("https://www.monsterindia.com/seeker/registration"); // Testing webpage
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // for Implicit wait

		JavascriptExecutor js = (JavascriptExecutor) d; // Scrolling using JavascriptExecutor
//		   js.executeScript("window.scrollBy(0,380)");//Scroll Down to file upload button (+ve)

		Thread.sleep(3000);

		// FILE UPLOADING USING SENDKEYS ....

		WebElement browse = d.findElement(By.xpath("//input[@id='file-upload']"));
		js.executeAsyncScript("arguments[0].scrollIntoView()", browse);

		// click on ‘Choose file’ to upload the desired file
		browse.sendKeys("C:\\Users\\dsingh\\Documents\\Issue.docx"); // Uploading the file using sendKeys
		System.out.println("File is Uploaded Successfully");

	}

	public static void autoITUpload() throws IOException, InterruptedException {
		/*
		 * https://www.softwaretestinghelp.com/file-upload-in-selenium/ Go to AUTOIT ->
		 * DOWNLOADS. Download the latest AutoIT. Proceed with AutoIT installation and
		 * setup (next -> agree -> 32/64 bit selection -> file installation location
		 * selection -> Finish. There are 2 setup files: a) AutoIt version 3 and b)
		 * SciTE autoit 3. After installation is done, open AutoIT editor. Go to the
		 * location where setup files are saved, click on ‘SciTE.exe’ file, and the
		 * AutoIT editor opens. Please see the below screenshot for AutoIT editor.
		 */
		/*
		 * Now, let us understand in short how to use this tool:
		 * 
		 * Open the AutoIT editor. We need to write a simple code in AutoIT editor,
		 * required for file upload operation (the name of the file to be uploaded, will
		 * be mentioned in the code). Now close the editor and right click on it, you
		 * will see compile script option. Choose compile script (x64) option for 64 bit
		 * machine and go with compile script (x86) for a 32-bit machine. As soon as the
		 * above step is completed, a .exe file is created and this file will be
		 * mentioned in our selenium eclipse code. After compilation, as seen the below
		 * image ‘fileupload.exe’ file gets created. Now we can make use of this file in
		 * the Selenium web driver script.
		 */
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		ChromeDriver d = new ChromeDriver();
		d.manage().window().maximize(); // always write wait code after this
		d.get("https://opensource-demo.orangehrmlive.com/"); // Testing webpage

		WebElement uname = d.findElement(By.id("txtUsername")); // Username.........ID
		uname.sendKeys("Admin");

		WebElement pwd = d.findElement(By.name("txtPassword")); // Password.........NAME
		pwd.sendKeys("admin123");

		WebElement loginb = d.findElement(By.xpath("//input[@id='btnLogin']"));
		loginb.click(); // Loginbutton......XPATH

		WebElement pim = d.findElement(By.id("menu_pim_viewPimModule"));
		pim.click(); // Admin tab-PIM

		WebElement config = d.findElement(By.id("menu_pim_Configuration"));
		config.click(); // Configuration tab

		WebElement data_imp = d.findElement(By.partialLinkText("Data "));
		data_imp.click(); // Data Import tab.....PARTIALLINKT

		// UPLOADING FILE USING AutoIT....

		WebElement browser = d.findElement(By.xpath("//input[@id='pimCsvImport_csvFile']")); // Browse button
		browser.click();

		Runtime.getRuntime().exec("C:\\Users\\Chait\\Desktop\\autoit\\fileupload.exe");
		System.out.println("2");
		Thread.sleep(3000);

		WebElement upload = d.findElement(By.id("btnSave")); // Upload button
		upload.click();
		System.out.println("3");

		System.out.println("File Uploaded Successfully"); // Confirmation message

	}

}
