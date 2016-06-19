package servlets.accounts;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import exceptions.SignUpException;
import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.RawAPIResponse;
import facebook4j.auth.AccessToken;
import facebook4j.conf.ConfigurationBuilder;
import ioc.Server;
import org.json.JSONObject;
import servlets.TaleitServlet;
import usecases.users.Login;
import usecases.users.SignUp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;

@WebServlet( name = "FacebookLoginServlet", description = "FaceBookLogin servlet", urlPatterns = {"/rest/accounts/fblogin"} )
public class FacebookLoginServlet extends TaleitServlet{
    @Override
    public void init() throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }

    @Override
    protected JSONObject handle(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        final String KEY_FACEBOOK_ACCESS_TOKEN = "facebookAccessToken";
        final String KEY_FACEBOOK_ID = "facebookId";
        final String APP_ID = "136173363469093";
        final String APP_SECRET = "e125df335c1aafb0e35facb8f4cf27d8";

        String content = CharStreams.toString(new InputStreamReader(request.getInputStream(), Charsets.UTF_8));
        JSONObject jsonObject = new JSONObject(content);

        String facebookAccessToken = jsonObject.getString(KEY_FACEBOOK_ACCESS_TOKEN);
        String facebookId = jsonObject.getString(KEY_FACEBOOK_ID);

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthAppId(APP_ID)
                .setOAuthAppSecret(APP_SECRET)
                .setOAuthAccessToken(APP_ID + "|" + APP_SECRET)
                .setOAuthPermissions("email");
        FacebookFactory ff = new FacebookFactory(cb.build());
        Facebook facebook = ff.getInstance();
        RawAPIResponse res = facebook.callGetAPI("/debug_token?input_token=" + facebookAccessToken + "&access_token=" + APP_ID + "|" + APP_SECRET);
        facebook4j.internal.org.json.JSONObject fbresjson = res.asJSONObject();
        facebook4j.internal.org.json.JSONObject data = fbresjson.getJSONObject("data");
        System.out.println(fbresjson);
        if (!(data.getString("app_id").equals(APP_ID) || data.getString("user_id").equals(facebookId))){
            JSONObject responseJson = new JSONObject().put("hacker", "baddddd hacker!");
            return responseJson;
        }

        facebook.setOAuthAccessToken(new AccessToken(facebookAccessToken));
        res = facebook.callGetAPI("/me?fields=email,name&" + facebookAccessToken);
        fbresjson = res.asJSONObject();
        String name = fbresjson.getString("name");
        String username = fbresjson.getString("email");

        SignUp signup = new SignUp(Server.Instance.getSafeModel(), username, Integer.toString((new SecureRandom()).nextInt()));
        signup.setFacebookId(facebookId);
        signup.setName(name);
        try {
            signup.perform();

        }catch (SignUpException e){
            //no need for sign up
        }

        Login login = new Login(Server.Instance.getSafeModel(), username);
        login.setFacebookAccessToken(facebookAccessToken);
        login.setFacebookAuthorized();
        login.perform();

        // verify facebook, check if user exists, and log him in , else singup, get info and login

        JSONObject responseJson = new JSONObject().put("cookie", login.getCookie());

        return responseJson;
    }
}
