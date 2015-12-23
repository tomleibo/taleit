package usecases.users;

import exceptions.LogoutException;
import lang.SafeObject;
import model.Model;
import usecases.ActionUseCase;

public class Logout extends ActionUseCase {
    String cookie = null;

    protected Logout(SafeObject<Model> context, String cookie) {
        super(context);

        this.cookie = cookie;
    }

    public void perform(Model model) {
        model.logoutUser(cookie);
    }
}