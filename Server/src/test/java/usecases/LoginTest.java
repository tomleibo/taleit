package usecases;

import exceptions.LoginException;
import exceptions.UsecaseException;
import lang.SafeObject;
import model.Model;
import org.junit.Before;
import org.junit.Test;
import usecases.core.SignedUpTestBase;
import usecases.users.Login;
import usecases.utils.UserDetailForTest;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Kerzman on 12/23/2015.
 */
public class LoginTest extends SignedUpTestBase {
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void userExists_validData_userLoggedIn_2_1() {
        // arrange
        Login login = new Login(new SafeObject<Model>(model), userName, password);

        // act
        login.perform();

        // assert
        assertNotNull("could not login username: " + userName, login.getCookie());
        assertNotNull("could not login username: " + userName, model.getUserFromCookie(login.getCookie()));
    }

    @Test(expected = LoginException.class)
    public void noUser_validData_userNotLoggedIn_2_2() {
        // arrange
        String firstTimeUserName = UserDetailForTest.USERNAME_FIRST_TIME.getValue();
        Login login = new Login(new SafeObject<Model>(model), firstTimeUserName, password);

        // act
        login.perform();

        // assert
        assertNull("could login username: " + firstTimeUserName + " but he was never signed up", login.getCookie());
        assertNotNull("could login username: " + firstTimeUserName + " but he was never signed up", model.getUserFromCookie(login.getCookie()));
    }

    @Test(expected = UsecaseException.class)
    public void userExists_badmail_userNotLoggedIn_2_3() {
        // arrange
        String invalidUserName = UserDetailForTest.USERNAME_INVALID.getValue();
        Login login = new Login(new SafeObject<Model>(model), invalidUserName, UserDetailForTest.PASSWORD_VALID.getValue());

        // act
        login.perform();

        // assert
        assertNull("could login username: " + invalidUserName + " but he has invalid username", login.getCookie());
        assertNotNull("could login username: " + invalidUserName + " but he has invalid username", model.getUserFromCookie(login.getCookie()));
    }

    @Test(expected = UsecaseException.class)
    public void userExists_passwordIsNull_userNotLoggedIn_2_4() {
        // arrange
        Login login = new Login(new SafeObject<Model>(model), userName, null);

        // act
        login.perform();

        // assert
        assertNull("could login username: " + userName + " but he had null password", login.getCookie());
        assertNotNull("could login username: " + userName + " but he has null password", model.getUserFromCookie(login.getCookie()));
    }

    @Test(expected = LoginException.class)
    public void userExists_passwordIncorrect_userNotLoggedIn_2_5() {
        // arrange
        String incorrectPassword = UserDetailForTest.PASSWORD_INCORRECT.getValue();
        Login login = new Login(new SafeObject<Model>(model), userName, incorrectPassword);

        // act
        login.perform();

        // assert
        assertNull("could login username: " + userName + " but he had wrong password", login.getCookie());
        assertNotNull("could login username: " + userName + " but he has wrong password", model.getUserFromCookie(login.getCookie()));
    }
}
