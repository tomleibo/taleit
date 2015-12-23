package usecases;

import exceptions.SignUpException;
import exceptions.UsecaseException;
import lang.SafeObject;
import model.Model;
import model.User;

import java.util.regex.Pattern;

/**
 * Created by gur on 12/23/2015.
 */
public class SignUp extends ActionUseCase {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    final String username;
    final String password;

    protected SignUp(SafeObject<Model> context, String username, String password) {
        super(context);

        this.username = username;
        this.password = password;
    }

    @Override
    protected void pre() {
        if (! validateUsername()){
            throw new SignUpException("Username does not match email pattern");
        }

        if (! validatePassword()){
            throw new SignUpException("Password too short. use atleast 6 characters");
        }
    }

    public void perform(Model model) {
        if (! validateNoUser(model)){
            throw new SignUpException("User with this email already exists");
        }

        model.addUser(new User(username, password));
    }

    boolean validateNoUser(Model model){
        return model.userExists(username) == false;
    }

    boolean validatePassword() {
        return password != null && password.length() >= MIN_PASSWORD_LENGTH;
    }

    boolean validateUsername() {
        if (this.username == null){
            return false;
        }

        return PATTERN.matcher(username).matches();
    }
}
