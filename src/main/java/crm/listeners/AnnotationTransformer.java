package crm.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;



public class AnnotationTransformer implements IAnnotationTransformer {

	/**
	 * Helps in setting the dataprovider, dataprovider class and retry analyser
	 * com.vt.annotation to all the test methods at run time.
	 */
	@Override
	public void transform(ITestAnnotation annotation, @SuppressWarnings("rawtypes") Class testClass,
			@SuppressWarnings("rawtypes") Constructor testConstructor, Method testMethod) {
//		com.vt.annotation.setDataProvider("getData");
//		com.vt.annotation.setDataProviderClass(DataProviderUtils.class);
		annotation.setRetryAnalyzer(RetryAnalyzerImpl.class);

	}

}
