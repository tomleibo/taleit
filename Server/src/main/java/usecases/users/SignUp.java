package usecases.users;

import exceptions.SignUpException;
import lang.SafeObject;
import model.Model;
import model.User;

/**
 * Created by gur on 12/23/2015.
 */
public class SignUp extends UserUseCase {
    public SignUp(SafeObject<Model> context, String username, String password) {
        super(context, username, password);
    }

    public void perform(Model model) {
        if (validateUserExists(model)){
            throw new SignUpException("User with this email already exists");
        }

        model.addUser(new User(username, password));
    }
}

