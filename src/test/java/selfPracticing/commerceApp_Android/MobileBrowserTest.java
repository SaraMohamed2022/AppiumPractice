package selfPracticing.commerceApp_Android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MobileBrowserTest extends MobileBrowserBaseTest
{
    @Test
    public void testMobileBrowser() {
//        driver.get("https://google.com");
//        acceptGoogleCookies();
//        driver.findElement(By.name("q")).sendKeys("Hello World");
//        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//        System.out.println(driver.getTitle() + "  Page title ");

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(AppiumBy.cssSelector("span.navbar-toggler-icon")).click();
        driver.findElement(AppiumBy.cssSelector("a[routerlink='/products']")).click();
        ((JavascriptExecutor)driver).executeScript("window-scrollBy(0,1000)","");
        assertEquals(driver.findElement(AppiumBy.cssSelector("a[href*='products/3']")).getText(),"Devops");

    }
}
