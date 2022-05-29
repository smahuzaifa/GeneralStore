import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class DesiredCapabilitesClass {

	public static AndroidDriver<AndroidElement> capabilities() throws MalformedURLException
	{
		File appDir = new File("src");
		File app = new File(appDir,"General-Store.apk");
		DesiredCapabilities cap =new DesiredCapabilities();
		
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 5");
		//cap.setCapability(MobileCapabilityType.DEVICE_NAME, "OnePlus ONEPLUS A6000");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		//Adding timeout to desired capabilites
		//Below appium will wait for 14 seconds
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10);
		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

}
