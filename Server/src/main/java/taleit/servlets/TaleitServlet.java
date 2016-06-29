package taleit.servlets;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

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

    protected void sendError(HttpServletResponse response, Throwable wtf) throws IOException, JSONException {
        wtf.printStackTrace(response.getWriter());

        JSONObject content = new JSONObject();

        try (StringWriter errors = new StringWriter()){
            wtf.printStackTrace(new PrintWriter(errors));
            content.put("error", errors.toString());
        }

        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        String errorText = content.toString();
        response.getWriter().print(errorText);
    }

    protected void doRequest(HttpServletRequest request, HttpServletResponse response){
        try {
            sendSuccess(response, handle(request, response));
        } catch (Throwable throwable) {
            try {
                sendError(response, throwable);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract JSONObject handle(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}