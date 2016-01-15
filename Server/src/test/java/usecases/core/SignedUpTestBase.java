package usecases.core;

import lang.SafeObject;
import model.Model;
import usecases.users.SignUp;
import usecases.utils.UserDetailForTest;

/**
 * Created by Kerzman on 12/23/2015.
 */
public class SignedUpTestBase extends TestBase {

    public void setUp() {
        new SignUp(new SafeObject<Model>(model), userName, password).perform();
    }
}
