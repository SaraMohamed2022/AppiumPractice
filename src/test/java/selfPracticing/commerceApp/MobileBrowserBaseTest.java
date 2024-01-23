package selfPracticing.commerceApp;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class MobileBrowserBaseTest
{
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    @BeforeClass
    public void configureAppium() throws MalformedURLException {

        //To automate initializing the server instead on using "appium" from the terminal
         service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr//local//lib//node_modules//appium//build//lib//main.js"))
                .withIPAddress("127.0.0.1")
                .withTimeout(Duration.ofMinutes(3))
                .usingPort(4723).build();

        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 2 XL API 34");
        options.setChromedriverExecutable(System.getProperty("user.dir")+"/src/test/java/resources/chromedriver");
        options.setCapability("browserName", "Chrome");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    public void acceptGoogleCookies()
    {
        By readMoreElement = By.xpath("(//div[contains(text(),'Weitere Informationen')])[1]");
        By acceptAllElement = By.xpath("//div[normalize-space()='Alle akzeptieren']");

        int maxAttempts = 10;
        int attempt = 0;
        while (attempt < maxAttempts) {
            try {
                if (driver.findElement(readMoreElement).isDisplayed()) {
                    driver.findElement(readMoreElement).click();
                    attempt++;
                }else if (driver.findElement(acceptAllElement).isDisplayed()) {
                    driver.findElement(acceptAllElement).click();
                    break;
                }else {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void longPressAction(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture"
                , ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()
                        ,"duration", 2000));
    }

    public void scrollToEnd() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            ));
        } while (canScrollMore);
    }

    public WebElement scrollToElementWithTxt(String elementText)
    {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+elementText+"\"));"));
    }

    public void swipeToElement(WebElement element , String direction)
    {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId",((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", 0.25
        ));
    }

    public void dragAndDrop(WebElement element , int xCoordinate , int yCoordinate)
    {
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", xCoordinate,
                "endY", yCoordinate
        ));
    }

    public void runInLandScapeMode()
    {
        DeviceRotation landScape= new DeviceRotation(0,0,90);
        driver.rotate(landScape);
    }

    public void waitUntilPageToLoad(String pageTitle)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(
                driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title"))
                ,"text",pageTitle));
    }
    public void startActivity(String activity)
    {
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity"
                , ImmutableMap.of("intent",
                        activity));

    }

    public Float getFormattedAmount(String text)
    {
        return Float.parseFloat
                (text.replaceAll("[^\\d.]", ""));
    }

    public void jumpToProductListPage()
    {
        String countryToTest= "Bahamas";
        String userName = "Sara";
        WebElement countryDDL = driver.findElement(AppiumBy.id("android:id/text1"));
        countryDDL.click();
        scrollToElementWithTxt(countryToTest).click();
        WebElement guestName= driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"));
        guestName.sendKeys(userName);
        //driver.hideKeyboard(); if the keyboard hide the next element
        WebElement guestGender=  driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale"));
        guestGender.click();
        WebElement letsShopBtn = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"));
        letsShopBtn.click();
    }

    @AfterClass
    public void tearDown()
    {
//        driver.quit();
//        service.stop();
    }
}
