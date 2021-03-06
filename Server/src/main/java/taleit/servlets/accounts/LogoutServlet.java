package taleit.servlets.accounts;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import taleit.ioc.TaleItServer;
import org.json.JSONObject;
import taleit.servlets.TaleitServlet;
import taleit.usecases.users.Logout;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet( name = "LogoutServlet", description = "Logout servlet", urlPatterns = {"/rest/accounts/logout"} )
public class LogoutServlet extends TaleitServlet{
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

        String cookie = jsonObject.getString("cookie");
        Logout logout = new Logout(TaleItServer.Instance.getSafeModel(), cookie);

        logout.perform();

        return null;
    }
}