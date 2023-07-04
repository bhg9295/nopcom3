package Electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utilities;

public class ElectronicsTest extends Utilities {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }


    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully(){
        //Mouse Hover on “Electronics” Tab
        WebElement electronicsTab = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));
        //Mouse Hover on “Cell phones” and click
        WebElement cellPhonesTab = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        //Mouse Hover method
        Actions actions = new Actions(driver);
        actions.moveToElement(electronicsTab).moveToElement(cellPhonesTab).click().perform();
        //Verify the text “Cell phones
        String actualCategories = getTextFromElement(By.xpath("//h1[normalize-space()='Cell phones']"));
        //verify the cell method
        System.out.println(actualCategories);
        Assert.assertEquals("Cell phones title not displayed correctly", "Cell phones", actualCategories);
// Click on List View Tab
        clickOnElement(By.cssSelector("a[title='List']"));
        clickOnElement(By.xpath("//a[normalize-space()='Nokia Lumia 1020']"));
//Click on product name “Nokia Lumia 1020” link
        String expectedProductName = "Nokia Lumia 1020";
        String actualProductName = getTextFromElement(By.xpath("//h1[normalize-space()='Nokia Lumia 1020']"));
        //Verify the text “Nokia Lumia 1020”
        System.out.println(actualProductName);
        Assert.assertEquals("Product name displayed incorrectly", expectedProductName, actualProductName);
//Verify the price “$349.00”
        String actualProductPrice = getTextFromElement(By.cssSelector("#price-value-20"));
        System.out.println(actualProductPrice);
        Assert.assertEquals("Product price displayed incorrectly", "$349.00", actualProductPrice);

        WebElement qtyTextBox = driver.findElement(By.cssSelector("#product_enteredQuantity_20"));
        qtyTextBox.clear();
        //Change quantity to 2
        sendTextToElement((By) qtyTextBox,"2");
        //Click on “ADD TO CART” tab
        clickOnElement(By.cssSelector("#add-to-cart-button-20"));
        //Verify the Message "The product has been added to your shopping cart" on Top green Bar
        String expectedHeaderMsg = "The product has been added to your shopping cart";
        String actualHeaderMsg = getTextFromElement(By.cssSelector(".content"));
        System.out.println(actualHeaderMsg);
        Assert.assertTrue("Product added message not displayed correctly", actualHeaderMsg.contains(expectedHeaderMsg));
        clickOnElement(By.cssSelector("span[title='Close']"));
//Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        WebElement shoppingCart = driver.findElement(By.xpath("//span[@class='cart-label']"));
        WebElement goToCart = driver.findElement(By.xpath("//button[normalize-space()='Go to cart']"));
        actions.moveToElement(shoppingCart).moveToElement(goToCart).click().perform();
//Verify the message "Shopping cart"
        String expectedCartTitle = "Shopping cart";
        String actualCartTitle = getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']"));
        System.out.println(actualCartTitle);
        Assert.assertEquals("Cart title is not displayed correctly", expectedCartTitle, actualCartTitle);
//Verify the quantity is 2
        String actualQty = getTextFromElement(By.xpath("//input[@id='itemquantity11240']"));
        System.out.println(actualQty);
        Assert.assertEquals("Quantity displayed incorrectly", "2", actualQty);
//Verify the Total $698.00
        String actualTotal = getTextFromElement(By.cssSelector(".product-subtotal"));
        System.out.println(actualTotal);
        Assert.assertEquals("Total displayed incorrectly", "$698.00", actualTotal);
        //click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        //Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));
//Verify the Text “Welcome, Please Sign In
        String expectedWelcomeMsg = "Welcome, Please Sign In!";
        String actualWelcomeMsg = getTextFromElement(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));
        System.out.println(actualWelcomeMsg);
        Assert.assertEquals("Welcome Sign In message not displayed correctly", expectedWelcomeMsg, actualWelcomeMsg);
//Click on “REGISTER” tab
        clickOnElement(By.cssSelector(".button-1.register-button"));
        //Verify the text “Register”
        String actualRegisterText = getTextFromElement(By.cssSelector("div[class='page-title'] h1"));
        Assert.assertEquals("Register text is not displayed", "Register", actualRegisterText);

        sendTextToElement(By.cssSelector("#FirstName"),"Geeta");
        sendTextToElement(By.cssSelector("#LastName"), "Joshi");
        sendTextToElement(By.cssSelector("#Email"),"geeta.joshi@gmail.com");
        sendTextToElement(By.cssSelector("#Password"),"Test123");
        sendTextToElement(By.cssSelector("#ConfirmPassword"),"Test123");
        clickOnElement(By.cssSelector("#register-button"));
//Verify the message “Your registration completed”
        String expectedRegCompletedMsg = "Your registration completed";
        String actualRegCompletedMsg = getTextFromElement(By.cssSelector(".result"));
        System.out.println(actualRegCompletedMsg);
        Assert.assertEquals("Register Completed message is not displayed", expectedRegCompletedMsg, actualRegCompletedMsg);
        clickOnElement(By.cssSelector(".button-1.register-continue-button"));
//Verify the text “Shopping card
        String actualCartText = getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']"));
        System.out.println(actualCartTitle);
        Assert.assertEquals("Cart title is not displayed correctly", expectedCartTitle, actualCartText);

        clickOnElement(By.xpath("//a[normalize-space()='Log in']"));
        sendTextToElement(By.cssSelector("#Email"),"geeta.joshi@gmail.com");
        sendTextToElement(By.cssSelector("#Password"),"Test123");
        clickOnElement(By.cssSelector("button[class='button-1 login-button']"));


        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));
    }



    @After
    public void teardown() {
        closeBrowser();
    }


}
