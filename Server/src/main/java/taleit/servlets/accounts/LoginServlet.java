package taleit.servlets.accounts;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import taleit.ioc.TaleItServer;
import org.json.JSONObject;
import taleit.servlets.TaleitServlet;
import taleit.usecases.users.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet( name = "LoginServlet", description = "Login servlet", urlPatterns = {"/rest/accounts/login"} )
public class LoginServlet extends TaleitServlet{
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


        String content = CharStreams.toString(new InputStreamReader(request.getInputStream(), Charsets.UTF_8));
        JSONObject jsonObject = new JSONObject(content);

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String facebookAccessToken = null;
        //optional json parameter facebook access token
        if (jsonObject.has(KEY_FACEBOOK_ACCESS_TOKEN)) {
            facebookAccessToken = jsonObject.getString(KEY_FACEBOOK_ACCESS_TOKEN);
        }
        Login login = new Login(TaleItServer.Instance.getSafeModel(), username, password);
        login.setFacebookAccessToken(facebookAccessToken);

        login.perform();

        JSONObject responseJson = new JSONObject().put("cookie", login.getCookie());

        return responseJson;
    }
}
