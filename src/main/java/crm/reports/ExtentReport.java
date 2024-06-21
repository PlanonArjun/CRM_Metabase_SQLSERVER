package crm.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import crm.constants.FrameworkConstants;
import crm.enums.CategoryType;

/**
 * Perform initialisation and termination of
 * {@link com.aventstack.extentreports.ExtentReports} After creating an instance
 * for {@link com.aventstack.extentreports.ExtentTest}, it is delegated to
 * ThreadLocal variable for providing thread safety.
 * 
 * @author Ansuman
 */
public final class ExtentReport {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private ExtentReport() {
	}

	private static ExtentReports extent;

	/**
	 * Set the initial configuration for the Extent Reports and decides the report
	 * generation path.
	 * 
	 * @author Ansuman Jan 22, 2021
	 * @throws Exception
	 */
	public static void initReports() {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
			extent.attachReporter(spark);
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("Automation Reports");
			spark.config().setReportName("Vitiger Application");
		}
	}

	/**
	 * Flushing the reports ensures extent logs are reflected properly. Opens the
	 * report in the default desktop browser. Sets the ThreadLocal variable to
	 * default value
	 * 
	 * @author Ansuman Jan 22, 2021
	 * @throws Exception
	 */
	public static void flushReports() {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}
		ExtentManager.unload();
		try {
			Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a test node in the extent report. Delegates to {@link ExtentManager}
	 * for providing thread safety
	 * 
	 * @author Ansuman
	 * @param testcasename Test Name that needs to be reflected in the report
	 */
	public static void createTest(String testcasename) {
		ExtentManager.setExtentTest(extent.createTest(testcasename));
	}

	/**
	 * Logs the authors details in the authors view in the extent report. Gives an
	 * clear idea of Authors Vs Percentage success metrics
	 * 
	 * @author Ansuman
	 * @param authors Authors who created a particular test case
	 */
	public static void addAuthors(String[] authors) {
		for (String temp : authors) {
			ExtentManager.getExtentTest().assignAuthor(temp);
		}
	}

	/**
	 * Adds the category a particular test case belongs to. Gives an clear idea of
	 * Group Vs Percentage success metrics.
	 * 
	 * @author Ansuman
	 * @param categories category a particular test case belongs to.
	 */
	public static void addCategories(CategoryType[] categories) {
		for (CategoryType temp : categories) {
			ExtentManager.getExtentTest().assignCategory(temp.toString());
		}
	}

	/**
	 * Adds the Devices a particular test case belongs to. Gives an clear idea of
	 * Group Vs Percentage success metrics.
	 * 
	 * @author Ansuman
	 * @param Devices Device(Os name & Browser version) a particular test case
	 *                belongs to.
	 */
	public static void addDevices(String[] devices) {
		for (String temp : devices) {
			ExtentManager.getExtentTest().assignDevice(temp);
		}
	}

}
