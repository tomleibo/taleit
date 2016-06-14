package usecases.users;

import exceptions.LoginException;
import exceptions.LogoutException;
import lang.SafeObject;
import model.Model;
import model.User;

public class Login extends UserUseCase {
    String cookie = null;
    protected String facebookAccessToken;

    public Login(SafeObject<Model> context, String username, String password) {
        super(context, username, password);
        facebookAccessToken = null;
    }

    public void perform(Model model) {
        if (!userExists(model)){
            throw new LoginException("User does not exists");
        }

        cookie = model.loginUser(username, password);
        User user= model.getUserFromCookie(cookie);
        user.setFacebookAccessToken(facebookAccessToken);

    }

    public String getCookie(){
        return this.cookie;
    }

    public void setFacebookAccessToken(String facebookAccessToken) {
        this.facebookAccessToken = facebookAccessToken;
    }

}

