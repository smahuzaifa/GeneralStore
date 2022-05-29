import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class FillingForm extends DesiredCapabilitesClass {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		AndroidDriver<AndroidElement> driver = capabilities();
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElementByAndroidUIAutomator("text(\"Enter name here\")").sendKeys("ABCD");
		driver.hideKeyboard();
		driver.findElementByAndroidUIAutomator("text(\"Female\")").click();
		driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
		/*
		 * If above step is not working (does not work on few systems)
		 * then we can use,
		 *  driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()
		 *  .scrollable(true).instance(0)).scrollIntoView(new UiSelector()
		 *  .textMatches(\"" + containedText + "\").instance(0))"));   
		 */
		driver.findElementByAndroidUIAutomator("text(\"Argentina\")").click();
		driver.findElementByAndroidUIAutomator("text(\"Let's  Shop\")").click();
		
	}

}
