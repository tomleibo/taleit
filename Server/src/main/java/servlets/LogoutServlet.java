package servlets;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import ioc.Server;
import org.json.JSONObject;
import usecases.users.Logout;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by gur on 1/16/2016.
 */
@WebServlet( name = "LogoutServlet", description = "Logout servlet", urlPatterns = {"/rest/accounts/logout"} )
public class LogoutServlet extends HttpServlet{
    @Override
    public void init() throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String content = CharStreams.toString(new InputStreamReader(request.getInputStream(), Charsets.UTF_8));
            JSONObject jsonObject = new JSONObject(content);

            String cookie = jsonObject.getString("cookie");
            Logout logout = new Logout(Server.Instance.getSafeModel(), cookie);

            logout.perform();

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