package crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import crm.webdriverActions.WebDriverUtility;

public class OrganizationsPage {

	@SuppressWarnings("unused")
	private WebDriverUtility webDriverUtility;

	@FindBy(xpath = "//*[contains(@alt,'Create Organization...')]") // img[@alt='Create Organization...']
	private WebElement createOrgLookUpImg;

	@FindBy(name = "accountname")
	private WebElement orgNameEdt;

	@FindBy(xpath = "//*[contains(@name, 'industry')]")
	private WebElement industryDropDwn;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	// @FindBy(xpath = "//*[contains(@class,'dvHeaderText')]")
	@FindBy(css = "[class='dvHeaderText']")
	private WebElement orgFromHeaderText;

	// initializations
	public OrganizationsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Business Library
	public void clickOnLeadLookUpImage() {
		createOrgLookUpImg.click();
	}

	public WebElement getCreatedOrgHeaderText() {
		return orgFromHeaderText;
	}

	public void createOrganization(WebDriver driver, String orgname) {
		orgNameEdt.sendKeys(orgname);
		saveBtn.click();
		WebDriverUtility.fluentWait(driver, orgFromHeaderText);

	}

	public void createOrganizationWithIndustry(WebDriver driver, String orgname, String industryType) {
		createOrgLookUpImg.click();
		orgNameEdt.sendKeys(orgname);
		WebDriverUtility.handleDropDown(industryDropDwn, industryType);
		saveBtn.click();
		WebDriverUtility.fluentWait(driver, orgFromHeaderText);

	}

	public String getHeader() {
		return getCreatedOrgHeaderText().getText();
	}

}
