package seleniumSession;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewStyleDDL {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://seleniumpractise.blogspot.com/2016/08/bootstrap-dropdown-example-for-selenium.html?lr=1");
		
		//<HTML tag>[contains(@attribute_name,'attribute_value')]
		driver.findElement(By.xpath("//button[contains(@class, 'btn btn-default')]")).click();
		
		List<WebElement> lst=driver.findElements(By.xpath("//ul[@class='dropdown-menu']//li//a"));
		
		for(WebElement e:lst) {
			// for every elements it will print the name using innerHTML
			System.out.println(e.getAttribute("innerHTML"));
			
			// Here we will verify if link (item) is equal to Java Script
			if (e.getAttribute("innerHTML").equalsIgnoreCase("CSS")) {
				e.click();
				break;
			}
		}
	}

}
