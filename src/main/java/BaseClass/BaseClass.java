package BaseClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	/**
	 * https://www.toolsqa.com/selenium-webdriver/webdrivermanager/ if you want to
	 * execute Selenium WebDriver automation scripts on the Chrome browser, then you
	 * need first to download chromedriver.exe and then use the System.setProperty
	 * method to set its path.
	 * 
	 * System.setProperty("webdriver.ie.driver", "c:\\Drivers\\IEDriverServer.exe");
	 * System.setProperty("webdriver.gecko.driver", "c:\\Drivers\\geckodriver.exe");
	 * System.setProperty("webdriver.ie.driver", "c:\\Drivers\\IEDriverServer.exe");
	 * 
	 * If we fail to define this path or if we provide the wrong path, then the
	 * script raises an error *
	 */

	/**
	 * In the latest versions, Selenium provides us with a "WebDriverManager" class
	 * that automates this process for us to concentrate on Selenium scripts rather
	 * than on browser settings.
	 * 
	 * WebDriverManager.chromedriver().setup();
	 * WebDriverManager.chromedriver().driverVersion("85.0.4183.38").setup();
	 * //instantiate a specific browser version
	 * WebDriverManager.chromedriver().architecture(io.github.bonigarcia.wdm.
	 * Architecture.X32).setup(); //instantiate a platform version (x32 or x64)
	 * chromedriver().arch32().setup(); chromedriver().arch64().setup();
	 * WebDriverManager.chromedriver() .version("83.0.0") .arch32()
	 * .proxy("proxyhostname:80") .proxyUser("username") .proxyPass("password")
	 * .setup();//set a proxy username and password
	 * WebDriverManager.firefoxdriver().setup();
	 * 
	 * WebDriverManager.iedriver().setup();
	 * 
	 * WebDriverManager.edgedriver().setup();
	 * 
	 * WebDriverManager.operadriver().setup();
	 * 
	 * WebDriverManager.phantomjs().setup();
	 */

	private String MainWindow;
	public WebDriver driver;
	

	private String url="https://google.com";

/************************************************************************************************************************/
	public WebDriver launchBrowser(String browserName) {

		System.out.println("browser name is: " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
//			WebDriverManager.chromedriver().browserVersion("83.0.4103").setup();
//			WebDriverManager.chromedriver().arch64();
//			System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
//			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
			System.out.println(caps.getBrowserName() +": "+caps.getBrowserVersion());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
			System.out.println(caps.getBrowserName() +": "+caps.getBrowserVersion());
		} else if (browserName.equalsIgnoreCase("ie")) {
//			WebDriverManager.iedriver().arch32();

			System.setProperty("webdriver.ie.driver", "c:\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
			System.out.println(caps.getBrowserName() +": "+caps.getBrowserVersion());
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		} else {
			System.out.println("please pass the correct browser: " + browserName);
		}

		return driver;
	}
	public WebDriver openIE() {
//		System.setProperty("webdriver.ie.driver", "c:\\Drivers\\IEDriverServer.exe");
		WebDriverManager.iedriver().arch32();
//		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
		driver.manage().deleteAllCookies();
		return driver;
	}
	public void navigateUrl() {
		if (url == null) {
			System.out.println("please pass the right url");
			return;
		}
		if (url.contains("https//:") || url.contains("http")) {
			
			driver.get(url);
//			System.out.println(driver.getTitle());
			switchToWindow();
			
		} else {
			driver.close();
			try {
				throw new Exception("INVALIDURLEXCEPTION - please pass the right url");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void switchToWindow() {
		MainWindow = driver.getWindowHandle();
//		System.out.println("Parent Winodow ID: " + MainWindow);
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();// to fetch the value iterator() will return from the collection object

		while (i1.hasNext()) { // loop if having some valu until loop will run
			String ChildWindow = i1.next();

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
//				System.out.println("Child Winodow ID: " + ChildWindow);
				// Switching to Child window
				driver.switchTo().window(ChildWindow);

			}
		}
	}

	public void switchBackToWindow() {
		driver.switchTo().window(MainWindow);
	}


/************************Element util************************/
	public By getBy(String locatorType, String locatorValue) {
		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;

		case "name":
			locator = By.name(locatorValue);
			break;

		case "classname":
			locator = By.className(locatorValue);
			break;

		case "xpath":
			locator = By.xpath(locatorValue);
			break;

		case "cssselector":
			locator = By.cssSelector(locatorValue);
			break;

		case "linktext":
			locator = By.linkText(locatorValue);
			break;

		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);
			break;

		case "tagname":
			locator = By.tagName(locatorValue);
			break;

		default:
			break;
		}
		return locator;

	}


	public WebElement getElement(By locator) {
//		waitForElementToBeVisible(locator, 5);
		WebElement elem = driver.findElement(locator);
//		changeColor("yellow", elem);
		drawBorder(elem);
		return elem;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean isElementPresent(By locator) {
		if (getElements(locator).size() > 0) {
			return true;
		}
		return false;
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean doIsEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public List<String> getLinksTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

	public List<String> getElementAttributeList(By locator, String attrName) {

		List<WebElement> eleList = getElements(locator);
		List<String> eleAttrList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String attrVal = e.getAttribute(attrName);
			System.out.println(attrVal);
			eleAttrList.add(attrVal);
		}
		return eleAttrList;
	}
/***************************JavaScript Utils**********************************/

	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");
		changeColor("rgb(0,200,0)", element);// 1
		changeColor(bgcolor, element);// 2
//		for (int i = 0; i < 15; i++) {
//			changeColor("rgb(0,200,0)", element);// 1
//			changeColor(bgcolor, element);// 2
//		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}
	public void drawBorder(WebElement element) {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].style.border='3px solid red'", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public String getTitleByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();
	}

	public void refreshBrowserByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
	}

	public void generateAlert(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + message + "')");
	}

	public String getPageInnerText() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}

	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void sendKeysUsingWithId(String id, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}

	public void scrollPageDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollPageDown(String height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, '" + height + "')");
	}

	public void scrollPageUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	
