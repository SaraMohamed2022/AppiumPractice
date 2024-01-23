package selfPracticing;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DragAndDrop extends BaseTest
{
    @Test
    public void  swipeInGallery() {

    // write your automation logic
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement firstCircle = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
        dragAndDrop(firstCircle,841,747);
        assertEquals(driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_result_text")).getText(),"Dropped!");
    }
}
