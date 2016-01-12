package usecases.users;

import lang.SafeObject;
import model.Model;
import usecases.ActionUseCase;

public class Logout extends ActionUseCase {
    String cookie = null;

    public Logout(SafeObject<Model> context, String cookie) {
        super(context);

        this.cookie = cookie;
    }

    public void perform(Model model) {
        model.logoutUser(cookie);
    }
}