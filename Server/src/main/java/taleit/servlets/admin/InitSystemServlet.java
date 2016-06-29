package taleit.servlets.admin;

import taleit.ioc.TaleItServer;
import org.json.JSONObject;
import taleit.servlets.TaleitServlet;
import taleit.usecases.InitSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gur on 4/27/2016.
 */
@WebServlet( urlPatterns = {"/rest/admin/init/*"} )
public class InitSystemServlet extends TaleitServlet {
    @Override
    public void init() throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }

    @Override
    protected JSONObject handle(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        InitSystem usecase = new InitSystem(TaleItServer.Instance.getSafeModel());

        usecase.perform();

        return new JSONObject();
    }
}
