package seleniumSession;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Browser {
	static WebDriver driver;
	public static WebDriver usingChrome() {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		return driver;
	}

	public static WebDriver usingFireFox() {
		System.setProperty("webdriver.gecko.driver", "c:\\Drivers\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}
	public static WebDriver usingInternetExplorer() {
//		webdriver.ie.driver  The location of the IE driver binary.
		System.setProperty("webdriver.ie.driver", "c:\\Drivers\\IEDriverServer.exe");
		driver=new InternetExplorerDriver();
		driver.manage().window().maximize();
		return driver;
	}
	public static void navigate() {
		//Convenient
		driver.get("https://selenium.dev");

		//Longer way
		driver.navigate().to("https://selenium.dev");
		driver.navigate().back(); //Pressing the browser’s back button
		driver.navigate().forward(); //Pressing the browser’s forward button
		driver.navigate().refresh(); //Refresh the current page
	}
	
}
