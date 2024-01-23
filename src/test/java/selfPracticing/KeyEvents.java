package selfPracticing;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.annotations.Test;

import static io.appium.java_client.android.nativekey.AndroidKey.*;
import static org.testng.Assert.assertEquals;

public class KeyEvents extends BaseTest
{
    @Test
    public void  WifiSettingName()  {

    // write your automation logic
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();
        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
        assertEquals(driver.findElement(AppiumBy.id("android:id/alertTitle")).getText(),"WiFi settings");
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Hello");
        //Click Enter
        driver.pressKey(new KeyEvent(ENTER));
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        //Click on back button
        driver.pressKey(new KeyEvent(BACK));
        //Click on Home button
        driver.pressKey(new KeyEvent(HOME));
    }
}
