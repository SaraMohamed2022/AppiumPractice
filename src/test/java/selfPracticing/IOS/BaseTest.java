package selfPracticing.IOS;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest
{
    public IOSDriver driver;
    public AppiumDriverLocalService service;
    @BeforeClass
    public void configureAppium() throws MalformedURLException {

        //To automate initializing the server instead on using "appium" from the terminal
         service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr//local//lib//node_modules//appium//build//lib//main.js"))
                .withIPAddress("127.0.0.1")
                .withTimeout(Duration.ofMinutes(3))
                .usingPort(4724).build();

        service.start();

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("");
        options.setApp(System.getProperty("user.dir")+"/src/test/java/resources/UIKitCatalog.app/UIKitCatalog");
        driver = new IOSDriver(new URL ("http://127.0.0.1:4724"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void longPressAction(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture"
                , ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()
                        ,"duration", 2000));
    }


    @AfterClass
    public void tearDown()
    {
//        driver.quit();
//        service.stop();
    }
}
