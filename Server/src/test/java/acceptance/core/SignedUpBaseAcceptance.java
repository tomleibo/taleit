package acceptance.core;

import org.junit.Before;

import static junit.framework.Assert.assertTrue;

/**
 * Created by sharonk on 1/18/2016
 */
public class SignedUpBaseAcceptance extends AcceptanceTestBase {

    @Before
    public void init(){
        super.init();
        // login user
        assertTrue("failed to perform signup with user: " + userName + " and password: " + password, bridge.signUp(userName, password));
    }


}
