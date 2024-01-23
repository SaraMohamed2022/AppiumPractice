package selfPracticing;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ScrollDemo extends BaseTest
{
    @Test
    public void  scrollToWebView()  {

    // write your automation logic
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        scrollToElementWithTxt("WebView");
        WebElement webViewOpt = driver.findElement(AppiumBy.accessibilityId("WebView"));

        webViewOpt.click();
    }
}
