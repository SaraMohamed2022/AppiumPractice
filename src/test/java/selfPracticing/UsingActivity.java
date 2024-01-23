package selfPracticing;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UsingActivity extends BaseTest
{
    @Test
    public void  MoveToWifiSettingsUsingActivity()  {

        //adb shell dumpsys window | grep -E 'mCurrentFocus' on mac
        // to get the App package name and activity name and pass it as activity attribute
       String activity= "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies";
       startActivity( activity);
    // the first two steps are replace by jumping directly to the activity page
//        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
//        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();
        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
        assertEquals(driver.findElement(AppiumBy.id("android:id/alertTitle")).getText(),"WiFi settings");
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Hello World");
        driver.findElement(AppiumBy.id("android:id/button1")).click();

        //Do assertion
        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
        assertEquals(driver.findElement(AppiumBy.id("android:id/edit")).getText(),"Hello World");

    }
}
