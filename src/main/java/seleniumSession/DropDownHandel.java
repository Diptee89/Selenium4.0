package seleniumSession;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropDownHandel {
	public static WebDriver driver;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		/*
		 * navigateT0(); selectDDL(); selectMultiDDL();
		 */
		handleNewStyleDDL();
	}

	public static void navigateT0() {
		driver.get("https://demoqa.com/select-menu");
		System.out.println(driver.getTitle());
		String expected = "ToolsQA";
		String actual = driver.getTitle();
		if (expected.equals(actual)) {
			System.out.println("Page loded correctly");
		} else {
			System.out.println("Page not loded correctly");
		}
	}

	public static void selectDDL() {
//		
		WebElement eSelectValue = driver.findElement(By.className("css-tlfecz-indicatorContainer"));
		eSelectValue.click();
		driver.findElement(By.xpath("Text()='Group 2, option 2")).click();

		// Handle Single Old Style Select Menu Drop down
		WebElement eOldSelectMenu = driver.findElement(By.id("oldSelectMenu"));
		Select ddlOldSelectMenu = new Select(eOldSelectMenu);
		ddlOldSelectMenu.selectByVisibleText("Voilet");
		ddlOldSelectMenu.selectByValue("1");
		ddlOldSelectMenu.selectByIndex(0);

	}

	public static void selectMultiDDL() {
		// Handle Standard multi select drop down

		WebElement eStandardMultiSelect = driver.findElement(By.id("cars"));
		Select ddlStandardMultiSelect = new Select(eStandardMultiSelect);

		List<WebElement> listGetOptions = ddlStandardMultiSelect.getOptions();
		for (WebElement egetOptions : listGetOptions) {
			System.out.println("Options: " + egetOptions.getText());
		}

		if (ddlStandardMultiSelect.isMultiple()) {

			ddlStandardMultiSelect.selectByIndex(0);
			ddlStandardMultiSelect.selectByValue("saab");
			ddlStandardMultiSelect.selectByVisibleText("Opel");
			ddlStandardMultiSelect.selectByVisibleText("Audi");

			ddlStandardMultiSelect.deselectByIndex(0);
			// All selected options belonging to this select tag
			List<WebElement> listValue = ddlStandardMultiSelect.getAllSelectedOptions();
			for (WebElement e : listValue) {
				System.out.println(e.getText());
			}
			ddlStandardMultiSelect.deselectAll();

		}
	}

	public static void handleNewStyleDDL() {
		driver.get("https://jqueryui.com/selectmenu/");
	
		
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(".demo-frame")));
		
		driver.findElement(By.xpath("//span[contains(@id, 'speed-button')]")).click();
		
		List<WebElement> lst=driver.findElements(By.xpath("//ul[contains(@class, 'ui-menu ui')]//li/div"));
		for(WebElement e:lst) {
			System.out.println(e.getAttribute("innerHTML"));
			if(e.getAttribute("innerHTML").equalsIgnoreCase("Fast")) {
				e.click();
				break;
			}
		}
	}
}
