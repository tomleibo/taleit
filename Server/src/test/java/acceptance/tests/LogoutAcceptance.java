package acceptance.tests;

import acceptance.core.SignedUpBaseAcceptance;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sharonk on 1/18/2016
 */
public class LogoutAcceptance extends SignedUpBaseAcceptance {
    @Before
    public void init(){
        super.init();
    }

    @Test
    public void afterLogin() {
        assertTrue("failed to perform login with user: " + userName + " and password: " + password,
                bridge.login(userName, password));

        assertTrue("failed to perform logout with user: " + userName,
                bridge.logout());
    }

    @Test
    public void withoutLogin() {
        assertFalse("succeed to perform logout without login: " + userName, bridge.logout());
    }

    @Test
    public void loginAndTwoLogouts() {
        assertTrue("failed to perform login with user: " + userName + " and password: " + password,
                bridge.login(userName, password));

        assertTrue("failed to perform logout with user: " + userName, bridge.logout());

        assertFalse("succeed to perform logout without login: " + userName, bridge.logout());
    }

    @Test
     public void randomNumbersOfLoginLogout() {
        int randomNumber = getRandomNumber();

        System.out.println("will perform " + randomNumber + " of login and logout");
        for (int i=1; i<=randomNumber; i++){
            assertTrue("failed to perform login with user: " + userName + " and password: " + password,
                    bridge.login(userName, password));

            assertTrue("failed to perform logout with user: " + userName,
                    bridge.logout());
        }
    }

}
