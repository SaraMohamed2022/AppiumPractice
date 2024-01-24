package selfPracticing.commerceApp_Android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class welcomeForm extends BaseTest
{
    @Test
    public void  FillingTheFormWihoutName()
    {
        String countryToTest= "Bahamas";
        WebElement countryDDL = driver.findElement(AppiumBy.id("android:id/text1"));
        countryDDL.click();
        scrollToElementWithTxt(countryToTest).click();
        //driver.hideKeyboard(); if the keyboard hide the next element
        WebElement guestGender=  driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale"));
        guestGender.click();
        WebElement letsShopBtn = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"));
        letsShopBtn.click();
        WebElement toastMsg = driver.findElement(AppiumBy.xpath("(//android.widget.Toast)"));
        assertEquals(toastMsg.getAttribute("name"),"Please enter your name");
    }
    @Test
    public void  FillingTheForm()
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
}
