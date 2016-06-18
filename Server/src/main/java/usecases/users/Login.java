package usecases.users;

import exceptions.LoginException;
import exceptions.LogoutException;
import lang.SafeObject;
import model.Model;
import model.User;

public class Login extends UserUseCase {
    String cookie = null;
    protected String facebookAccessToken;
    private boolean FacebookAuthorized = false;

    public Login(SafeObject<Model> context, String username, String password) {
        super(context, username, password);
        facebookAccessToken = null;
    }

    public Login(SafeObject<Model> context, String username) {
        super(context, username, "blabla");
        facebookAccessToken = null;
    }

    public void perform(Model model) {
        if (!userExists(model)){
            throw new LoginException("User does not exists");
        }

        if (this.FacebookAuthorized){
            cookie = model.loginUser(username);
        }else {
            cookie = model.loginUser(username, password);
        }
        User user = model.getUserFromCookie(cookie);
        user.setFacebookAccessToken(facebookAccessToken);

    }

    public String getCookie(){
        return this.cookie;
    }

    public void setFacebookAccessToken(String facebookAccessToken) {
        this.facebookAccessToken = facebookAccessToken;
    }

    public void setFacebookAuthorized() {
        this.FacebookAuthorized = true;
    }
}

