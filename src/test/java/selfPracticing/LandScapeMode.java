package selfPracticing;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.DeviceRotation;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class LandScapeMode extends BaseTest
{
    @Test
    public void  WifiSettingName() {

    // write your automation logic
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();
        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
        assertEquals(driver.findElement(AppiumBy.id("android:id/alertTitle")).getText(),"WiFi settings");
        runInLandScapeMode();
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Hello");
        driver.findElement(AppiumBy.id("android:id/button1")).click();
    }
}
