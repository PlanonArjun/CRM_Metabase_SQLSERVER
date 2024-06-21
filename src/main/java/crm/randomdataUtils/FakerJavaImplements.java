package crm.randomdataUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.github.javafaker.Faker;



public final class FakerJavaImplements {

	private FakerJavaImplements() {
	}

	private static final Faker faker = new Faker();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final int MAX_SENTENCES = 3;

	static int getNumber(int startvalue, int endvalue) {
		return faker.number().numberBetween(startvalue, endvalue);
	}

	static String getFirstName() {
		return faker.name().firstName();
	}

	static String getLastName() {
		return faker.name().lastName();
	}

	static String getCompanyName() {

		String companyNameWithSpecialChars = faker.company().name();
		return companyNameWithSpecialChars.replaceAll("[^a-zA-Z\\s-]", "");
	}

	static String getDates() {
		Date date = faker.date().birthday(); // Or any other method to generate a date
		return dateFormat.format(date);
	}

	static String getPrice() {
		return faker.commerce().price(0, 1000);// 123.00 //1000.00
	}

	static String getFoodItem() {
		return faker.food().dish();
	}

	static String getCheckinDate() {
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	static String getCheckoutDate(int daysInFuture) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, daysInFuture);
		Date checkoutDate = calendar.getTime();
		return dateFormat.format(checkoutDate);
	}

	static String getProduct() {
		return faker.commerce().productName();
	}

	static String getCommissionRate() {
		// Generate a random double between 0 and 100 with 2 decimal places
		double commissionRate = faker.number().randomDouble(2, 0, 100);
		// Format the commission rate with 2 decimal places, without the percentage
		// symbol
		return String.format("%.2f", commissionRate);
	}

	static String generateDescription() {
		StringBuilder description = new StringBuilder();
		for (int i = 0; i < MAX_SENTENCES; i++) {
			description.append(faker.lorem().sentence()).append(". ");
		}
		return description.toString().trim();
	}

	static String generateOpportunityName() {
		return faker.company().name();
	}

	static String generateCampaignName() {
		return faker.company().industry();
	}

	static String websiteGenerator() {
		return "http://" + faker.internet().domainName();
	}

	static String generateMobileNumber() {
		return faker.phoneNumber().cellPhone();
	}

	static String generateEmail() {
		return faker.internet().emailAddress();
	}

	static String generateAddress() {
		return faker.address().fullAddress();
	}

	static String generatorCategory() {
		return faker.commerce().department();
	}

}
