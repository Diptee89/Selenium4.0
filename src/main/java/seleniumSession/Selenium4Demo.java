package seleniumSession;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Selenium4Demo {

	public static void main(String[] args) {
//		https://www.softwaretestingmaterial.com/selenium-4/
		ChromeOptions options = new ChromeOptions();

		options.setAcceptInsecureCerts(true);
		
		System.getProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		
        ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://www.softwaretestingmaterial.com");

	}

}
