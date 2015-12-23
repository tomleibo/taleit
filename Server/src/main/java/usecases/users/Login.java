package usecases.users;

import exceptions.LoginException;
import lang.SafeObject;
import model.Model;

public class Login extends UserUseCase {
    protected Login(SafeObject<Model> context, String username, String password) {
        super(context, username, password);
    }

    public void perform(Model model) {
        if (!validateUserExists(model)){
            throw new LoginException("User does not exists");
        }

        model.loginUser(username, password);
    }
}
