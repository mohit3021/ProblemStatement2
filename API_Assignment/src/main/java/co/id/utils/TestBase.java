package co.id.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.CapabilityType.ForSeleniumServer;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class TestBase {

	public static WebDriver driver;
	static ChromeOptions options = new ChromeOptions();

	public static WebDriver getWebDriver() {
		if (driver == null) {
			String browsername = ConfigurationFunction.getProperty("browser");
			if (browsername.equalsIgnoreCase("remotewebdriver")) {
				
				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				options.addArguments("disable-infobars");
				// options.addArguments("start-maximized");
				URL url = null;
				try {
					url = new URL("http://acn.selenium-hub.apps.ocp2.azlife.allianz.co.id/wd/hub");
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				driver = new RemoteWebDriver(url, options);

			} 
			else if (browsername.equalsIgnoreCase("headlesschrome")) {
				// options.addArguments("window-size=1200x600");
				System.setProperty("webdriver.chrome.silentOutput", "true");
				System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				options.setHeadless(true);
				driver = new ChromeDriver(options);
			}else if (browsername.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.silentOutput", "true");
				System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
				options.addArguments("start-maximized");
				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				options.setProxy(null);
				driver = new ChromeDriver(options);
			} else if (browsername.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
				driver = new FirefoxDriver(options);
			} else if (browsername.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver(options);

			} else {
				System.setProperty("webdriver.chrome.silentOutput", "true");
				System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
				options.addArguments("start-maximized");
				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				driver = new ChromeDriver(options);
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		return driver;
	}

	public static WebDriver setWebDriver() {
		return driver = null;
	}

}
