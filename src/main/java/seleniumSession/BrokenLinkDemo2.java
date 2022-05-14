package seleniumSession;

import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinkDemo2 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://www.softwaretestingmaterial.com");

		// Used tagName method to collect the list of items with tagName "a"
		// findElements - to find all the elements with in the current page. It returns
		// a list of all webelements or an empty list if nothing matches
		List<WebElement> links = driver.findElements(By.tagName("a"));

		// To print the total number of links
		System.out.println("Total links are " + links.size());

		// used for inhance loop to print all links
		for (WebElement e : links) {
			// By using "href" attribute, we could get the url of the requried link
//			System.out.println(e.getAttribute("href"));
			String url = e.getAttribute("href");
			// calling verifyLink() method here. Passing the parameter as url which we
			// collected in the above link
			verifyLink(url);
		}

	}

	// The below function verifyLink(String urlLink) verifies any broken links and
	// return the server status.
	public static void verifyLink(String urlLink) {
		// Sometimes we may face exception "java.net.MalformedURLException". Keep the
		// code in try catch block to continue the broken link analysis
		try {
			// Use URL Class - Create object of the URL Class and pass the urlLink as
			// parameter
			URL link = new URL(urlLink);
			// Create a connection using URL object (i.e., link)
			HttpsURLConnection httpConnection = (HttpsURLConnection) link.openConnection();
			//Set the timeout for 2 seconds
			httpConnection.setConnectTimeout(2000);
			//connect using connect method
			httpConnection.connect();
			//use getResponseCode() to get the response code. 
			if(httpConnection.getResponseCode()== 200) {	
				System.out.println(urlLink+" - "+httpConnection.getResponseMessage());
			}else 
				if(httpConnection.getResponseCode()== 404) {
				System.out.println(urlLink+" - "+httpConnection.getResponseMessage());
			}

		}
		// getResponseCode method returns = IOException - if an error occurred
		// connecting to the server.
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
