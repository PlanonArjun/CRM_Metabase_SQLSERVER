package crm.vtigerOrganizationsTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import crm.annotation.FrameworkAnnotation;
import crm.enums.CategoryType;
import crm.listeners.ListenerClass;
import crm.objectRepository.HomePage;
import crm.objectRepository.OrganizationsPage;
import crm.randomdataUtils.RandomUtilsImpliments;
import crm.utilities.BaseClass;
import crm.utilities.ExcelUtils;

@Listeners(ListenerClass.class)
public class CreateMultipleOrganizationWithInd extends BaseClass {

	@FrameworkAnnotation(author = { "Ansuman" }, category = { CategoryType.REGRESSION })
	@Test(dataProvider = "getData",invocationCount = 1)
	public void createOrgWithIndTest(String industryType) throws Exception {

		String orgname = RandomUtilsImpliments.getCompanyName();

		/*
		 * String industryType = "Engineering";// can use data-provide for multipule
		 * data & run.
		 */
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationsLink();

		OrganizationsPage op = new OrganizationsPage(driver);

		op.clickOnLeadLookUpImage();

		op.createOrganizationWithIndustry(driver, orgname, industryType);

		String orgHeader = op.getHeader();
		Assert.assertTrue(orgHeader.contains(orgname));

	}

	@DataProvider(parallel = false)
	public Object[][] getData() {
		return ExcelUtils.readMultipleData("IndustryType");
	}

}
