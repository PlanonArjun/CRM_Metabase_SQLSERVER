package crm.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Implements {@link IRetryAnalyzer}. Helps in rerunning the failed tests.
 */
public class RetryAnalyzerImpl implements IRetryAnalyzer {
	private int count = 0;
	private static int maxRetry = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (!result.isSuccess()) {
			if (count < maxRetry) {
				count++;
				result.setStatus(ITestResult.FAILURE);
				return true;
			} else {
				result.setStatus(ITestResult.FAILURE);
			}

		} else {
			result.setStatus(ITestResult.SUCCESS);
		}
		return false;
	}
}
