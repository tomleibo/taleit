package servlets;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gur on 3/11/2016.
 */
public abstract class TaleitServlet extends HttpServlet {
    protected void sendSuccess(HttpServletResponse response, JSONObject data) throws JSONException, IOException {
        JSONObject content = new JSONObject();
        content.put("data", data);

        response.getWriter().print(content.toString());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected void sendError(HttpServletResponse response, Throwable wtf) throws IOException {
        wtf.printStackTrace(response.getWriter());
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    protected void doRequest(HttpServletRequest request, HttpServletResponse response){
        try {
            sendSuccess(response, handle(request, response));
        } catch (Throwable throwable) {
            try {
                sendError(response, throwable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract JSONObject handle(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}