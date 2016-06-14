package servlets.accounts;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import ioc.Server;
import org.json.JSONObject;
import servlets.TaleitServlet;
import usecases.users.SignUp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet( name = "SignUpServlet", description = "Sign up servlet", urlPatterns = {"/rest/accounts/signup"} )
public class SignUpServlet extends TaleitServlet {

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
        String content = CharStreams.toString(new InputStreamReader(request.getInputStream(), Charsets.UTF_8));
        JSONObject jsonObject = new JSONObject(content);

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String facebookId = jsonObject.getString("facebookId");
        SignUp signup = new SignUp(Server.Instance.getSafeModel(), username, password);
        signup.setFacebookId(facebookId);

        signup.perform();

        return null;
    }
}