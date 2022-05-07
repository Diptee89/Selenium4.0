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

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://demo.guru99.com/test/radio.html");

		WebElement chkbox1 = driver.findElement(By.id("vfb-6-0"));

		// Select the check 1 check box
		chkbox1.click();
		// Check whether check 1 is selected or not
		if (chkbox1.isSelected()) {
			System.out.println("Option 1 checkbox  is Toggeled On");
		} else {
			System.out.println("Option 1 checkbox  is Toggeled Off");
		}

		// Uncheck the check box 1
		chkbox1.click();

		// Now Let's see whether un checked or not
		if (!chkbox1.isSelected()) {
			System.out.println("Checkbox1  is Toggeled Off");
		} else {
			System.out.println("Checkbox1  is Toggeled On");
		}
		
		//Selecting Checkbox and using isSelected Method
		
		WebElement chkBox2=driver.findElement(By.cssSelector("input[value='checkbox2']"));
		for(int i=0; i<2; i++) {
			chkBox2.click();
			System.out.println("Checkbox2 Status is - "+ chkBox2.isSelected());
		}
		
		WebElement chkBox3=driver.findElement(By.cssSelector("input[value='checkbox3']"));
		
		chkbox1.click();
		chkBox2.click();
		chkBox3.click();
//		Radio button
		
		WebElement radioOption1= driver.findElement(By.cssSelector("input[value='Option 1']"));
		WebElement radioOption2= driver.findElement(By.cssSelector("input[value='Option 2']"));
		WebElement radioOption3= driver.findElement(By.cssSelector("input[value='Option 3']"));
		
		radioOption1.click();
		radioOption2.click();
		radioOption3.click();
		
		
	}

}
