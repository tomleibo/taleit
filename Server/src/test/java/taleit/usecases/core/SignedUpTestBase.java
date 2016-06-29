package taleit.usecases.core;

import taleit.lang.SafeObject;
import taleit.model.Model;
import taleit.usecases.users.SignUp;

/**
 * Created by Kerzman on 12/23/2015.
 */
public class SignedUpTestBase extends TestBase {

    public void setUp() {
        new SignUp(new SafeObject<Model>(model), userName, password).perform();
    }
}
