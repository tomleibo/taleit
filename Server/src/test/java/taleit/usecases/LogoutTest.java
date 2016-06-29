package taleit.usecases;

import taleit.lang.SafeObject;
import taleit.model.Model;
import org.junit.Before;
import org.junit.Test;
import taleit.usecases.core.SignedUpTestBase;
import taleit.usecases.users.Login;
import taleit.usecases.users.Logout;

import static junit.framework.Assert.assertFalse;

/**
 * Created by Kerzman on 12/23/2015.
 */
public class LogoutTest extends SignedUpTestBase {
    private Login login = null;

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void userLoggedIn_validData_userLoggedOut_3_1() {
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
