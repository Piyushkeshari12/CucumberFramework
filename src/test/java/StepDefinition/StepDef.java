package StepDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.ReadConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

//Child class of Baseclass

public class StepDef extends BaseClass {

	@Before("@sanity")
	public void setup1() {

		readConfig = new ReadConfig();

		// initialise logger
		log = LogManager.getLogger("StepDef");

		System.out.println("Setup-sanity method executed...");

		String browser = readConfig.getBrowser();
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;
		}

		log.info("Setup1 executed...");
	}

	@Before("@regression")
	public void setup2() {
		System.out.println("Setup-regression method executed...");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

//	@BeforeStep
//	public void beforeStepMethodDemo() {
//		System.out.println("This is a beforestep.....");
//	}
//	
//	@AfterStep
//	public void afterStepMethodDemo() {
//		System.out.println("This is a afterstep.....");
//	}

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		addNewCustPg = new AddNewCustomerPage(driver);
		loginPg = new LoginPage(driver);
		searchCustPg = new SearchCustomerPage(driver);

		log.info("chrome browser launched");
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);

		log.info("url opened");
	}

	@And("User enters Email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String emailadd, String password) {
		loginPg.enterEmail(emailadd);
		loginPg.enterPassword(password);

		log.info("email address and password entered");
	}

	@And("Click on Login")
	public void click_on_login() {
		loginPg.clickOnLoginButton();

		log.info("clicked on login button");
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String expectedTitle) {
		String actualTitle = driver.getTitle();

		if (actualTitle.equals(expectedTitle)) {
			log.warn("Test Passed: Login feature: page title matched");

			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
			log.warn("Test Failed: Login feature: page title not matched");

		}
	}

	@When("user click on Logout link")
	public void user_click_on_logout_link() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginPg.clickOnLogoutButton();

		log.info("user clicked on logout link");
	}


	/////////////////////////// Add new customer/////////////////////

	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
		String actualTitle = addNewCustPg.getPageTitle();
		String expectedTitle = "Dashboard / nopCommerce administration";

		if (actualTitle.equals(expectedTitle)) {

			log.info("user can view dashboard test passed");
			Assert.assertTrue(true);

		} else {
			Assert.assertTrue(false);
			log.warn("user can view dashboard test failed");

		}
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() {
		addNewCustPg.clickOnCustomersMenu();
		log.info("customer menu clicked");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("click on customers Menu item")
	public void click_on_customers_menu_item() {
		addNewCustPg.clickOnCustomerMenuItem();
		log.info("customer menu item clicked");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@When("click on Add new button")
	public void click_on_add_new_button() {
		addNewCustPg.clickOnAddnew();
		log.info("clicked on add new button");

	}

	@Then("user can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		String actualTitle = addNewCustPg.getPageTitle();
		String expectedTitle = "Add a new customer / nopCommerce administration";

		if (actualTitle.equals(expectedTitle)) {
			log.info("user can view Add new customer page- passed");

			Assert.assertTrue(true);// pass
		} else {
			Assert.assertTrue(false);// fail
			log.info("user can view Add new customer page- passed");

		}
	}

	@When("user enter customer info")
	public void user_enter_customer_info() {
		addNewCustPg.enterEmail(generateEmailId() + "@gmail.com");
		addNewCustPg.enterPassword("test1");
		addNewCustPg.enterFirstName("Prachi");
		addNewCustPg.enterLastName("Gupta");
		addNewCustPg.enterGender("Female");
		addNewCustPg.enterDob("6/13/1988");
		addNewCustPg.enterCompanyName("CodeStudio");
		addNewCustPg.enterAdminContent("Admin content");
		addNewCustPg.enterManagerOfVendor("Vendor 1");

		log.info("customer information entered");
	}

	@When("click on Save button")
	public void click_on_save_button() {
		addNewCustPg.clickOnSave();

		log.info("save button clicked");
	}

	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String exptectedConfirmationMsg) {
		String bodyTag = driver.findElement(By.tagName("Body")).getText();
		if (bodyTag.contains(exptectedConfirmationMsg)) {
			log.info("user can view confirmation message- passed");
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
			log.info("user can view confirmation message- failed");

		}

	}

	///////////// Search Customer by Email////////////////

	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
		searchCustPg.enterEmailAdd("victoria_victoria@nopCommerce.com");
		log.info("customer email entered");
	}

	@When("Click on search button")
	public void click_on_search_button() {
		searchCustPg.clickOnSearchButton();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
		String expectedEmail = "victoria_victoria@nopCommerce.com";
		Assert.assertTrue(searchCustPg.searchCustomerByEmail(expectedEmail));
	}

	///////////// Search Customer by Name////////////////

	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		searchCustPg.enterFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		searchCustPg.enterLastName("Terces");
	}

	@Then("User should found name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		String expectedName = "Victoria Terces";
		if (searchCustPg.searchCustomerByName(expectedName) == true) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

//	@After
//	public void teardown(Scenario sc) {
//		System.out.println("Tear Down Method executed...");
//		if (sc.isFailed() == true) {
//			// Convert web driver object to TakeScreenshot
//
//			String fileWithPath = "C:\\Users\\PIYUSH\\eclipse-workspace\\CucumberFramework\\Screenshot\\failedScreenshot.png";
//			TakesScreenshot scrShot = ((TakesScreenshot) driver);
//
//			// Call getScreenshotAs method to create image file
//			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
//
//			// Move image file to new destination
//			File DestFile = new File(fileWithPath);
//
//			// Copy file at destination
//
//			try {
//				FileUtils.copyFile(SrcFile, DestFile);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		driver.quit();
//	}

}
