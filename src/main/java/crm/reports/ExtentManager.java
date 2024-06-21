package crm.reports;

import java.util.Objects;

import com.aventstack.extentreports.ExtentTest;

/**
 * ExtentManager
 * 
 * @author Ansuman
 * @see Driver
 */
public class ExtentManager {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private ExtentManager() {
	}

	private static ThreadLocal<ExtentTest> extTest = new ThreadLocal<>();

	/**
	 * Returns the thread safe {@link com.aventstack.extentreports.ExtentTest}
	 * instance fetched from ThreadLocal variable.
	 * 
	 * @author Ansuman
	 * @return Thread safe {@link com.aventstack.extentreports.ExtentTest} instance.
	 */
	static ExtentTest getExtentTest() {
		return extTest.get();
	}

	/**
	 * Set the {@link com.aventstack.extentreports.ExtentTest} instance to thread
	 * local variable
	 * 
	 * @author Ansuman
	 * @param test {@link com.aventstack.extentreports.ExtentTest} instance that
	 *             needs to saved from Thread safety issues.
	 *             <p>
	 */
	static void setExtentTest(ExtentTest test) {
		if (Objects.nonNull(test)) {
			extTest.set(test);
		}
	}

	/**
	 * Calling remove method on Threadlocal variable ensures to set the default
	 * value to Threadlocal variable. It is much safer than assigning null value to
	 * ThreadLocal variable.
	 * 
	 * @author Ansuman
	 */
	static void unload() {
		extTest.remove();
	}
}
