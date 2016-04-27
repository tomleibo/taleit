import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

/**
 * Created by gur on 3/11/2016.
 */
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
        String username = "gurk" + new Random().nextInt() + "@post.bgu.ac.il";
        suite.signup(username, "123456");
        suite.login(username, "123456");
        suite.create(suite.cookie, "this is the title", "this is the text", "now this is a story","all of belair, my life got switched upside down");
    }
}
