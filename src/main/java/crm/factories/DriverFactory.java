package crm.factories;

import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;

import crm.enums.ConfigProperties;
import crm.utilities.PropertyUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public final class DriverFactory {

	private DriverFactory() {
	}

	public static String browserVersion = "";
	public static String browserName = "";

	@Parameters("browser")
	public static WebDriver getDriver(String browser, String version) throws Exception {

		WebDriver driver = null;
		Capabilities caps = null;

		String runmode = PropertyUtils.get(ConfigProperties.RUNMODE);
		if (browser.equalsIgnoreCase("chrome")) {
			if (runmode.equalsIgnoreCase("remote")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				options.addArguments("--start-maximized");
				options.setAcceptInsecureCerts(true);
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);

				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				options.merge(cap);
				driver = new RemoteWebDriver(new URL(PropertyUtils.get(ConfigProperties.SELENIUMGRIDURL)), cap);

			} else {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				options.addArguments("--start-maximized");
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);
				driver = new ChromeDriver(options);
				caps = ((RemoteWebDriver) driver).getCapabilities();
				browserVersion = caps.getBrowserVersion();
				browserName = caps.getBrowserName();
				System.out.println("------" + browserName);

			}
		} else if (browser.equalsIgnoreCase("firefox")) {
			if (runmode.equalsIgnoreCase("remote")) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--start-maximized");
				options.addArguments("-private");
				options.setAcceptInsecureCerts(true);
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);

				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
				cap.getBrowserVersion();
				driver = new RemoteWebDriver(new URL(PropertyUtils.get(ConfigProperties.SELENIUMGRIDURL)), cap);
			} else {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--incognito");
				options.addArguments("--start-maximized");
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);
				driver = new FirefoxDriver();
				caps = ((RemoteWebDriver) driver).getCapabilities();
				browserVersion = caps.getBrowserVersion();
				browserName = caps.getBrowserName();
				System.out.println("------" + browserName);

			}
		} else if (browser.equalsIgnoreCase("edge")) {
			if (runmode.equalsIgnoreCase("remote")) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--start-maximized");
				options.addArguments("-private");
				options.setAcceptInsecureCerts(true);
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);

				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(EdgeOptions.CAPABILITY, options);
				cap.getBrowserVersion();
				driver = new RemoteWebDriver(new URL(PropertyUtils.get(ConfigProperties.SELENIUMGRIDURL)), cap);
			} else {
				WebDriverManager.edgedriver().setup();
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--incognito");
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);
				driver = new EdgeDriver();
				caps = ((RemoteWebDriver) driver).getCapabilities();
				browserVersion = caps.getBrowserVersion();
				browserName = caps.getBrowserName();
				System.out.println("------" + browserName);

			}
		}
		return driver;
	}
}
