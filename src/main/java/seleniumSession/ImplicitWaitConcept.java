package seleniumSession;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ImplicitWaitConcept {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//Selenium 4 has replaced the TimeUnit with Duration.
		// Imp wait:
		// is only for webelements
		// its a global wait - will be applied for all the web elements by default
		// its not applicable for non webelements -- title, alert, url

		driver.manage().window().maximize();
		driver.get("https://Google.com");
	}

}
