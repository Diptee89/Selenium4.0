package seleniumSession;

import java.time.Duration;
import org.openqa.selenium.By;

import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("http://demo.guru99.com/test/facebook.html");
		
		driver.findElement(By.id("email")).sendKeys("Demo@gmail.com");
		driver.findElement(By.name("pass")).sendKeys("Pass1234");
		driver.findElement(By.linkText("New Tours")).click();
		driver.navigate().back();
//		driver.navigate().forward();
		driver.findElement(By.cssSelector("input#email")).clear(); //CSS Selector – Tag and ID input#email
		driver.findElement(By.cssSelector("input.inputtext")).sendKeys("CssTagClass@gmail.com");//CSS Selector – Tag and Class  input.inputtext
		driver.findElement(By.cssSelector("input[name='pass']")).clear();//CSS Selector – Tag and Attribute input[name=lastName] square brackets within which a specific attribute 
		driver.findElement(By.cssSelector("input.inputtext[type='password']")).sendKeys("Pass@12345678");  //CSS Selector – tag, class, and attribute> input.inputtext[tabindex=1]

		driver.findElement(By.cssSelector("label:contain('Email or Phone')"));

		
	}

}
