package usecases.users;

import exceptions.SignUpException;
import lang.SafeObject;
import model.Model;
import model.User;

/**
 * Created by gur on 12/23/2015.
 */
public class SignUp extends UserUseCase {
    protected String facebookId;

    public SignUp(SafeObject<Model> context, String username, String password) {
        super(context, username, password);
        facebookId = null;
    }

    public void perform(Model model) {
        if (userExists(model)){
            throw new SignUpException("User with this email already exists");
        }

        User user = new User(username, password);
        user.setFacebookId(facebookId);

        model.addUser(user);
    }

    public void setFacebookId(String facebookId){
        this.facebookId = facebookId;
    }
}