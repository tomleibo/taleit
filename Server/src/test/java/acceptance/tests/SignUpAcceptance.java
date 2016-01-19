package acceptance.tests;

import acceptance.core.AcceptanceTestBase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sharonk on 1/18/2016
 */
@Ignore
public class SignUpAcceptance extends AcceptanceTestBase {
    @Before
    public void init(){
        super.init();
    }

    @Test
    public void validNameAndPassword_1_1() {
        assertTrue("failed to perform signup with user: " + userName + " and password: " +
                password, bridge.signUp(userName, password));
    }

    @Test
    public void invalidName_1_2() {
        assertFalse("succeed to perform signup with invalid user name: " + invalidName, bridge.signUp(invalidName,
                password));
    }

    @Test
    public void noName_1_3() {
        assertFalse("succeed to perform signup to user with no name", bridge.signUp("",
                password));
    }

    @Test
    public void shortPassword_1_4() {
        assertFalse("succeed to perform signup with invalid password: " + shortPassword,
                bridge.signUp(userName, shortPassword));
    }

    @Test
    public void noPassword_1_5() {
        assertFalse("succeed to perform signup with no password",
                bridge.signUp(userName, ""));
    }

    @Test
    public void sameName_1_6() {
        assertTrue("failed to perform signup with user: " + userName + " and password: " + password,
                bridge.signUp(userName, password));

        assertFalse("succeed to perform signup with same user name : " + userName,
                bridge.signUp(userName, secondPassword));
    }

    @Test
    public void samePassword_1_7() {
        assertTrue("failed to perform signup with user: " + userName + " and password: " + password,
                bridge.signUp(userName, password));

        assertTrue("failed to perform signup with same password : " + password,
                bridge.signUp(secondUserName, password));
    }
    @Test
    public void randomNumberOfSignup_1_8(){
        int randomNumber = getRandomNumber();
        String userName = "gurk";
        String userPostfix = "@gmail.com";

        System.out.println("will perform " + randomNumber + " of Sign ups");
        for (int i=1; i<=randomNumber; i++){
            String actualUsername = userName+i+userPostfix;
            assertTrue("faild to perform Signup number " + i ,
                    bridge.signUp(actualUsername, password));
        }
    }
}
