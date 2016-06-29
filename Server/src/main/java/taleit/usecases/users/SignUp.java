package taleit.usecases.users;

import taleit.exceptions.SignUpException;
import taleit.lang.SafeObject;
import taleit.model.Model;
import taleit.model.User;

/**
 * Created by gur on 12/23/2015.
 */
public class SignUp extends UserUseCase {
    protected String facebookId;
    protected String name;

    public SignUp(SafeObject<Model> context, String username, String password) {
        super(context, username, password);
        facebookId = null;
        name = null;
    }

    public void perform(Model model) {
        if (userExists(model)){
            throw new SignUpException("User with this email already exists");
        }

        User user = new User(username, password);
        user.setFacebookId(facebookId);
        user.setName(name);

        model.addUser(user);
    }

    public void setFacebookId(String facebookId){
        this.facebookId = facebookId;
    }

    public void setName(String name) {
        this.name = name;
    }
}