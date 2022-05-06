package mvnforjenkins;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class OpenBrowser {
	
	WebDriver driver;
	
	@Test
	
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://google.com");
		driver.findElement(By.name("q")).sendKeys("selenium tutorial");
		Thread.sleep(3000);

		List<WebElement> optionsList = driver.findElements(By.xpath("//ul[@class='erkvQe']//div[@class='OBMEnb']//ul[@class='G43f7e']"));

		System.out.println(optionsList.size());
		for(WebElement e:optionsList) {
			String value=e.getText();
			System.out.println(value);
			
			if(value.contains("selenium")) {
				e.click();
				break;
			}
		}
			

	}

}
