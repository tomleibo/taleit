package selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.pageObjects.CreatePageObject;
import selenium.pageObjects.FacebookPage;
import selenium.pageObjects.MainPageObject;

/**
 * Created by sharonk on 6/11/2016
 */
public class SeleniumBase {

    protected static final int TIME_TO_WAIT = 500;
    protected static final String URL_BASE = "http://localhost:8080";
    protected static final String STORY_TITLE = "Lorem ipsum dolor sit amet, conse";
    protected static final String PARAGRAPH_TITLE = "Dicimus aliquem hilare vivere";
    protected static final String BODY_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sic enim maiores" +
            " nostri labores non fugiendos tristissimo tamen verbo aerumnas etiam in deo nominaverunt. Ut non sine causa " +
            "ex iis memoriae ducta sit disciplina. Sed ut iis bonis erigimur, quae expectamus, sic laetamur iis, quae " +
            "recordamur. Ita relinquet duas, de quibus etiam atque etiam consideret. Istam voluptatem, inquit, Epicurus " +
            "ignorat? Sed ne, dum huic obsequor, vobis molestus sim. Suo genere perveniant ad extremum; Quid turpius quam " +
            "sapientis vitam ex insipientium sermone pendere? Duo Reges: constructio interrete.\n" +
            "\n" +
            "Quo modo autem philosophus loquitur? Sed " +
            "tamen est aliquid, quod nobis non liceat, liceat illis. Quod si ita se habeat, non possit beatam praestare " +
            "vitam sapientia. At hoc in eo M. An eiusdem modi? Sed tu istuc dixti bene Latine, parum plane. Occultum " +
            "facinus esse potuerit, gaudebit; Nosti, credo, illud: Nemo pius est, qui pietatem-; Quamquam te quidem video " +
            "minime esse deterritum. Quare attende, quaeso.";

    protected static final String CONTINUE_PARAGRAPH_TITLE = "WOW This Is Great Paragraph Title";

    protected WebDriver webDriver;
    protected ServicesSeleniumFacade facade;

    @Before
    public void buildup() {
        System.setProperty("webdriver.chrome.driver", "utils\\chromedriver.exe");
        log("Open new Chrome Driver");
        webDriver = new ChromeDriver();
        log("Maximize window");
        webDriver.manage().window().maximize();
        log("Navigating to Tale it");
        webDriver.get(URL_BASE);
        facade = new ServicesSeleniumFacade(webDriver);
        // (new DummyDB()).eraseInjectDummyDB();

    }

    @After
    public void tearDown() {
        webDriver.close();
    }

    protected void waitFor(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void log(String str) {
        System.out.println(str);
    }

    protected void performFacebookLogin() {
        String parentHandle = webDriver.getWindowHandle();

        log("Click on 'Facebook Login' button");
        MainPageObject mainPageObject = facade.mainPageObject();

        mainPageObject.facebookButton().click();
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }
        waitFor(TIME_TO_WAIT);
        FacebookPage facebookPage = facade.facebookPage();

        log("Insert Email");
        facebookPage.email().sendKeys("taleit42@gmail.com");
        waitFor(TIME_TO_WAIT);

        log("Insert Password");
        facebookPage.password().sendKeys("BritneySpears42");
        waitFor(TIME_TO_WAIT);

        log("Click login");
        facebookPage.login().click();
        webDriver.switchTo().window(parentHandle);
        waitFor(3000);
    }

    protected void createNewStory() {
        log("Click on 'Create' button");
        MainPageObject mainPageObject = facade.mainPageObject();
        mainPageObject.createButton().click();
        waitFor(TIME_TO_WAIT);

        log("Fill Story Title");
        CreatePageObject createPageObject = facade.createPageObject();
        createPageObject.storyTitle().sendKeys(STORY_TITLE);
        waitFor(TIME_TO_WAIT);

        log("Fill Paragraph Title");
        createPageObject.paragraphTitle().sendKeys(PARAGRAPH_TITLE);
        waitFor(TIME_TO_WAIT);

        log("Fill Body text");
        createPageObject.body().sendKeys(BODY_TEXT);
        waitFor(TIME_TO_WAIT);

        log("Choose Category");
        createPageObject.chooseCategotyByIndex(3);
        waitFor(TIME_TO_WAIT);

        log("Click on 'Set It Free' button");
        createPageObject.setItFree().click();
        waitFor(TIME_TO_WAIT);
    }
}
