package servlets.stories;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import ioc.Server;
import org.json.JSONObject;
import servlets.TaleitServlet;
import usecases.Stories.ContinueStory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet( name = "ContinueStoryServlet", description = "Continue story servlet", urlPatterns = {"/rest/stories/continue"} )
public class ContinueStoryServlet extends TaleitServlet {
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

        String cookie = jsonObject.getString("cookie"); //TODO: maybe get cookie from cookieStore/cookieJar ?
        String title = jsonObject.getString("title");
        String text = jsonObject.getString("text");
        String paragraphId = jsonObject.getString("paragraphId");
        String storyId = jsonObject.getString("storyId");

        ContinueStory usecase = new ContinueStory(Server.Instance.getSafeModel(), storyId, paragraphId, title, text, cookie);

        usecase.perform();

        return null;
    }
}