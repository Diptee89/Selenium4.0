package seleniumSession;
/*
 * Web Tables are categorized into two parts published that are Static Web Tables and Dynamic Web Tables.
 * Static web table:  The Number of rows and columns will be static. Eg. Table of days, months, etc.
 * Dynamic web table: Number of rows and columns will be dynamically changing, which means that it keeps on increasing or decreasing based on data. Eg: Student table, Customer table.
 */

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DynamicWebTableTest {
	static WebDriver driver;

	public static void main(String[] args) throws ParseException {
		driver = Browser.usingChrome();
//		dynamicWebTable();
		webTable();

	}

	public static void dynamicWebTable() {
		driver.get("https://www.nyse.com/ipo-center/filings");

		WebElement eTable = driver
				.findElement(By.xpath("//table[contains(@class, 'table-condensed spacer-lg')]/thead"));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", eTable);

		List<WebElement> listColumnsNumber = driver
				.findElements(By.xpath("//div[@class='flex gap-4 flex-col']/div[3]/div/table[1]/thead/tr/th"));
		System.out.println("No of columns in this table : " + listColumnsNumber.size());

		List<WebElement> listRowsNumber = driver
				.findElements(By.xpath("//div[@class='flex gap-4 flex-col']/div[3]/div/table[1]/tbody/tr"));
		System.out.println("No of Rows in this table : " + listRowsNumber.size());

		WebElement value1 = driver
				.findElement(By.xpath("//div[@class='flex gap-4 flex-col']/div[3]/div/table[1]/thead/tr[1]/th[2]"));

//		How to retrieve typed text from a textbox?
		
		WebElement value2 = driver
				.findElement(By.xpath("//div[@class='flex gap-4 flex-col']/div[3]/div/table[1]/tbody/tr[1]/td[2]"));
		System.out.println(value1.getText() + ": " + value2.getText());

		List<WebElement> value3 = driver
				.findElements(By.xpath("//div[@class='flex gap-4 flex-col']/div[3]/div/table[1]/tbody/tr/td"));
//		How can you fetch an attribute from an element?
		for (WebElement e : value3) {
			System.out.print(e.getAttribute("innerHTML"));
			System.out.println();
		}

	}

	public static void webTable() throws ParseException {
		
//		Example: Get Maximum of all the Values in a Column of Dynamic Table
		
		driver.get("https://money.rediff.com/gainers/bsc/daily/groupa.");
        String max;
        double n=0, p=0;
        
        //No of Columns
        List<WebElement>  columns = driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/thead/tr/th"));
        System.out.println("No of columns : " +columns.size());
        
      //No of Rows
        List<WebElement>  rows = driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr/td[1]"));
        System.out.println("No of rows : " +rows.size());
        
        for (int i =1;i<rows.size();i++)
        {    
            max= driver.findElement(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr[" + (i+1)+ "]/td[4]")).getText();
            NumberFormat f =NumberFormat.getNumberInstance(); 
            Number num = f.parse(max);
            max = num.toString();
            n = Double.parseDouble(max);
            if(n>p)
             {    
                p=n;
             }
        }
        System.out.println("The web table Maximum current price : "+ p);

        

		
	}

}
