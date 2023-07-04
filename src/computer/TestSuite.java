package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utilities;

import java.util.List;

public class TestSuite extends Utilities {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }


    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        clickOnElement(By.xpath("(//a[normalize-space()='Computers'])[1]"));

        clickOnElement(By.cssSelector("h2[class='title'] a[title='Show products in category Desktops']"));

        WebElement dropDown = driver.findElement(By.id("products-orderby"));
        Select select = new Select(dropDown);
        select.selectByVisibleText("Name: Z to A");
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() {
        //click on computer menu
        clickOnElement(By.xpath("(//a[normalize-space()='Computers'])[1]"));
        // click on desktop
        clickOnElement(By.cssSelector("h2[class='title'] a[title='Show products in Desktops']"));
        // sort by name z to a
        selectByVisibleTextFromDropDown(By.id("products-orderby"), "Name: A to Z");
        driver.navigate().refresh();
        List<WebElement> addToCartBtn = driver.findElements(By.xpath("//div[@class='buttons']/button"));
        addToCartBtn.get(0).click();
        //verify build computer
        String expectedProductName = "Build your computer";
        String actualProductName = getTextFromElement(By.xpath("//h1[contains(text(),'Build your own computer')]"));
        System.out.println(actualProductName);

        Assert.assertEquals("incorrectly Product Name displayed ", expectedProductName, actualProductName);
// select 2.2 GHz Intel Pentium Dual-Core E2200
        selectByVisibleTextFromDropDown(By.id("product_attribute_1"), "2.2 GHz Intel Pentium Dual-Core E2200");
        selectByVisibleTextFromDropDown(By.id("product_attribute_2"), "8GB [+$60.00]");
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_5_10']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_5_12']"));
// Verify the price "$1,475.00"
        String expectedPrice = "$1,475.00";
        String actualPrice = getTextFromElement(By.xpath("//span[@id='price-value-1']"));
        System.out.println(actualPrice);
        Assert.assertEquals("Product Price not displayed correctly", expectedPrice, actualPrice);
        //Click on "Add To Cart"
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));
       //
        String expectedNotification = "";
        String actualNotification = getTextFromElement(By.xpath("//p[@class='content']"));
        System.out.println(actualNotification);
        Assert.assertEquals("Notification displayed incorrectly", expectedNotification, actualNotification);
        clickOnElement(By.xpath("//span[@title='Close']"));

        //Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        Actions actions = new Actions(driver);
        WebElement shoppingCart = driver.findElement(By.xpath("//span[@class='cart-label']"));
        WebElement goToCart = driver.findElement(By.xpath("//button[normalize-space()='Go to cart']"));
        actions.moveToElement(shoppingCart).moveToElement(goToCart).click().perform();
        //Verify the message "Shopping cart"
        String expectedCartTitle = "Shopping cart";
        String actualCartTitle = getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']"));
        System.out.println(actualCartTitle);
        Assert.assertEquals("Cart title is not displayed correctly", expectedCartTitle, actualCartTitle);

        WebElement qtyBox = driver.findElement(By.xpath("//input[@id='itemquantity11277']"));
        qtyBox.clear();
        sendTextToElement((By) qtyBox, "2");
        clickOnElement(By.xpath("//button[@id='updatecart']"));

        String expectedTotal = "$2,950.00";
        String actualTotal = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        System.out.println(actualTotal);
        Assert.assertEquals("Total price displayed incorrectly", expectedTotal, actualTotal);
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));

        String expectedWelcomeMsg = "Welcome, Please Sign In!";
        String actualWelcomeMsg = getTextFromElement(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));
        System.out.println(actualWelcomeMsg);
        Assert.assertEquals("Welcome Sign In message not displayed correctly", expectedWelcomeMsg, actualWelcomeMsg);
        clickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));

        sendTextToElement(By.xpath("//button[normalize-space()='Checkout as Guest']"), "Prisha");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Patel");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "123patel@gmail.com");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United kingdom");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "75 b Repton Street");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "E13L67");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "09909456788");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        clickOnElement(By.xpath("//input[@id='shippingoption_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Debit");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Debit card");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Prisha Patel");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "123456677888");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireMonth']"), "01");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2030");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "356");
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        // Verify “Payment Method” is “Credit Card”
        String expectedPaymentMethod = "Creditcard";
        String actualPaymentMethod = getTextFromElement(By.xpath("//span[normalize-space()='Credit Card']"));
        //  System.out.println(actualPaymentMethod);
        Assert.assertEquals("Payment method is not displayed", expectedPaymentMethod, actualPaymentMethod);
         //Verify “Shipping Method” is “Next Day Air”
        String expectedShippingMethod = "Next day air";
        String actualShippingMethod = getTextFromElement(By.xpath("//span[normalize-space()='Next Day Air']"));
        System.out.println(actualShippingMethod);
        Assert.assertEquals("Shipping method is not displayed", expectedShippingMethod, actualShippingMethod);
         //Verify Total is “$2,950.00”
        String expectedTotalPrice = "$2,950.00";
        String actualTotalPrice = getTextFromElement(By.cssSelector("span[class='value-summary'] strong"));
        System.out.println(actualTotalPrice);
        Assert.assertEquals("total price displayed ", expectedTotalPrice, actualTotalPrice);
        // Click on “CONFIRM”
        clickOnElement(By.xpath("//button[normalize-space()='Confirm']"));
        String actualGreetingMsg = getTextFromElement(By.cssSelector("div[class='page-title'] h1"));
        //Verify the Text “Thank You”
        System.out.println(actualGreetingMsg);
        Assert.assertEquals("Thankyou message is not displayed", "Thank you", actualGreetingMsg);
       //Verify the message “Your order has been successfully processed!”
        String expectedOrderProcessedMsg = "Your order has been successfully processed!";
        String actualOrderProcessedMsg = getTextFromElement(By.cssSelector("div[class='section order-completed'] div[class='title'] strong"));
        System.out.println(actualOrderProcessedMsg);
        Assert.assertEquals("Order Proceed message is not displayed", expectedOrderProcessedMsg, actualOrderProcessedMsg);
        clickOnElement(By.cssSelector(".button-1.order-completed-continue-button"));
       //Verify the text “Welcome to our store”
        String actualWelcomeStoreMsg = getTextFromElement(By.xpath("//h2[normalize-space()='Welcome to our store']"));
        System.out.println(actualWelcomeStoreMsg);
        Assert.assertEquals("Welcome to store message is not displayed", "Welcome to our store", actualWelcomeStoreMsg);



    }


    @After
    public void teardown() {
        //closeBrowser();
    }

}