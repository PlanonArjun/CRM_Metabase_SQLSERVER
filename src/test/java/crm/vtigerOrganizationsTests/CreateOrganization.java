package crm.vtigerOrganizationsTests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import crm.annotation.FrameworkAnnotation;
import crm.enums.CategoryType;
import crm.listeners.ListenerClass;
import crm.objectRepository.HomePage;
import crm.objectRepository.OrganizationsPage;
import crm.randomdataUtils.RandomUtilsImpliments;
import crm.utilities.BaseClass;

@Listeners(ListenerClass.class)
public class CreateOrganization extends BaseClass {

	@FrameworkAnnotation(author = { "Ansuman" }, category = { CategoryType.SMOKE, CategoryType.REGRESSION })
	@Test(groups = {"SMOKE","REGRESSION"})
	public void createOrganizationTest() throws Exception {
		String organizationName = RandomUtilsImpliments.getCompanyName();

		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationsLink();

		OrganizationsPage op = new OrganizationsPage(driver);

		op.clickOnLeadLookUpImage();
		op.createOrganization(driver, organizationName);

		String orgHeader = op.getHeader();
		Assert.assertTrue(orgHeader.contains(organizationName));
		System.out.println("Orgabization Name Matched : " + orgHeader);

	}

}
