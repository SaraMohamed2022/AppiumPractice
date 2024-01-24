package selfPracticing.commerceApp_Android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class DealingWithHyperApp extends BaseTest
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

        Thread.sleep(Duration.ofSeconds(5));
        // Here the APP will switch to a web view , to switch your TC scope to web view perform the next
        Set<String> contextHandles = driver.getContextHandles();
        contextHandles.forEach(System.out::println);
        //NATIVE_APP >> Modile app handler
        //WEBVIEW_com.androidsample.generalstore >> Web app handler

        //Switch to web context
        List<String> contextList = new ArrayList<>(contextHandles);
        driver.context(contextList.get(contextList.size()-1));

        //Do some actions on the web view

        //1 - handling Google Terms alert
        acceptGoogleCookies();
        WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));

        driver.findElement(By.name("q")).sendKeys("Hello World");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //2- Navigate back again to the mobile app context
        driver.context(contextList.get(0));

    }
}
