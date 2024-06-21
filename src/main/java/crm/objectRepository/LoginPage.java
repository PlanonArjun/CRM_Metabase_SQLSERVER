package crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindAll;
import org.testng.Assert;

/*
 * @Epic("Web Application Regression Testing")
 * 
 * @Feature("Login Page Tests")
 */

public class LoginPage { // rule 1: create class

	// Rule 2: identify the element using annotations
	@FindBy(name = "user_name")
	private WebElement userNameEdt;

	@FindBy(name = "user_password")
	private WebElement passwordEdt;

	@FindAll({ @FindBy(id = "submitButton"), @FindBy(xpath = "//input[@type='submit']") })
	private WebElement loginBtn;

	@FindBy(xpath = "//span[@class='userName']")
	private WebElement loginUserName;

	// Rule 3: Create a constructor to initiliaze
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Business Libraries - project specific generic utilities
	/**
	 * This method will login to application
	 * 
	 * @param USERNAME
	 * @param PASSWORD
	 */

	/*
	 * @Severity(SeverityLevel.BLOCKER)
	 * 
	 * @Description("Test Description : After successful login to application opens Home page"
	 * )
	 * 
	 * @Story("Successful login of application opens Dashboard Page")
	 */
	public void loginToApp(String username, String password) {
		userNameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();

		String userName = loginUserName.getText();
		String actualName = "Administrator";
		Assert.assertTrue(userName.contains(actualName));
		System.out.println("User login as Admin: " + userName);

	}
}
