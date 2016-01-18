package acceptance.tests;

import acceptance.core.TestBase;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import static com.sun.webkit.perf.WCFontPerfLogger.log;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sharonk on 1/18/2016
 */
public class SignUp extends TestBase {
    @Before
    public void init(){
        super.init();
    }

    @Test
    public void validNameAndPassword() {
        assertTrue("failed to perform signup with user: " + userName + " and password: " + password, bridge.signUp
                (userName, password));
    }

    @Test
    public void invalidName() {
        assertFalse("succeed to perform signup with invalid user name: " + invalidName, bridge.signUp(invalidName,
                password));
    }

    @Test
    public void shortPassword() {
        assertFalse("succeed to perform signup with invalid password: " + shortPassword, bridge.signUp(userName,
                shortPassword));
    }

    @Test
    public void sameName() {
        assertTrue("failed to perform signup with user: " + userName + " and password: " + password, bridge.signUp
                (userName, password));

        assertFalse("succeed to perform signup with same user name : " + userName, bridge.signUp(userName,
                secondPassword));
    }

    @Test
    public void samePassword() {
        assertTrue("failed to perform signup with user: " + userName + " and password: " + password, bridge.signUp
                (userName, password));

        assertTrue("failed to perform signup with same password : " + password, bridge.signUp(secondUserName,
                password));
    }
    @Test
    public void randomNumberOfSignup(){
        int randomNumber = getRandomNumber();
        String userName = "gurk";
        String userPostfix = "@gmail.com";

        System.out.println("will perform " + randomNumber + " of Sign ups");
        for (int i=1; i<=randomNumber; i++){
            String actualUsername = userName+i+userPostfix;
            assertTrue("faild to perform Signup number " + i ,
                    bridge.login(actualUsername, password));
        }
    }
}
