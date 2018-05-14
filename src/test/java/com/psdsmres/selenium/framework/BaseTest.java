package com.psdsmres.selenium.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.psdsmres.pages.PSDS_Page;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
	private static final String BREAK_LINE = "\n";// "</br>";
	protected String userName;
	protected String password;
	protected String browserType;
	private WebDriver driver;
	protected String applicationUrl;
	// public static String screen;
	public static ExtentTest test;
	public static ExtentReports extent;

	/* pages object initialization */
	protected PSDS_Page psds_Page;

	enum DriverType {
		Firefox, IE, Chrome
	}

	public BaseTest(String browser) {
		this.browserType = browser;
	}

	/*@BeforeSuite
	public void before() {
		extent = new ExtentReports("target/surefire-reports/ExtentReport.html", true);
	}*/

	@BeforeMethod
	public void setUp(Method method) throws Exception {
		if (browserType == null) {
			browserType = Configuration.readApplicationFile("Browser");
		}
		userName = Configuration.readApplicationFile("username");
		password = Configuration.readApplicationFile("password");

		this.applicationUrl = Configuration.readApplicationFile("URL");

		if (DriverType.Firefox.toString().toLowerCase().equals(browserType.toLowerCase())) {
			driver = new FirefoxDriver();
		} else if (DriverType.IE.toString().toLowerCase().equals(browserType.toLowerCase())) {
			System.setProperty("webdriver.ie.driver",
					Utilities.getPath() + "//src//test//resources//webdriver/IEDriverServer.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new InternetExplorerDriver(capabilities);
		}

		else if (DriverType.Chrome.toString().toLowerCase().equals(browserType.toLowerCase())) {
			System.setProperty("webdriver.chrome.driver",
					Utilities.getPath() + "/src//test//resources//webdriver/chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			throw new Exception("Please pass valiedbrowser type value");
		}

		/* Delete cookies */
		driver.manage().deleteAllCookies();

		/* maximize the browser */
		getWebDriver().manage().window().maximize();

		/* open application URL */
		getWebDriver().navigate().to(applicationUrl);
		psds_Page = PageFactory.initElements(getWebDriver(),PSDS_Page.class);

	}

	@AfterMethod
	public void captureScreenShot(ITestResult result) throws IOException, InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			captureScreenshot(result.getName());
		}
		//driver.quit();
		extent.endTest(test);
	}

	@AfterClass
	public void afterMainMethod() {
	driver.quit();
	}

	/*@AfterSuite
	public void tearDownSuite() {
		// reporter.endReport();
		extent.flush();
		extent.close();
	}*/

	/* Return WebDriver */
	public WebDriver getWebDriver() {
		return driver;
	}

	/* Handle child windows */
	public String switchPreviewWindow() {
		Set<String> windows = getWebDriver().getWindowHandles();
		Iterator<String> iter = windows.iterator();
		String parent = iter.next();
		getWebDriver().switchTo().window(iter.next());
		return parent;
	}

	/* capturing screenshot */
	public void captureScreenshot1(String fileName) {
		try {
			String screenshotName = Utilities.getFileName(fileName);
			FileOutputStream out = new FileOutputStream("screenshots//" + screenshotName + ".png");
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
			String path = Utilities.getPath();
			// String screen = "file://" + path + "/screenshots/" +
			// screenshotName + ".png";
			String screen = path + "/screenshots/" + screenshotName + ".png";
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
			Reporter.log(
					"<a href= '" + screen + "'target='_blank' ><img src='" + screen + "'>" + screenshotName + "</a>");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public void captureScreenshot(String fileName) throws IOException, InterruptedException {
		try{
		String screenshotName = Utilities.getFileName(fileName);
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = Utilities.getPath();
		String screen = path + "/screenshots/" + screenshotName + ".png";
		File screenshotLocation = new File(screen);
		FileUtils.copyFile(screenshot, screenshotLocation);
		Thread.sleep(2000);
		InputStream is = new FileInputStream(screenshotLocation);
		byte[] imageBytes = IOUtils.toByteArray(is);
		Thread.sleep(2000);
		String base64 = Base64.getEncoder().encodeToString(imageBytes);
		test.log(LogStatus.FAIL, "Snapshot below: " + test.addBase64ScreenShot("data:image/png;base64," + base64));
		Reporter.log(
				"<a href= '" + screen + "'target='_blank' ><img src='" + screen + "'>" + screenshotName + "</a>");
	} catch (Exception e) {
		System.out.println(e.getStackTrace());
	}
	}

	/* Report logs */
	public void reportLog(String message) {
		test.log(LogStatus.INFO, message);
		message = BREAK_LINE + message;
		logger.info("Message: " + message);
		Reporter.log(message);

	}

}
