package seleniumSession;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {
	static WebDriver driver;
	public static WebDriver usingChrome() {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		return driver;
	}

	public static WebDriver usingFireFox() {
		System.setProperty("webdriver.gecko.driver", "c:\\Drivers\\geckodriver.exe");
		driver=new ChromeDriver();
		return driver;
	}
	public static WebDriver usingInternetExplorer() {
		System.setProperty("webdriver.IE.driver", "c:\\Drivers\\IEDriverServer.exe");
		driver=new ChromeDriver();
		return driver;
	}
}
