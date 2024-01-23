package selfPracticing;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LongPress extends BaseTest
{
    @Test
    public void  longPressOnPeopleNames()  {

    // write your automation logic
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        //    //android.widget.TextView[@text="People Names"]
        WebElement peopleNamesWidgeList = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"People Names\"]"));
        longPressAction(peopleNamesWidgeList);
        assertEquals(driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Sample menu\"]")).getText(),"Sample menu");

    }
}
