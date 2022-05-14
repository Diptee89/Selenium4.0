package seleniumSession;

/*the HTTP status codes.
200 – Valid Link/success
301/302 - Page redirection temporary/permanent
404 – Page not found
400 – Bad request
401 – Unauthorized
500 – Internal Server Error
reads the status of each href link with the help of HttpURLConnection class.

1. All the links are tagged with either link <a> or image <img> on a web page. Collect the links based on tags <a>, <img>
2. Send HTTP request and read HTTP response code of every link.


 */
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinkDemo {

	public static void main(String[] args) {
		String url = "";

		int respCode = 200;

		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("http://www.zlti.com");

		List<WebElement> links = driver.findElements(By.tagName("a"));
		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {
			url = it.next().getAttribute("href");

			System.out.println(url);

			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if (!url.startsWith("homePage")) {
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			try {
				// Use URL Class - Create object of the URL Class and pass the urlLink as
				// parameter
				URL link = new URL(url);
				// Create a connection using URL object (i.e., link)
				HttpURLConnection httpConn = (HttpURLConnection) link.openConnection(); // reads the status of each href
																						// link

				httpConn.setRequestMethod("HEAD");

				httpConn.connect();

				//use getResponseCode() to get the response code.
				respCode = httpConn.getResponseCode();

				if (respCode >= 400) {
					System.out.println(url + " is a broken link");
				} else {
					System.out.println(url + " is a valid link");
				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		driver.quit();
	}

}
