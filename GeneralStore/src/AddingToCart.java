import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.Assert;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AddingToCart extends DesiredCapabilitiesClass{

	public static void main(String[] args) throws MalformedURLException {
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
		
		//Page 2
		
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".resourceId(\"com.androidsample.generalstore:id/rvProductList\"))"
				+ ".scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));
		/*
		 * We cannot directly scroll to required product and select add to cart since the scroll happens
		 * only till the name is found, add to cart button may be hidden. So we traverse the parent
		 * component until the required  child component is completely visible.
		 */
		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		//Now we are finding all elements with that ID on the screen
		for(int i=0;i<count;i++)
		    {
		    	String text=driver.findElements(By.id("com.androidsample.generalstore:id/productName"))
		    			.get(i).getText();
		    	//Find text of all elements with mentioned ID in the screen and get their position too
		    	if(text.equalsIgnoreCase("Jordan 6 Rings"))
		    	{
		    		driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart"))
		    		.get(i).click();
		    		//Click on add to cart of the product satisfying the if condition
		    		break;
		    		/*
		    		 * We are breaking so that control comes out of loop once condition is satisifed
		    		 * instead of wasting time executing the entire loop
		    		 */
		    	}
		    }
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		//Checking if the product added to cart and prodcut in cart is same
		String productincart = driver.findElementById("com.androidsample.generalstore:id/productName").getText();
		/*
		 * We cannot use string to compare the text as we are getting text in above statement 
		 */
		Assert.assertEquals(productincart, "Jordan 6 Rings");
	}

}
