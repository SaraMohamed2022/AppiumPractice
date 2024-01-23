package selfPracticing;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SwipeDemo extends BaseTest
{
    @Test
    public void  swipeInGallery()  {

    // write your automation logic
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
        WebElement firstImage = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[1]"));
        assertTrue(Boolean.parseBoolean(firstImage.getAttribute("focusable")));
        swipeToElement(firstImage,"left");
        assertFalse(Boolean.parseBoolean(firstImage.getAttribute("focusable")));

    }
}
