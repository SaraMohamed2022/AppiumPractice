package selfPracticing.commerceApp_Android;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class OrderSubmission extends BaseTest
{
    @Test
    public void  orderSubmission() throws InterruptedException {
        //Jump directly to product list page
        jumpToProductListPage();
        //Add product to the cart
        driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']"))
                .get(0).click();
        // a- navigate to cart page
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        waitUntilPageToLoad("Cart");

        //long press to show Terms and conditions
        longPressAction(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton")));

        //Assert terms and conditions popup displayed successfully
        assertEquals(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/alertTitle")).getText(),"Terms Of Conditions");
        assertEquals(driver.findElement(AppiumBy.id("android:id/message")).getText(),
               "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book." );

        //Close the popup
        driver.findElement(AppiumBy.id("android:id/button1")).click();

        //Click on Complete purchase button
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();

    }
}
