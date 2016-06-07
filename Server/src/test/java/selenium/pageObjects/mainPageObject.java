package selenium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by Shai on 07/06/2016.
 */
public class MainPageObject {
    WebDriver webDriver;


    public MainPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public List<WebElement> categories(){
        return webDriver.findElements(By.className("categoryClass"));
    }


}
