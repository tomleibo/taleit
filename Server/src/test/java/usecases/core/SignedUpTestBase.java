package usecases.core;

import lang.SafeObject;
import model.Model;
import usecases.users.SignUp;
import usecases.utils.UserDetailForTest;

/**
 * Created by Kerzman on 12/23/2015.
 */
public class SignedUpTestBase extends TestBase{
    protected String userName;
    protected String password;

    public void setUp(){
        userName = UserDetailForTest.USERNAME_VALID.getValue();
        password = UserDetailForTest.PASSWORD_VALID.getValue();
        new SignUp(new SafeObject<Model>(model), userName,password).perform();
    }
}
