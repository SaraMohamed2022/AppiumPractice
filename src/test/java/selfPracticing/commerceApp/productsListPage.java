package selfPracticing.commerceApp;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class productsListPage extends BaseTest
{
    @Test
    public void  addingProductToCart()
    {
       String countryToTest= "Bahamas";
       String userName = "Sara";
       String productName ="Jordan 6 Rings";
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

        scrollToElementWithTxt(productName);
        List<WebElement> productsDisplayed= driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName"));

        for (int i = 0; i < productsDisplayed.size(); i++)
        {
            String displayedName = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if(displayedName.equalsIgnoreCase(productName))
            {
                driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }

        //Go to cart page
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        waitUntilPageToLoad("Cart");

        //Assert the added item in the cart is the same one you choose
        assertEquals(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText(),productName);
    }

    @Test
    public void  validateTotalPriceForTwoProductsInCart()
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

        //Add first two products in the list to the cart
        // 1- Get the products prices to be checked later in checkout page
        // extract the float value from the displayed text
        float firstProductPrice = getFormattedAmount(driver.findElements
                        (AppiumBy.id("com.androidsample.generalstore:id/productPrice"))
                .get(0).getText());
        float secondProductPrice = getFormattedAmount(driver.findElements
                        (AppiumBy.id("com.androidsample.generalstore:id/productPrice"))
                .get(1).getText());

        // 2- Add the products
        driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']"))
                .get(0).click();
        driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']"))
                .get(0).click();

        // 3- Go to cart page to check the displayed sum
        // a- navigate to cart page
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        waitUntilPageToLoad("Cart");

        //b- extract the sum value
        float sum = getFormattedAmount(
                driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl"))
                        .getText());
        //c- assert the total price equals to the sum of the products prices
        assertEquals(sum,firstProductPrice+secondProductPrice);
    }
}
