package seleniumSession;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

/*
getWindowHandle( ): When a website opens, we need to handle the main window i.e the parent window using driver.getWindowHandle( ); method. With this method, we get a unique ID of the current window which will identify it within this driver instance. This method will return the value of the String type.
getWindowHandles( ): To handle all opened windows which are the child windows by web driver, we use driver.getWindowHandles( ); method. The windows store in a Set of String type and here we can see the transition from one window to another window in a web application. Its return type is Set <String>.
switchto(): Using this method we perform switch operation within windows.
action: This method helps in performing certain actions on the windows.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowsHandel {
	static WebDriver driver;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://demoqa.com/browser-windows");

		driver.findElement(By.id("windowButton")).click();
		driver.findElement(By.cssSelector("#messageWindowButton")).click();

		//Get handles of the windows
		String parentWindow = driver.getWindowHandle();
		System.out.println(parentWindow);
		
		Set<String> allChildWindows=driver.getWindowHandles();
		
		System.out.println(allChildWindows);
		Iterator<String> iterator=allChildWindows.iterator();
		// Here we will check if child window has other child windows and will fetch the heading of the child window
        while(iterator.hasNext()) {
        	String childWindow=iterator.next();
        	if (!parentWindow.equalsIgnoreCase(childWindow)) {
        		driver.switchTo().window(childWindow);
        		driver.getTitle();
        		driver.close();
        	}
        }
        //  Switch back to the main window which is the parent window.
        driver.switchTo().window(parentWindow);
        driver.quit();
	}
	/*
	 * String handle= driver.getWindowHandle();
for (String handle : driver.getWindowHandles()) 
{
driver.switchTo().window(handle);
}
	 */
	public static void windowHandle() {
//		https://www.selenium.dev/documentation/webdriver/browser/windows/
//		We can get the window handle of the current window by using:
//		driver.getWindowHandle();
//		Switching windows or tabs 
		
		//Store the ID of the original window
		String originalWindow = driver.getWindowHandle();
		
		//Check we don't have other windows open already
		assert driver.getWindowHandles().size() == 1;
		//Click the link which opens in a new window
		driver.findElement(By.linkText("new window")).click();
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		//Wait for the new window or tab
//		wait.until(numberOfWindowsToBe(2));
		
		//Loop through until we find a new window handle
		for (String windowHandle : driver.getWindowHandles()) {
		    if(!originalWindow.contentEquals(windowHandle)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}
		

		//Wait for the new tab to finish loading content
//		wait.until(titleIs("Selenium documentation"));
		
		//Close the tab or window
		driver.close();

		//Switch back to the old tab or window
		driver.switchTo().window(originalWindow);
	}
}
