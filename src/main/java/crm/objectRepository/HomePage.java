package crm.objectRepository;


import java.util.NoSuchElementException;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import crm.webdriverActions.WebDriverUtility;

public class HomePage  {

	 @SuppressWarnings("unused")
	private WebDriverUtility webDriverUtility;
	
	@FindBy(xpath = "//td[@class='moduleName']")
	private WebElement homepageHeader;

	@FindBy(linkText = "Leads")
	private WebElement leadsLink;

	@FindBy(linkText = "Leads")
	private WebElement leadPageTitle;

	@FindBy(linkText = "Organizations")
	private WebElement organizationsLink;

	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;

	@FindBy(linkText = "Opportunities")
	private WebElement opportunitiesLink;

	@FindBy(linkText = "Products")
	private WebElement productsLink;

	@FindBy(linkText = "More")
	private WebElement moreLink;

	@FindBy(name = "Campaigns")
	private WebElement campaignsLink;

	@FindBy(name = "Vendors")
	private WebElement vendorsLink;

	@FindAll({ @FindBy(linkText = "Sign Out"), @FindBy(xpath = "//*[text()='Sign Out']") })
	private WebElement signOutLink;

	@FindAll({ // @FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']"),
			@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']/..") })
	private WebElement administratorImg;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Business Library
	public void clickOnLeadsLink() {
		leadsLink.click();
	}

	public void clickOnOrganizationsLink() {
		organizationsLink.click();
	}

	public void clickOnContactsLink() {
		contactsLink.click();
	}

	public void clickOnOpportunitiesLink() {
		opportunitiesLink.click();
	}

	public void clickOnProductsLink() {
		productsLink.click();
	}

	public void clickOnMoreLink() {
		moreLink.click();
	}

	public void clickOnCampaignsLink(WebDriver driver) {
		moreLink.click();
		WebDriverUtility.waitForElementToBeVisisble(driver, campaignsLink);
		campaignsLink.click();
	}

	public void clickOnVendorsLink(WebDriver driver) {
		moreLink.click();
		WebDriverUtility.waitForElementToBeVisisble(driver, vendorsLink);
		vendorsLink.click();
	}

	public void logOutOfApp(WebDriver driver) {
		
		WebDriverUtility.mouseHoverAction(driver, administratorImg);

		Boolean actualElement = true;
		while (Boolean.TRUE.equals(actualElement)) {
			try {
				WebDriverUtility.waitForElementToBeVisisble(driver, signOutLink);
				signOutLink.click();
				actualElement = false;
			} catch (NoSuchElementException | ElementNotInteractableException e) {
				actualElement = true;
			}
		}
	}
	
}
