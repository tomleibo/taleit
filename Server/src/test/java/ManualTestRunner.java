import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

/**
 * Created by gur on 3/11/2016.
 */
@Ignore
public class ManualTestRunner {
    ManualTests suite;

    @Before
    public void init(){
        this.suite = new ManualTests();
    }

    @After
    public void dispose(){
        this.suite = null;
    }

    @Test
    public void login() throws IOException, JSONException {
        String username = "gurk" + new Random().nextInt() + "@post.bgu.ac.il";
        suite.signup(username, "123456");
        suite.login(username, "123456");
    }

    @Test
    public void signup() throws IOException, JSONException {
        String username = "gurk" + new Random().nextInt() + "@post.bgu.ac.il";
        suite.signup(username, "123456");
    }

    @Test
    public void logout() throws IOException, JSONException {
        String username = "gurk" + new Random().nextInt() + "@post.bgu.ac.il";
        suite.signup(username, "123456");
        suite.login(username, "123456");
        suite.logout(suite.cookie);
    }

    @Test
    public void browse() throws IOException, JSONException {
        suite.browse();
    }

    @Test
    public void create() throws IOException, JSONException {
        suite = new ManualTests("52.58.147.123");
        String username = "yuvalla" + new Random().nextInt() + "@post.bgu.ac.il";
        suite.signup(username, "123456");
        suite.login(username, "123456");
        suite.create(suite.cookie, "סיפורו של דג", "עוד קצת תוכן", "תוכן תוכן","אהההההההההההה");
    }
}
