import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class FillingFormWithToastMessage extends DesiredCapabilitiesClass{
	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		AndroidDriver<AndroidElement> driver = capabilities();
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//driver.findElementByAndroidUIAutomator("text(\"Enter name here\")").sendKeys("ABCD");
		//toast is shown ony when name is blank so it has been commented
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
		
		//Validating toast message is shown or not
		String toastmessage=driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
		//We are using name attribute here because developers specify the toast message under name
		System.out.println(toastmessage);
		
	}

}
