package selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.File;

import static junit.framework.Assert.*;

/**
 * Created by sharonk on 1/18/2016
 */
public class test {
    WebDriver driver;

    @Before
    public void buildup(){
        System.setProperty("webdriver.chrome.driver", "utils\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void messingaround(){
        driver.get("http://localhost:8080");
    }

}