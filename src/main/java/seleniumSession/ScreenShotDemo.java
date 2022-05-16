package seleniumSession;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScreenShotDemo {
static WebDriver driver=null;
static Random rand;
	public static void main(String[] args) {
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		 //navigate to url
        driver.get("https://demoqa.com");
        
       //Take the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        //Copy the file to a location and use try catch block to handle exception
//        try {
//            FileUtils.copyFile(screenshot, new File("C:\\projectScreenshots\\homePageScreenshot.png"));
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
        
        //closing the webdriver
        driver.close();
    
	}
	public static void takeSnapShot(String strName) throws Exception {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver); 
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE); // to create image file
		rand = new Random();
		int value = rand.nextInt(1000000);
		File DestFile = new File("C:\\TFS\\Automation(Selenium)\\Maven_TestNG_POM_POI\\uCustoms\\src\\main\\java\\snapShots\\"+strName+value+".jpg");
//		FileUtils.copyFile(SrcFile, DestFile);

	}
	public static void fullPageScreenShot() {
//		Screenshot s=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
//        ImageIO.write(s.getImage(),"PNG",new File("C:\\projectScreenshots\\fullPageScreenshot.png"));
//        
		
	}
}
