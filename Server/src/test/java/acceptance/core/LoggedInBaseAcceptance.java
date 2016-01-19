package acceptance.core;

import acceptance.utils.StoryDetailForTest;
import org.junit.Before;

import static junit.framework.Assert.assertTrue;

/**
 * Created by sharonk on 1/18/2016
 */
public class LoggedInBaseAcceptance extends SignedUpBaseAcceptance {
    protected String storyTitle = StoryDetailForTest.FIRST_TITLE.getValue();
    protected String storyText = StoryDetailForTest.FIRST_BODY.getValue();


    @Before
    public void init(){
        super.init();
        // login user
        assertTrue("failed to perform login with user: " + userName + " and password: " + password,
                bridge.login(userName, password));

    }


}
