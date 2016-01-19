package acceptance.tests;

import acceptance.core.SignedUpBaseAcceptance;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sharonk on 1/18/2016
 */
@Ignore
public class LoginAcceptance extends SignedUpBaseAcceptance {
    @Before
    public void init(){
        super.init();
    }

    @Test
    public void validNameAndPassword_2_1() {
        assertTrue("failed to perform login with user: " + userName + " and password: " + password, bridge.login
                (userName, password));
    }

    @Test
    public void unexistingName_2_2() {
        assertFalse("succeed to perform login with un existing user name: " + secondUserName, bridge.login(secondUserName,
                password));
    }

    @Test
    public void noName_2_3() {
        assertFalse("succeed to perform login with no username",
                bridge.login("",password));
    }

    @Test
    public void unexistingPassword_2_4() {
        assertFalse("succeed to perform login with invalid password: " + secondPassword,
                bridge.login(userName, secondPassword));
    }

    @Test
    public void noPassword_2_5() {
        assertFalse("succeed to perform login with no password",
                bridge.login(userName, ""));
    }

    @Test
     public void twoLoginsSameUser_2_6() {
        assertTrue("failed to perform login with user: " + userName + " and password: " + password,
                bridge.login(userName, password));

        assertFalse("succeed to perform second login with same user name : " + userName,
                bridge.signUp(userName, password));
    }

    @Test
    public void twoLoginsDifferentUser_2_7() {
        assertTrue("failed to perform signup with user: " + secondUserName + " and password: " + secondPassword,
                bridge.signUp(secondUserName, secondPassword));

        assertTrue("failed to perform login with user: " + userName + " and password: " + password,
                bridge.login(userName, password));

        assertFalse("succeed to perform login with different will logged in",
                bridge.login(secondUserName, secondPassword));
    }
}
