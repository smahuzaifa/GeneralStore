import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class VerifyingAddedCostWithTotalCost extends DesiredCapabilitiesClass {

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
		Thread.sleep(4000);
		/*
		 * Since we are navigating to multiple pages that both have mentioned common product orice and 
		 * ther id being same to avoid appium getting confised we are asking it to wait till the next screen is loaded
		 */
		//Getting price
		String amount1 = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(0).getText();
		/* $160 is the price, to remove dollar we save the string from 1st index ignoring oth index $
		 * Since the converted value is still a string and we cannot add 2 string value to get final value
		 * we will be converting it or parsing it
		*/
		amount1 = amount1.substring(1);
		double amount1value = Double.parseDouble(amount1);
		String amount2 = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(1).getText();
		amount2 = amount2.substring(1);
		double amount2value = Double.parseDouble(amount2);
		double finalvalue = amount1value + amount2value;
		//Comparing calculated value with value shown
		String mrp = driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();
		mrp=mrp.substring(1);
		Double finalmrp = Double.parseDouble(mrp); 
		Assert.assertEquals(finalmrp, finalvalue);
		if(finalmrp==finalvalue)
		{
			System.out.println("The calculated and actual value are true");
		}
				
	}

}
