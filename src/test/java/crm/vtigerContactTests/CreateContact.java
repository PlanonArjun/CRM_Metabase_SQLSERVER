package crm.vtigerContactTests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import crm.annotation.FrameworkAnnotation;
import crm.enums.CategoryType;
import crm.listeners.ListenerClass;
import crm.objectRepository.ContactsPage;
import crm.objectRepository.HomePage;
import crm.randomdataUtils.RandomUtilsImpliments;
import crm.utilities.BaseClass;

@Listeners(ListenerClass.class)
public class CreateContact extends BaseClass {

	@FrameworkAnnotation(author = { "Ansuman" }, category = { CategoryType.SMOKE, CategoryType.SANITY })
	@Test(groups = {"SMOKE","SANITY"})
	public void createContactTest() throws Exception {

		String firstName = RandomUtilsImpliments.getFirstName();
		String lastname = RandomUtilsImpliments.getLastName();

		HomePage hp = new HomePage(driver);
		hp.clickOnContactsLink();

		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookupImage();

		cp.createContact(driver, firstName, lastname);

		System.out.println("Name : " + lastname);

		String contactHeader = cp.getContactFormHeader();
		System.out.println(contactHeader);
		Assert.assertTrue(contactHeader.contains(lastname));
		System.out.println("Contact Header Matched : " + lastname);

	}
}
