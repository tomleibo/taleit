package selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by sharonk on 1/18/2016
 */
public class SeleniumBase {
    private WebDriver webDriver;
    static final String URL_BASE = "http://localhost:8080";
    protected ServicesSeleniumFacade facade;

    @Before
    public void buildup(){
        System.setProperty("webdriver.chrome.driver", "utils\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get(URL_BASE);

        facade = new ServicesSeleniumFacade(webDriver);

    }

    @After
    public void tearDown(){
        webDriver.close();
    }
}