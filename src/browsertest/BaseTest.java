package browsertest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest  {
    public static WebDriver driver;
    public void openBrowser(String baseUrl){

        driver = new ChromeDriver();
        //Open the url into Browser
        driver.get(baseUrl);
        //Maximise Browser
        driver.manage().window().maximize();
        //We give Implicit wait to Driver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
    }
    public void closeBrowser(){
        driver.quit();
    }

}