/************************Drop Down Utils*******************************************/
	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public List<String> doGetDropDownOptions(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		List<String> optionsValList = new ArrayList<String>();
		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsValList.add(text);
		}
		return optionsValList;
	}

	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

/*******************Actions Utils ************************/
	public void selectMenu(By parentMenu) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
	}

	public void selectSubMenu(By parentMenu, By childMenu) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);
		getElement(childMenu).click();
	}

	public void selectSubMenu(By parentMenu, By childMenu, By subChildMenu) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(childMenu)).perform();
		Thread.sleep(2000);
		getElement(subChildMenu).click();
	}

	public void selectSubMenu(By parentMenu, By childMenu1, By subChildMenu2, By subChildMenu3)
			throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(childMenu1)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(subChildMenu2)).perform();
		Thread.sleep(2000);
		getElement(subChildMenu3).click();
	}

	public void doContextClick(By locator) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(locator)).perform();
	}

	public int getRightClickOptionsCount(By rightClick, By rightClickOptions) {
		return getRightClickOptionsList(rightClick, rightClickOptions).size();
	}

	public List<String> getRightClickOptionsList(By rightClick, By rightClickOptions) {
		List<String> rightClickItems = new ArrayList<String>();
		doContextClick(rightClick);
		List<WebElement> itemsList = getElements(rightClickOptions);
		System.out.println(itemsList.size());

		for (WebElement e : itemsList) {
			String text = e.getText();
			// System.out.println(text);
			rightClickItems.add(text);
		}
		return rightClickItems;
	}

	public void selectRightClickMenu(By rightClick, By rightClickOptions, String value) {
		doContextClick(rightClick);
		List<WebElement> itemsList = getElements(rightClickOptions);
		System.out.println(itemsList.size());
		for (WebElement e : itemsList) {
			String text = e.getText();
			// System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}

	}

	/**
	 * Clicks in the middle of the given element. Equivalent to:
	 * Actions.moveToElement(onElement).click()
	 * 
	 * @param locator
	 */
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	/**
	 * Equivalent to calling: Actions.click(element).sendKeys(keysToSend).
	 * 
	 * @param locator
	 * @param value
	 */
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

/*********************************Wait utils **************************/

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible. polling time =
	 * 500 ms (default)
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementPresent(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));// sel 4.x
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0. polling time = 500 ms
	 * (default)
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));// sel 4.x
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeOut
	 * @param pollingTime
	 * @return
	 */
	public WebElement waitForElementPresent(By locator, int timeOut, long pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @param pollingTime
	 * @return
	 */
	public WebElement waitForElementToBeVisible(By locator, int timeOut, long pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}

	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}

	public String waitForUrl(int timeOut, String urlFraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		}
		return null;
	}

	public String waitForUrlToBe(int timeOut, String urlVal) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlToBe(urlVal))) {
			return driver.getCurrentUrl();
		}
		return null;
	}

	public String waitForTitleContains(int timeOut, String titleFraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		} else {
			System.out.println("title is not correct.....");
			return null;
		}
	}

	public String waitForTitleIs(int timeOut, String titleVal) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleIs(titleVal))) {
			return driver.getTitle();
		} else {
			System.out.println("title is not correct.....");
			return null;
		}
	}

	public WebDriver waitForFrameByLocator(int timeOut, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public WebDriver waitForFrameByIndex(int timeOut, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public WebDriver waitForFrameByString(int timeOut, String frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public WebDriver waitForFrameByElement(int timeOut, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 */
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 */
	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(getElement(locator))).click();
	}

	public void printAllElementsText(By locator, int timeOut) {
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
		for (WebElement e : eleList) {
			System.out.println(e.getText());
		}
	}

	public List<String> getElementsTextListWithWait(By locator, int timeOut) {
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}

	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public WebElement retryingElement(By locator, int timeOut) {

		WebElement element = null;
		int attempts = 0;
		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found in attempt : " + attempts + ":" + locator);
				try {
					Thread.sleep(500);// default interval time
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}
		if (element == null) {
			try {
				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			} catch (Exception e) {
				System.out.println("element is not found exception...tried for : " + timeOut + " secs "
						+ " with the interval of : " + 500 + " ms");
			}
		}

		return element;

	}

	public WebElement retryingElement(By locator, int timeOut, int intervalTime) {

		WebElement element = null;

		int attempts = 0;
		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found in attempt : " + attempts + ":" + locator);
				try {
					Thread.sleep(intervalTime);// custom interval time
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}
		if (element == null) {
			try {
				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			} catch (Exception e) {
				System.out.println("element is not found exception...tried for : " + timeOut + " secs "
						+ " with the interval of : " + intervalTime + " ms");
			}
		}

		return element;

	}

	public void waitForPageLoad(int timeOut) {

		long endTime = System.currentTimeMillis() + timeOut;
		while (System.currentTimeMillis() < endTime) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String state = js.executeScript("return document.readyState").toString();
			System.out.println(state);
			if (state.equals("complete")) {
				break;
			}

		}

	}

	public WebDriver waitForFrameWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchFrameException.class);

		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public Alert waitForAlertFluentWait(int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoAlertPresentException.class);

		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public WebElement waitForElementPrsentWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class, ElementNotInteractableException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementVisibleWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/************************************Random*******************************/
	public String uniqueNo() {
		Random rand = new Random();
		int value = rand.nextInt(10000);
		String number = Integer.toString(value);
		return number;
	}
}
