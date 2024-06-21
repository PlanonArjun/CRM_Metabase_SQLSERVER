package crm.listeners;

import java.util.ArrayList;
import java.util.List;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

public class MethodInterceptor implements IMethodInterceptor {

	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
		List<IMethodInstance> result = new ArrayList<>();

		// Check if an XML suite is present
		XmlSuite xmlSuite = context.getSuite().getXmlSuite();

		if (xmlSuite != null) {
			// If XML suite is present, read test details from XML parameters
			result.addAll(interceptFromXmlSuite(methods, xmlSuite));
		} else {
			// If no XML suite, run the methods as is
			result.addAll(methods);
		}

		return result;
	}

	private List<IMethodInstance> interceptFromXmlSuite(List<IMethodInstance> methods, XmlSuite xmlSuite) {
		List<IMethodInstance> result = new ArrayList<>();

		for (IMethodInstance method : methods) {
			String methodName = method.getMethod().getMethodName();

			// Assuming you have parameters in your XML suite
			String executeParam = xmlSuite.getParameter(methodName + ".execute");

			if (executeParam != null && executeParam.equalsIgnoreCase("NO")) {
				// Assuming you have other parameters as well
				String description = xmlSuite.getParameter(methodName + ".testdescription");
				String count = xmlSuite.getParameter(methodName + ".count");
				String priority = xmlSuite.getParameter(methodName + ".priority");

				method.getMethod().setDescription(description);
				method.getMethod().setInvocationCount(Integer.parseInt(count));
				method.getMethod().setPriority(Integer.parseInt(priority));
				method.getMethod().getGroups();

				result.add(method);
			}
		}
		return result;
	}
}
