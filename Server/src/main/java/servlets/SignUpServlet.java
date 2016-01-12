package servlets;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import ioc.Server;
import org.json.JSONException;
import org.json.JSONObject;
import usecases.users.SignUp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by gur on 12/23/2015.
 */
@WebServlet( name = "SignUpServlet", description = "Sign up servlet", urlPatterns = {"/rest/actions/signup"} )
public class SignUpServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = CharStreams.toString(new InputStreamReader(request.getInputStream(), Charsets.UTF_8));

        try {
            JSONObject jsonObject = new JSONObject(content);

            SignUp usecase = new SignUp(Server.Instance.getSafeModel(), "", "");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}