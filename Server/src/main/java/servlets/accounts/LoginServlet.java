package servlets.accounts;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import ioc.Server;
import org.json.JSONObject;
import usecases.users.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet( name = "LoginServlet", description = "Login servlet", urlPatterns = {"/rest/accounts/login"} )
public class LoginServlet extends HttpServlet{
    @Override
    public void init() throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getOutputStream().print("waaaaaaaaaaaaaaaaaaaaaaaaaaaaaar");
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String content = CharStreams.toString(new InputStreamReader(request.getInputStream(), Charsets.UTF_8));
            JSONObject jsonObject = new JSONObject(content);

            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            Login login = new Login(Server.Instance.getSafeModel(), username, password);

            login.perform();

            JSONObject responseJson = new JSONObject()
                .put("cookie", login.getCookie());
            response.getOutputStream().print(responseJson.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch (Throwable wtf){
            wtf.printStackTrace();

            //TODO: add relevant error?
            //TODO: print relevant error to stream?
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
