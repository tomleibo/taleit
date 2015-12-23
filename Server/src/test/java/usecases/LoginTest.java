package usecases;

import exceptions.LoginException;
import exceptions.SignUpException;
import exceptions.UsecaseException;
import junit.framework.Assert;
import lang.SafeObject;
import model.Model;
import org.junit.Before;
import org.junit.Test;
import usecases.users.Login;
import usecases.users.SignUp;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertNotNull;

/**
 * Created by gur on 12/23/2015.
 */
public class LoginTest extends TestBase {
    @Before
    public void setUp(){
        String userName = UserDetailForTest.USERNAME_VALID.getValue();
        new SignUp(new SafeObject<Model>(model), userName, UserDetailForTest.PASSWORD_VALID.getValue()).perform();
    }
    @Test
    public void userExists_validData_userLoggedIn() {
        // arrange
        String userName = UserDetailForTest.USERNAME_VALID.getValue();
        Login login = new Login(new SafeObject<Model>(model), userName, UserDetailForTest.PASSWORD_VALID.getValue());

        // act
        login.perform();

        // assert
        assertNotNull("could not login username: " + userName, login.getCookie());
        assertNotNull("could not login username: " + userName, model.getUserFromCookie(login.getCookie()));
    }

    @Test(expected = LoginException.class)
    public void noUser_validData_userNotLoggedIn() {
        // arrange
        String userName = UserDetailForTest.USERNAME_FIRST_TIME.getValue();
        Login login = new Login(new SafeObject<Model>(model), userName, UserDetailForTest.PASSWORD_VALID.getValue());

        // act
        login.perform();

        // assert
        assertNull("could login username: " + userName + " but he was never signed up", login.getCookie());
        assertNotNull("could login username: " + userName + " but he was never signed up", model.getUserFromCookie(login.getCookie()));
    }

    @Test(expected = UsecaseException.class)
    public void userExists_badmail_userNotLoggedIn() {
        // arrange
        String userName = UserDetailForTest.USERNAME_INVALID.getValue();
        Login login = new Login(new SafeObject<Model>(model), userName, UserDetailForTest.PASSWORD_VALID.getValue());

        // act
        login.perform();

        // assert
        assertNull("could login username: " + userName + " but he has invalid username", login.getCookie());
        assertNotNull("could login username: " + userName + " but he has invalid username", model.getUserFromCookie(login.getCookie()));
    }

    @Test(expected = UsecaseException.class)
    public void userExists_passwordIsNull_userNotLoggedIn() {
        // arrange
        String userName = UserDetailForTest.USERNAME_VALID.getValue();
        Login login = new Login(new SafeObject<Model>(model), userName, null);

        // act
        login.perform();

        // assert
        assertNull("could login username: " + userName + " but he had null password", login.getCookie());
        assertNotNull("could login username: " + userName + " but he has null password", model.getUserFromCookie(login.getCookie()));
    }

    @Test(expected = LoginException.class)
    public void userExists_passwordIncorrect_userNotLoggedIn() {
        // arrange
        String userName = UserDetailForTest.USERNAME_VALID.getValue();
        Login login = new Login(new SafeObject<Model>(model), userName, UserDetailForTest.PASSWORD_INCORRECT.getValue());

        // act
        login.perform();

        // assert
        assertNull("could login username: " + userName + " but he had wrong password", login.getCookie());
        assertNotNull("could login username: " + userName + " but he has wrong password", model.getUserFromCookie(login.getCookie()));
    }
}
