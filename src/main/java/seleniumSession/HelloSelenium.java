package seleniumSession;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {
	static WebDriver driver;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		try {
			// Navigate to Url
			driver.get("https://google.com");

			// Enter text "q" and perform keyboard action "Enter"
			driver.findElement(By.name("q")).sendKeys("Hello Selenium" + Keys.ENTER);
		} finally {
			driver.quit();
		}
	}
	
	 protected void isLoaded() throws Error {
		    String url = driver.getCurrentUrl();
		  }

	public static void setUserID(String uID) {
//		Enter User ID
		WebElement field = driver.findElement(By.name("u_ID"));
		clearAndType(field, uID);

	}
	public static void setUserPassword(String uPass) {
//		Enter User Password
		WebElement field = driver.findElement(By.name("password"));
		clearAndType(field, uPass);

	}

	private static void clearAndType(WebElement field, String text) {
		field.clear();
		field.sendKeys(text);
	}
}
