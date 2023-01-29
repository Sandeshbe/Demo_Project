package demoProject;

import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GetTitle {
	static ExtentTest tests;
	static ExtentReports report;
	public WebDriver driver;

	String Username = "TestUsername";
	String EmailId = "TestUsername@gmail.com";
	String UserCurrentAddress = "Mysore";
	String UserPermanentAddress = "Shimoga";
	String Password = "12345";
	String ErrorMessageActual = "Invalid username or password!";

	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");

	@SuppressWarnings("deprecation")
	@BeforeClass
	public void startTest() {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@BeforeTest
	public void LaunchBroswer() {

		extent.attachReporter(spark);

		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Tata Elxsi Test Engineer");

	}

	@Test(priority = 1)

	public void SubmitForm() {
		driver.get("https://demoqa.com/");
		System.out.println("Page title is : " + driver.getTitle());

		ExtentTest tests = extent.createTest("Automation Test Case Result 01");

		if (driver.getTitle().equals("DEMOQA")) {
			tests.log(Status.PASS, "Test Passed");
		} else {
			tests.log(Status.FAIL, "Test Failed");
		}

		driver.manage().window().maximize();

		WebElement ElementTab = driver.findElement(By.xpath("//div[@class='card-body']/h5[1][text()='Elements']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ElementTab);

		ElementTab.click();

		WebElement TextBox = driver.findElement(By.xpath("//span[text()='Text Box']"));

		TextBox.click();
		WebElement UserName = driver.findElement(By.xpath("//input[@id='userName']"));

		UserName.sendKeys(Username);

		WebElement UserEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));

		UserEmail.sendKeys(EmailId);

		WebElement CurrentAddress = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));

		CurrentAddress.sendKeys(UserCurrentAddress);

		WebElement PermanentAddress = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));

		PermanentAddress.sendKeys(UserPermanentAddress);

		WebElement SubmitButton = driver.findElement(By.xpath("//button[@id='submit']"));

		js.executeScript("arguments[0].scrollIntoView();", SubmitButton);

		SubmitButton.click();

		WebElement OutputName = driver.findElement(By.xpath("//div[@id='output']/div/p[@id='name']"));

		WebElement OutputEmail = driver.findElement(By.xpath("//div[@id='output']/div/p[@id='email']"));

		WebElement OutputCurrentAddress = driver
				.findElement(By.xpath("//div[@id='output']/div/p[@id='currentAddress']"));

		WebElement OutputPermanentAddress = driver
				.findElement(By.xpath("//div[@id='output']/div/p[@id='permanentAddress']"));

		String OutputText = OutputName.getText();
		System.out.println(OutputText);

		String OutputEmailText = OutputEmail.getText();
		System.out.println(OutputEmailText);

		String OutputCurrentAddressText = OutputCurrentAddress.getText();
		System.out.println(OutputCurrentAddressText);

		String OutputPermanentAddressText = OutputPermanentAddress.getText();
		System.out.println(OutputPermanentAddressText);

		if (OutputText.contains(Username)) {
			tests.log(Status.PASS, "Correct Username");
		} else {
			tests.log(Status.FAIL, "InCorrect Username");
		}

		if (OutputEmailText.contains(EmailId)) {
			tests.log(Status.PASS, "Correct Email");
		} else {
			tests.log(Status.FAIL, "InCorrect Email");
		}

		if (OutputCurrentAddressText.contains(UserCurrentAddress)) {
			tests.log(Status.PASS, "Correct CurrentAddress ");
		} else {
			tests.log(Status.FAIL, "InCorrect CurrentAddress ");
		}

		if (OutputPermanentAddressText.contains(UserPermanentAddress)) {
			tests.log(Status.PASS, "Correct PermanentAddress ");
		} else {
			tests.log(Status.FAIL, "InCorrect PermanentAddress ");
		}

	}

	@Test(priority = 2)

	public void OpenBookStoreApplication() {

		WebElement LogoIcon = driver.findElement(By.xpath("//*[@id='app']/header/a/img"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", LogoIcon);
		ExtentTest tests = extent.createTest("Automation Test Case Result 02");

		LogoIcon.click();

		WebElement BookStoreElement = driver
				.findElement(By.xpath("//div[@class='card-body']/h5[text()='Book Store Application']"));
		js.executeScript("arguments[0].scrollIntoView();", BookStoreElement);

		BookStoreElement.click();

		WebElement LoginButton = driver.findElement(By.xpath("//button[@id='login']"));
		LoginButton.click();

		WebElement LoginUsername = driver.findElement(By.xpath("//input[@id='userName']"));
		LoginUsername.sendKeys(Username);
		WebElement LoginPassword = driver.findElement(By.xpath("//input[@id='password']"));
		LoginPassword.sendKeys(Password);

		WebElement LoginButton2 = driver.findElement(By.xpath("//button[@id='login']"));

		js.executeScript("arguments[0].scrollIntoView();", LoginButton2);

		LoginButton2.click();

		WebElement ErrorMessage = driver.findElement(By.xpath("//p[@id='name']"));

		if (ErrorMessage.getText().equals(ErrorMessageActual)) {
			tests.log(Status.PASS, "Error Message Verified!, TestCase Passed ");
		} else {
			tests.log(Status.FAIL, "TestCase Failed  ");
		}

	}

	@Test(priority = 3)

	public void PassedTestCase() {

		ExtentTest tests = extent.createTest("Automation Test Case Result 03");
		tests.log(Status.PASS, "TestCase Passed ");

	}

	@Test(priority = 4)

	public void FailedTestCase() {
		ExtentTest tests = extent.createTest("Automation Test Case Result 04");
		tests.log(Status.FAIL, "TestCase Failed  ");

	}

	@AfterTest
	public void endTest() {
		extent.flush();
	}
}
