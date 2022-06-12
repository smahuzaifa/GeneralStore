import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class OptimisedfVerifyingAddedCostWithTotalCost extends DesiredCapabilitiesClass {
	//To do common task of removing $ from string and then converting it into string
	public static double getAmount(String value)
	{
		value=value.substring(1);
		double numbervalue = Double.parseDouble(value);
		return numbervalue;
	}

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
		int productcount = driver.findElementsById("com.androidsample.generalstore:id/productPrice").size();
		double sum =0;
		/*
		 * When there are multiple product then we hae to write an optimised code whicch will do all
		 * calculations that need same type of code but with indexes changing hence we are writing an
		 * for loop here to calculate total value of all products added
		 */
		for(int i=0;i<productcount;i++)
		{
			String productvalue = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(i).getText();
			double amount = getAmount(productvalue);
			sum=sum+amount;
		}
		//Comparing calculated value with value shown
		String mrp = driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();
		mrp=mrp.substring(1);
		Double finalmrp = Double.parseDouble(mrp); 
		Assert.assertEquals(finalmrp, sum);
		if(finalmrp==sum)
		{
			System.out.println("The calculated and actual value are true");
		}
				
	}

}
