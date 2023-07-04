package utilities;

import browsertest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Utilities extends BaseTest {
    /**
     * This method will click on element
     */
    public void clickOnElement(By by){
        driver.findElement(by).click();

    }

    /**
     *
     * This method will get text from element
     */
    public String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }

    /**
     *
     * This method will send text ti element
     */
    public void sendTextToElement(By by, String text){
        driver.findElement(by).sendKeys(text);
    }
    // **********************************Alert methods **************************//
    // 5 methods do in homework switch.get, dismis,text,accept,send
    //***********************************Select class method *****************//

    /**
     * This method will select the option by visible text
     */
    public void selectByVisibleTextFromDropDown(By by, String text){
        WebElement dropDown = driver.findElement(by);
     Select select = new Select(dropDown);
        select.selectByVisibleText(text);// hw create another method
    }
    /**
     * This method will select the option by visible text
     */
    public void selectByValuefromDropdown(By by , String value){
       /// new Select(driver.findElement(by).selectByvalue)
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(value);



    }

}
