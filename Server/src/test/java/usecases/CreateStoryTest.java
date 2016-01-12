package usecases;

import exceptions.LoginException;
import exceptions.UsecaseException;
import lang.SafeObject;
import model.Model;
import model.Story;
import org.junit.Before;
import org.junit.Test;
import usecases.Stories.CreateStory;
import usecases.users.Login;
import usecases.users.SignUp;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Kerzman on 12/23/2015.
 */
public class CreateStoryTest extends TestBase {

    final private String AUTHOR = "B.J. Rollin";
    final private String TITLE = "True Detective";
    final private String BODY = "Once upon a time i was a girl";

    @Test
    public void userAndTextValid() {
        CreateStory story = new CreateStory(new SafeObject<Model>(model), AUTHOR, TITLE, BODY);
        story.perform();
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
