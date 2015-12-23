package usecases.users;

import exceptions.SignUpException;
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
        if (! validateUsername()){
            throw new SignUpException("Username does not match email pattern");
        }

        if (! validatePassword()){
            throw new SignUpException("Password too short. use atleast 6 characters");
        }
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

    boolean validateUserExists(Model model){
        return model.userExists(username);
    }
}
