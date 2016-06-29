package taleit.usecases.users;

import taleit.lang.SafeObject;
import taleit.model.Model;
import taleit.usecases.ActionUseCase;

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