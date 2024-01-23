package selfPracticing;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class ClipboardCopyPaste extends BaseTest
{
    @Test
    public void  WifiSettingName() throws InterruptedException {

    // write your automation logic
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();
        WebElement wifiSettings = driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]"));
        wifiSettings.click();
        assertEquals(driver.findElement(AppiumBy.id("android:id/alertTitle")).getText(),"WiFi settings");
        driver.setClipboardText("Hello World");
        WebElement wifiSettingsValue = driver.findElement(AppiumBy.id("android:id/edit"));
        wifiSettingsValue.sendKeys(driver.getClipboardText());
        driver.findElement(AppiumBy.id("android:id/button1")).click();


        //Open Wifi settings again and assert the value
        //The upcoming assertion pass only by two ways
        // 1- adding Thread.sleep
        //  Thread.sleep(Duration.ofSeconds(3));  wifiSettings.click();

        // 2- inspect the elements again
        //driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();

        // if i used wifiSettings.click() and wifiSettingsValue.getText() it will fail !!

        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
        assertEquals(driver.findElement(AppiumBy.id("android:id/edit")).getText(),"Hello World");
    }
}
