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
public class SignUpTest {
    @Test
    public void nouser_validData_userAdded() {
        // arrange
        Model model = new Model();
        String sharon = "Sharon@gmail.com";
        UseCase signUp = new SignUp(new SafeObject<Model>(model), sharon, "123456");

        // act
        signUp.perform();

        // assert
        Assert.assertTrue(model.userExists(sharon));
    }

    @Test(expected = SignUpException.class)
    public void userExists_validData_userNotAdded() {
        // arrange
        Model model = new Model();
        String sharon = "Sharon@gmail.com";
        UseCase signUp = new SignUp(new SafeObject<Model>(model), sharon, "123456");

        // act
        signUp.perform();
        signUp.perform();

        // assert
        Assert.assertTrue(model.userExists(sharon));
    }

    @Test(expected = SignUpException.class)
    public void nouser_badmail_userNotAdded(){
        // arrange
        Model model = new Model();
        String sharon = "Sharon";
        UseCase signUp = new SignUp(new SafeObject<Model>(model), sharon, "123456");

        // act
        signUp.perform();

        // assert
        Assert.assertFalse(model.userExists(sharon));
    }

    @Test(expected = SignUpException.class)
    public void nouser_nameIsNull_userNotAdded(){
        // arrange
        Model model = new Model();
        String sharon = null;
        UseCase signUp = new SignUp(new SafeObject<Model>(model), sharon, "123456");

        // act
        signUp.perform();

        // assert
        Assert.assertFalse(model.userExists(sharon));
    }

    @Test(expected = SignUpException.class)
    public void nouser_passwordIsNull_userNotAdded(){
        // arrange
        Model model = new Model();
        String sharon = "sharon@gmail.com";
        UseCase signUp = new SignUp(new SafeObject<Model>(model), sharon, null);

        // act
        signUp.perform();

        // assert
        Assert.assertFalse(model.userExists(sharon));
    }

    @Test(expected = SignUpException.class)
    public void nouser_passwordTooShort_userNotAdded(){
        // arrange
        Model model = new Model();
        String sharon = "sharon@gmail.com";
        UseCase signUp = new SignUp(new SafeObject<Model>(model), sharon, "1234");

        // act
        signUp.perform();

        // assert
        Assert.assertFalse(model.userExists(sharon));
    }
}
