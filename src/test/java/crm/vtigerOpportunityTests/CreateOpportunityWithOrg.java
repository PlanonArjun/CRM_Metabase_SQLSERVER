package crm.vtigerOpportunityTests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import crm.objectRepository.HomePage;
import crm.objectRepository.OpportunityPage;
import crm.objectRepository.OrganizationsPage;
import crm.randomdataUtils.RandomUtilsImpliments;
import crm.utilities.BaseClass;

public class CreateOpportunityWithOrg extends BaseClass {

	@Test
	public void createOpportunityWithOrg() throws Exception {

		String opportunityName = RandomUtilsImpliments.generateOpportunityName();
		String campaingName = RandomUtilsImpliments.generateCampaignName();
		String description = RandomUtilsImpliments.generateDescription();
		String closeDate = RandomUtilsImpliments.getOpportunityCloseDate(7);

		String orgname = RandomUtilsImpliments.getCompanyName();
		String industryType = "Engineering";// can use data-provide for multipule data & run.

		// Selecting Dropdown value for the organization(Accounts) /contact
		String relatedToDrp = "Accounts";// Manuly passing the data we can pass from dropdown.

		Map<String, String> opportunityDetails = new HashMap<>();
		opportunityDetails.put("opportunityname", opportunityName);
		opportunityDetails.put("commonValue", orgname);
		opportunityDetails.put("relatedDrp", relatedToDrp);
		opportunityDetails.put("closeDate", closeDate);
		opportunityDetails.put("description", description);

		// * For closed date we have to pass number of days on
		// getOpportunityCloseDate(7)

		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationsLink();

		// Create Organization
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnLeadLookUpImage();
		op.createOrganizationWithIndustry(driver, orgname, industryType);
		Thread.sleep(4000);

		// Navigate to Opportunity link and Create Opportunity
		hp.clickOnOpportunitiesLink();
		System.out.println("Navigate to Opportunities Link & Click on Opportunities.");

		OpportunityPage opp = new OpportunityPage(driver);
		/*
		 * String opportunityPageHeader = opp.getOpportunitiesPageHeader();
		 * Assert.assertTrue(opportunityPageHeader.contains(opportunityPageHeader));
		 * System.out.println(opportunityPageHeader + "Its opportunityPageHeader ")
		 */;

		opp.clickOnOpportunityLookUpImage();
		System.out.println("Click on Opportunity image look up");
		String OpportunityFormTitel = opp.getOpportunitiesPageHeader();
		Assert.assertTrue(OpportunityFormTitel.contains(OpportunityFormTitel));
		System.out.println("Click on the Opportunity form to Create Opportunity");

		// Creating Opportunity with organization Name.
		opp.createOpportunityWithContactOrOrg(driver, opportunityDetails);
		System.out.println("Create Opportunity with Required data");
		String CretedOpportunity = opp.getCreatedOpportunityHeader();
		Assert.assertTrue(CretedOpportunity.contains(opportunityName));
		System.out.println("Creating Opportunity Name & Given Name is Same");

	}
}
