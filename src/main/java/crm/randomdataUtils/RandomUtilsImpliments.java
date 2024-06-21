package crm.randomdataUtils;


public class RandomUtilsImpliments {
	// business layer --> all the business level changes

	private RandomUtilsImpliments() {
	}

	public static int getId() {
		return FakerJavaImplements.getNumber(1000, 2000);
	}

	public static String getFirstName() {
		return FakerJavaImplements.getFirstName().toLowerCase();
	}

	public static String getLastName() {
		return FakerJavaImplements.getLastName().toLowerCase();
	}

	public static String getDate() {
		return FakerJavaImplements.getDates();
	}

	public static String getCompanyName() {
		return FakerJavaImplements.getCompanyName();
	}

	public static String getPrice() {
		double priceDouble = Math.random() * 1000 + 100; // Generate a random price between 100 and 1100
		int priceCents = (int) (priceDouble * 100); // Convert to cents
		double formattedPrice = priceCents / 100.0; // Convert back to double with two decimal places
		return String.format("%.2f", formattedPrice);
	}

	public static String getfoodItem() {
		return FakerJavaImplements.getFoodItem();
	}

	public static String getCheckinDate() {
		return FakerJavaImplements.getCheckinDate();
	}

	public static String getCheckoutDate() {
		return FakerJavaImplements.getCheckoutDate(2);
	}

	public static String getOpportunityCloseDate(int daysInFuture) {
		return FakerJavaImplements.getCheckoutDate(daysInFuture);
	}

	public static String getProductName() {
		return FakerJavaImplements.getProduct();
	}

	public static String getCommissionRate() {
		return FakerJavaImplements.getCommissionRate();
	}

	public static String generateDescription() {
		return FakerJavaImplements.generateDescription();
	}

	public static String generateOpportunityName() {
		return FakerJavaImplements.generateOpportunityName();
	}

	public static String generateCampaignName() {
		return FakerJavaImplements.generateCampaignName();
	}

	public static String generateWebsite() {
		return FakerJavaImplements.websiteGenerator();
	}

	public static String generateMobileNumber() {
		return FakerJavaImplements.generateMobileNumber();
	}

	public static String generateEmail() {
		return FakerJavaImplements.generateEmail();
	}

	public static String generateAddress() {
		return FakerJavaImplements.generateAddress();
	}

	public static String generatorCategory() {
		return FakerJavaImplements.generatorCategory();
	}

}
