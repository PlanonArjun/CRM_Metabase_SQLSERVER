package crm.objectRepository;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import crm.webdriverActions.WebDriverUtility;

public class ContactsPage {

	@SuppressWarnings("unused")
	private WebDriverUtility webDriverUtility;

	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createContactImg;

	@FindBy(name = "firstname")
	private WebElement firstNameEdt;

	@FindBy(xpath = "//select[@name='salutationtype']")
	private WebElement firstNameDrp;

	@FindBy(name = "lastname")
	private WebElement lastNameEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement contactFormHeaderText;

	@FindBy(xpath = "//input[@name='account_name']/following-sibling::Img[@title='Select']")
	private WebElement orgLookUpImg;

	@FindBy(name = "search_text")
	private WebElement orgSearchEdt;

	@FindBy(name = "search")
	private WebElement orgSearchBtn;

	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Business Library
	public void clickOnCreateContactLookupImage() {
		createContactImg.click();
	}

	public void enterFirstName(String firstName) {
		firstNameEdt.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		lastNameEdt.sendKeys(lastName);
	}

	public void clickOnSaveButton() {
		saveBtn.click();
	}

	public void selectSalutation(String salutation) {
		WebDriverUtility.handleDropDown(firstNameDrp, salutation);
	}

	public void clickOnOrgLookupImage() {
		orgLookUpImg.click();
	}

	public void createContact(WebDriver driver, String firstname, String lastname) {
		WebDriverUtility.handleDropDown(firstNameDrp, "Mr.");
		firstNameEdt.sendKeys(firstname);
		lastNameEdt.sendKeys(lastname);
		saveBtn.click();
	}
/**
	public void createContactWithOrg(WebDriver driver, String firtname, String lastname, String orgname) {
		WebDriverUtility.handleDropDown(firstNameDrp, "Mr.");
		firstNameEdt.sendKeys(firtname);
		lastNameEdt.sendKeys(lastname);
		orgLookUpImg.click();
		WebDriverUtility.switchToWindow(driver, "Accounts");
		orgSearchEdt.sendKeys(orgname);
		orgSearchBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		driver.findElement(By.xpath("//a[.='" + orgname + "']")).click();

		WebDriverUtility.switchToWindow(driver, "Contacts");
		saveBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	**/
	/**
	 * This is optimized code with Map value to avoid Sonar issues.
	 * @param driver
	 * @param contactDetails
	 */
	public void createContactWithOrg(WebDriver driver, Map<String, String> contactDetails) {

		String firstname = contactDetails.get("firstname");
		String lastname = contactDetails.get("lastname");
		String orgname = contactDetails.get("orgname");

		WebDriverUtility.handleDropDown(firstNameDrp, "Mr.");
		firstNameEdt.sendKeys(firstname);
		lastNameEdt.sendKeys(lastname);
		orgLookUpImg.click();
		WebDriverUtility.switchToWindow(driver, "Accounts");
		orgSearchEdt.sendKeys(orgname);
		orgSearchBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		driver.findElement(By.xpath("//a[.='" + orgname + "']")).click();

		WebDriverUtility.switchToWindow(driver, "Contacts");
		saveBtn.click();
		driver.navigate().refresh();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	public String getContactFormHeader() {
		return contactFormHeaderText.getText();
	}
}
