package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
public class BaseTest {
	public static WebDriver driver;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	

	@BeforeTest
	public void beforeTestMethod()
	{
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ File.separator+"reports"+File.separator+"SDETADDExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.DARK);
		extent.setSystemInfo("HostName", "RHEL8");
		extent.setSystemInfo("UserName", "root");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Automation Tests Results by Vrinda");
	}
	@BeforeMethod
	@Parameters("browser")
	public void beforeMethodMethod(String browser,Method testMethod) 
	{
		logger =extent.createTest(testMethod.getName());
		setupDriver(browser);
		driver.manage().window().maximize();
		driver.get(Constants.url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	@AfterMethod
	public void afterMethod(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE ) {
			logger.log(Status.FAIL,MarkupHelper.createLabel(result.getName()+"- Testcase Failed", ExtentColor.RED));
			logger.log(Status.FAIL,MarkupHelper.createLabel(result.getThrowable()+"- Testcase Failed", ExtentColor.RED));}
		else if(result.getStatus()==ITestResult.SKIP) {
			logger.log(Status.SKIP,MarkupHelper.createLabel(result.getName()+"- Testcase skiped", ExtentColor.ORANGE));}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			logger.log(Status.SKIP,MarkupHelper.createLabel(result.getName()+"- Testcase skiped", ExtentColor.GREEN));}
		driver.quit();



	}

	@AfterTest
	public void afterTest()
	{
		extent.flush();
	}
	public void setupDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();}
		else if(browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();}
	}

}






