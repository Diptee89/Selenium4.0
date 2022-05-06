package seleniumSession;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckBoxDemo {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://demo.guru99.com/test/radio.html");
		
		WebElement chkbox1=driver.findElement(By.id("vfb-6-0"));
		
		//Select the check 1 check box
		chkbox1.click();
		//Check whether check 1 is selected or not
		if(chkbox1.isSelected()) {
			System.out.println("Option 1 checkbox  is Toggeled On");
		}else {
			System.out.println("Option 1 checkbox  is Toggeled Off");
		}
		
		//Uncheck the check box 1
		chkbox1.click();
		
		//Now Let's see whether un checked or not
		if(!chkbox1.isSelected()) {
			System.out.println("Option 1 checkbox  is Toggeled Off");
		}
	}

}
