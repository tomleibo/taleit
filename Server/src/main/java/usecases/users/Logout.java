package usecases.users;

import exceptions.LogoutException;
import lang.SafeObject;
import model.Model;

public class Logout extends UserUseCase {
    String cookie = null;

    protected Logout(SafeObject<Model> context, String username, String cookie) {
        super(context, username, "");

        this.cookie = cookie;
    }

    @Override
    protected void pre(){
        validateUsername();
    }

    public void perform(Model model) {
        if (!userExists(model)){
            throw new LogoutException("User does not exists");
        }

        model.logoutUser(username);
    }

    public String getCookie(){
        return this.cookie;
    }
}
