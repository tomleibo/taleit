package usecases;

import exceptions.SignUpException;
import junit.framework.Assert;
import lang.SafeObject;
import model.Model;
import org.junit.Test;
import usecases.users.SignUp;

/**
 * Created by gur on 12/23/2015.
 */
public class SignUpTest extends TestBase {
    @Test
    public void nouser_validData_userAdded() {
        // arrange
        String userName = UserDetailForTest.USERNAME_VALID.getValue();
        UseCase signUp = new SignUp(new SafeObject<Model>(model), userName, UserDetailForTest.PASSWORD_VALID.getValue());

        // act
        signUp.perform();

        // assert
        Assert.assertTrue("could not find username: " + userName, model.userExists(userName));
    }

    @Test(expected = SignUpException.class)
    public void userExists_validData_userNotAdded() {
        // arrange
        String userName = UserDetailForTest.USERNAME_VALID.getValue();
        UseCase signUp = new SignUp(new SafeObject<Model>(model), userName, UserDetailForTest.PASSWORD_VALID.getValue());

        // act
        signUp.perform();
        signUp.perform();

        // assert
        Assert.assertTrue(model.userExists(userName));
    }

    @Test(expected = SignUpException.class)
    public void nouser_badmail_userNotAdded() {
        // arrange
        String userName = UserDetailForTest.USERNAME_INVALID.getValue();
        UseCase signUp = new SignUp(new SafeObject<Model>(model), userName, UserDetailForTest.PASSWORD_VALID.getValue());

        // act
        signUp.perform();

        // assert
        Assert.assertFalse(model.userExists(userName));
    }

    @Test(expected = SignUpException.class)
    public void nouser_nameIsNull_userNotAdded() {
        // arrange
        String userName = null;
        UseCase signUp = new SignUp(new SafeObject<Model>(model), userName, "123456");

        // act
        signUp.perform();

        // assert
        Assert.assertFalse(model.userExists(userName));
    }

    @Test(expected = SignUpException.class)
    public void nouser_passwordIsNull_userNotAdded() {
        // arrange
        String userName = UserDetailForTest.USERNAME_VALID.getValue();
        UseCase signUp = new SignUp(new SafeObject<Model>(model), userName, null);

        // act
        signUp.perform();

        // assert
        Assert.assertFalse(model.userExists(userName));
    }

    @Test(expected = SignUpException.class)
    public void nouser_passwordTooShort_userNotAdded() {
        // arrange
        String userName = UserDetailForTest.USERNAME_VALID.getValue();
        UseCase signUp = new SignUp(new SafeObject<Model>(model), userName, UserDetailForTest.PASSWORD_TO_SHORT.getValue());

        // act
        signUp.perform();

        // assert
        Assert.assertFalse(model.userExists(userName));
    }
}
