package acceptance.tests;

import acceptance.core.TestBase;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sharonk on 1/18/2016
 */
public class Login extends TestBase {
    @Before
    public void init(){
        super.init();
    }

    @Test
    public void validNameAndPassword() {
        assertTrue("failed to perform login with user: " + userName + " and password: " + password,
                bridge.login(userName, password));
    }

    @Test
    public void invalidName() {
        assertFalse("succeed to perform signup with invalid user name: " + invalidName,
                bridge.login(invalidName,password));
    }

    @Test
    public void invalidPassword() {
        assertFalse("succeed to perform login with invalid password: " + secondPassword,
                bridge.login(userName, secondPassword));
    }

    @Test
     public void twoLoginsSameUser() {
        assertTrue("failed to perform login with user: " + userName + " and password: " + password,
                bridge.login(userName, password));

        assertFalse("succeed to perform second login with same user name : " + userName,
                bridge.signUp(userName, password));
    }

    @Test
    public void twoLoginsDifferentUser() {
        assertTrue("failed to perform signup with user: " + secondUserName + " and password: " + secondPassword,
                bridge.signUp(secondUserName, secondPassword));

        assertTrue("failed to perform login with user: " + userName + " and password: " + password,
                bridge.login(userName, password));

        assertFalse("succeed to perform login with different will logged in",
                bridge.login(secondUserName, secondPassword));
    }
}
