package seleniumSession;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DropDownHandel {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get("https://demoqa.com/select-menu");
		System.out.println(driver.getTitle());
		String expected = "ToolsQA";
		String actual = driver.getTitle();
		if (expected.equals(actual)) {
			System.out.println("Page loded correctly");
		} else {
			System.out.println("Page not loded correctly");
		}

//		
		WebElement eSelectValue=driver.findElement(By.className("css-tlfecz-indicatorContainer"));
		eSelectValue.click();
		driver.findElement(By.xpath("Text()='Group 2, option 2")).click();
		
		// Handle Single Old Style Select Menu Drop down
		WebElement eOldSelectMenu = driver.findElement(By.id("oldSelectMenu"));
		Select ddlOldSelectMenu = new Select(eOldSelectMenu);
		ddlOldSelectMenu.selectByVisibleText("Voilet");
		ddlOldSelectMenu.selectByValue("1");
		ddlOldSelectMenu.selectByIndex(0);

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

}
