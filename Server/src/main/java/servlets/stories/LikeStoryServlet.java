package servlets.stories;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import ioc.Server;
import org.json.JSONObject;
import servlets.TaleitServlet;
import usecases.Stories.ContinueStory;
import usecases.Stories.LikeStory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet( name = "LikeStoryServlet", description = "Like story servlet", urlPatterns = {"/rest/stories/like"} )
public class LikeStoryServlet extends TaleitServlet {
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
        String storyId = jsonObject.getString("storyId");

        LikeStory usecase = new LikeStory(Server.Instance.getSafeModel(), storyId, cookie);

        usecase.perform();

        return new JSONObject().put("Story change", "like added");
    }
}