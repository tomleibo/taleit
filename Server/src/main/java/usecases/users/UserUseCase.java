package usecases.users;

import exceptions.UserException;
import lang.SafeObject;
import model.Model;
import usecases.ActionUseCase;

import java.util.regex.Pattern;

public abstract class UserUseCase extends ActionUseCase {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    protected final String username;
    protected final String password;

    protected UserUseCase(SafeObject<Model> context, String username, String password) {
        super(context);

        this.username = username;
        this.password = password;
    }

    @Override
    protected void pre() {
        validateUsername();
        validatePassword();
    }

    void validatePassword() {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH){
            throw new UserException("Password too short. use atleast 6 characters");
        }
    }

    void validateUsername() {
        if (username == null || PATTERN.matcher(username).matches() == false){
            throw new UserException("Username does not match email pattern");
        }
    }

    boolean userExists(Model model){
        return model.userExists(username);
    }
}