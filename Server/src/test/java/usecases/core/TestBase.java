package usecases.core;

import model.Model;
import usecases.utils.UserDetailForTest;

/**
 * Created by Kerzman on 12/23/2015.
 */
public class TestBase {
    protected String userName = UserDetailForTest.USERNAME_VALID.getValue();
    protected String password = UserDetailForTest.PASSWORD_VALID.getValue();

    public Model model = new Model();
}
