package crm.vtigerContactTests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import crm.annotation.FrameworkAnnotation;
import crm.enums.CategoryType;
import crm.listeners.ListenerClass;
import crm.objectRepository.ContactsPage;
import crm.objectRepository.HomePage;
import crm.objectRepository.OrganizationsPage;
import crm.randomdataUtils.RandomUtilsImpliments;
import crm.utilities.BaseClass;

@Listeners(ListenerClass.class)
public class CreateContactWithOrganization extends BaseClass {

	@FrameworkAnnotation(author = { "Ansuman" }, category = { CategoryType.SMOKE, CategoryType.REGRESSION })
	@Test(groups = { "SMOKE", "REGRESSION" })
	public void createContactWithOrganizationTest() {

		String industryType = "Engineering";// can use data-provide for multiple data & run.

		String firstname = RandomUtilsImpliments.getFirstName();
		String lastname = RandomUtilsImpliments.getLastName();
		String orgname = RandomUtilsImpliments.getCompanyName();

		Map<String, String> contactDetails = new HashMap<>();
		contactDetails.put("firstname", firstname);
		contactDetails.put("lastname", lastname);
		contactDetails.put("orgname", orgname);

		/*
		 * First we are creating organization based on that we will add contact.
		 */
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationsLink();

		OrganizationsPage op = new OrganizationsPage(driver);

		op.clickOnLeadLookUpImage();

		op.createOrganizationWithIndustry(driver, orgname, industryType);

		// Contact creation based on the organisation
		hp.clickOnContactsLink();

		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookupImage();

		// cp.createContactWithOrg(driver, firstName, lastname, orgname);
		cp.createContactWithOrg(driver, contactDetails);

		System.out.println("Name : " + lastname);

		// Validate
		String contactHeader = cp.getContactFormHeader();
		System.out.println(contactHeader);
		Assert.assertTrue(contactHeader.contains(lastname));
		System.out.println("Contact Header Matched : " + lastname);
		// wUtil.takeScreenShot(driver, contactHeader);

	}

}
