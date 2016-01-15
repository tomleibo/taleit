package usecases;

import lang.SafeObject;
import model.Model;
import org.junit.Before;
import org.junit.Test;
import usecases.core.TestBase;
import usecases.users.Login;
import usecases.users.Logout;
import usecases.users.SignUp;
import usecases.utils.UserDetailForTest;

import static junit.framework.Assert.assertFalse;

/**
 * Created by Kerzman on 12/23/2015.
 */
public class LogoutTest extends TestBase {
    private Login login = null;
    private String userName;
    private String password;

    @Before
    public void setUp() {
        userName = UserDetailForTest.USERNAME_VALID.getValue();
        password = UserDetailForTest.PASSWORD_VALID.getValue();
        new SignUp(new SafeObject<Model>(model), userName, password).perform();
    }

    @Test
    public void userLoggedIn_validData_userLoggedOut() {
        // arrange
        login = new Login(new SafeObject<Model>(model), userName, password);
        login.perform();
        Logout logout = new Logout(new SafeObject<Model>(model), login.getCookie());

        // act
        logout.perform();

        // assert
        assertFalse("could not perform logout to user: " + userName, model.isUserLoggedIn(userName));
    }

}
