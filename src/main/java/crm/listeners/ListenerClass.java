package crm.listeners;

import org.testng.*;

import crm.ELKUtils.Dashboard_Metabase_SqlServer;
import crm.ELKUtils.ELKUtils;
import crm.ELKUtils.MetabaseUtils;
import crm.annotation.FrameworkAnnotation;
import crm.enums.CategoryType;
import crm.factories.DriverFactory;
import crm.reports.ExtentReport;

import static crm.enums.LogType.*;
import static crm.reports.FrameworkLogger.log;

/**
 * Implements {@link org.testng.ITestListener} and
 * {@link org.testng.ISuiteListener} to leverage the abstract methods Mostly
 * used to help in extent report generation
 * 
 * <pre>
 * Please make sure to add the listener details in the testng.xml file
 * </pre>
 * 
 * @author Ansuman
 */
public class ListenerClass implements ITestListener, ISuiteListener {

	/**
	 * Initialise the reports with the file name
	 * 
	 * @see com.tmb.reports.ExtentReport
	 */
	@Override
	public void onStart(ISuite suite) {
		try {
			ExtentReport.initReports();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Terminate the reports
	 * 
	 * @see com.tmb.reports.ExtentReport
	 */
	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushReports();

	}

	/**
	 * Starts a test node for each testng test
	 * 
	 * @see com.tmb.reports.ExtentReport
	 * @see com.tmb.annotations.FrameworkAnnotation
	 */
	@Override
	public void onTestStart(ITestResult result) {

		if (result.getMethod().getDescription().equals("")) {
			ExtentReport.createTest(result.getMethod().getMethodName());
		} else {
			ExtentReport.createTest(result.getMethod().getDescription());
		}

		// ExtentReport.createTest(result.getMethod().getDescription());
		/*
		 * ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod
		 * ().getAnnotation(FrameworkAnnotation.class) .author());
		 * ExtentReport.addCategories(result.getMethod().getConstructorOrMethod().
		 * getMethod().getAnnotation(FrameworkAnnotation.class) .category());
		 */

		FrameworkAnnotation authoreName = result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class);
		FrameworkAnnotation categoriesName = result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class);

		String[] author = null;
		CategoryType[] category = null;
		String[] device = null;
		String os = System.getProperty("os.name");
		if (authoreName != null && categoriesName != null) {
			ExtentReport.addAuthors(authoreName.author());
			ExtentReport.addCategories(categoriesName.category());
			device = new String[] { (os + "--" + DriverFactory.browserName + "-" + DriverFactory.browserVersion) };
			ExtentReport.addDevices(device);
		} else {
			author = new String[] { "Arjun", "Ansumans" };
			category = new CategoryType[] { CategoryType.SMOKE, CategoryType.MINIREGRESSION };
			device = new String[] { (os + "-" + DriverFactory.browserName + "-" + DriverFactory.browserVersion) };
			ExtentReport.addAuthors(author);
			ExtentReport.addCategories(category);
			ExtentReport.addDevices(device);
		}
	}

	/**
	 * Marks the test as pass and logs it in the report
	 * 
	 * @see com.tmb.reports.FrameworkLogger
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		// ExtentLogger.pass(result.getMethod().getMethodName() +" is passed");
		log(PASS, result.getMethod().getMethodName() + " is passed");
		ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "pass");
		
		try {
			Dashboard_Metabase_SqlServer.sendDetailsToMetabase(result.getMethod().getMethodName(), "pass");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	/**
	 * Marks the test as fail,append base64 screenshot and logs it in the report
	 * 
	 * @see com.tmb.reports.FrameworkLogger
	 * @see com.tmb.utils.ScreenshotUtils
	 */
	@Override
	public void onTestFailure(ITestResult result) {

		log(FAIL, result.getMethod().getMethodName() + " is failed");
		log(INFO, result.getThrowable().toString());
		//log(INFO, Arrays.toString(result.getThrowable().getStackTrace()));

		ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "fail");
		
		try {
			Dashboard_Metabase_SqlServer.sendDetailsToMetabase(result.getMethod().getMethodName(), "fail");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Marks the test as skip and logs it in the report
	 * 
	 * @see com.tmb.reports.FrameworkLogger
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		// ExtentLogger.skip(result.getMethod().getMethodName() +" is skipped");
		log(SKIP, result.getMethod().getMethodName() + " is skipped");
		log(INFO, result.getThrowable().toString());

		ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "skip");
		
		try {	
			Dashboard_Metabase_SqlServer.sendDetailsToMetabase(result.getMethod().getMethodName(), "skip");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		/*
		 * For now, we are not using this.
		 */
	}

	@Override
	public void onStart(ITestContext context) {
		/*
		 * We are having just one test in our suite. So we dont have any special
		 * implementation as of now
		 */
	}

	@Override
	public void onFinish(ITestContext context) {
		/*
		 * We are having just one test in our suite. So we dont have any special
		 * implementation as of now
		 */

	}

}
