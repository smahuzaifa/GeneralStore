import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.net.MalformedURLException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class SwitchingToWebView extends DesiredCapabilitiesClass {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		AndroidDriver<AndroidElement> driver = capabilities();
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		//Page 1 Action code
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
		
		//Code for page 2 onwards action
		driver.findElementsByAndroidUIAutomator("text(\"ADD TO CART\")").get(0).click();
		/*
		 * After above operation the add to cart of earlier 0 becomes added to cart so for next action
		 * there is only one add to cart on screen so we are passing 0 there instead of incrementing from
		 * previous
		 */
		driver.findElementsByAndroidUIAutomator("text(\"ADD TO CART\")").get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(4000);//MobileGestures
		WebElement checkbox = driver.findElementByClassName("android.widget.CheckBox");
		//we can even add .click() to select checkbox in this case
		//We will be targetting mobile gestures like tap and longpress here
		TouchAction t = new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();
		WebElement tc = driver.findElementById("com.androidsample.generalstore:id/termsButton");
		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
		//Thread.sleep(2000);
		driver.findElementById("android:id/button1").click();
		driver.findElementByAndroidUIAutomator("text(\"Visit to the website to complete purchase\")").click();
		Thread.sleep(7000);
		/*
		 * https://appium.io/docs/en/writing-running-appium/web/hybrid/
		 * follow this link to know more about switching from native app automation to web view
		 * automation(appium->selenium)
		 */
		Set<String> context = driver.getContextHandles();
		//Set is a collection of strings
		for(String contextName : context) //enhanced for loop
		{
			System.out.println(contextName);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
		Thread.sleep(2000);
		/*
		 * this helps us to switch to the context of webview
		 * we can now automate the webview by inspecting the same on web and collecting its parameters
		 * and then automating it using similar code like driver.find....
		*/
		//driver.findElement(By.name("q")).sendKeys("hello"); 
		//locating using name is deprecated now
		//driver.findElement(By.xpath("//*[@name='q']")).sendKeys("Hello");
		driver.findElementByXPath("//*[@name='q']").sendKeys("hello");
		driver.findElementByXPath("//*[@name='q']").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		//Clicking on enter after searching hello
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		//Pressing the back button of android to go back to native app from web view
		driver.context("NATIVE_APP");
		//Switching back to native app
		
	}

}
