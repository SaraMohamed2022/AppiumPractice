package selfPracticing.commerceApp;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class MobileBrowserTest extends MobileBrowserBaseTest
{
    @Test
    public void testMobileBrowser() {
        driver.get("https://google.com");
        acceptGoogleCookies();
        driver.findElement(By.name("q")).sendKeys("Hello World");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        System.out.println(driver.getTitle() + "  Page title ");
    }
}
