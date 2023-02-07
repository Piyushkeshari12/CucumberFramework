package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage {

	WebDriver ldriver;
	
	public SearchCustomerPage(WebDriver rDriver) {
		ldriver = rDriver;
		PageFactory.initElements(rDriver, this);
	}
	
	// Find web elements on the web page
	@FindBy(id="SearchEmail")
	WebElement emailAdd;
	
	@FindBy(id="search-customers")
	WebElement searchBtn;
	
	@FindBy(xpath="//table[@xpath='1']")
	WebElement searchResult;

	@FindBy(xpath="//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows;
	
	@FindBy(id="SearchFirstName")
	WebElement firstName;
	
	@FindBy(id="SearchLastName")
	WebElement LastName;


	/*@FindBy(xpath="//table[@id='customers-grid']//tbody/tr[1]/td")
	List<WebElement> tableColumns;*/
	
	
	//action method to enter email address
	public void enterEmailAdd(String email) {
		emailAdd.sendKeys(email);
	}
	
	//action method to perform click action on search button
	public void clickOnSearchButton() {
		searchBtn.click();
	}
	
	public boolean searchCustomerByEmail(String email) {
		boolean found = false;

		//total no. of rows in a grid
		int ttlRows = tableRows.size();


		//total no. of columns
		//int ttlColumns = tableColumns.size();

		for(int i=1;i<=ttlRows;i++)//to iterate all the rows of the grid
		{
			System.out.println("Searching row:" +i );

			WebElement webElementEmail = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i  + "]/td[2]"));
			String actualEmailAdd = webElementEmail.getText();
			System.out.println(actualEmailAdd);

			if(actualEmailAdd.equals(email))
			{
				found=true;
			}


		}

		return found;

	}
	
	//action method to enter FirstName
	public void enterFirstName(String firstNameText) {
		firstName.sendKeys(firstNameText);
	}

	//action method to enter LastName
	public void enterLastName(String lastNameText) {
		LastName.sendKeys(lastNameText);
	}
	
	public boolean searchCustomerByName(String name) {
		boolean found = false;

		//total no. of rows in a grid
		int ttlRows = tableRows.size();

		for(int i=1;i<=ttlRows;i++)//to iterate all the rows of the grid
		{

			WebElement webElementName = ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr[" + i  + "]/td[3]"));
			String actualName = webElementName.getText();
			
			if(actualName.equals(name))
			{
				found=true;
				break;
			}
		}

		return found;

	}


}
