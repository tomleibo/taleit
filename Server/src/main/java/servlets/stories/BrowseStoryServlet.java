package servlets.stories;

import ioc.Server;
import model.Story;
import org.json.JSONArray;
import org.json.JSONObject;
import usecases.Stories.BrowseStory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gur on 1/16/2016.
 */
@WebServlet( name = "BrowseStoryServlet", description = "Browse story servlet", urlPatterns = {"/rest/stories/browse"} )
public class BrowseStoryServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            BrowseStory usecase = new BrowseStory(Server.Instance.getSafeModel());

            usecase.perform();
            JSONArray stories = new JSONArray();
            for (Story story: usecase.getStories()){
                JSONObject jsonRoot = new JSONObject();
                jsonRoot.put("id", story.getRoot().getId());
                jsonRoot.put("title", story.getRoot().getTitle());
                jsonRoot.put("text", story.getRoot().getText());
                jsonRoot.put("author", story.getUser().getUsername());

                JSONObject jsonStory = new JSONObject();
                jsonStory.put("id", story.getId());
                jsonStory.put("title", story.getTitle());
                jsonStory.put("root", jsonRoot);

                stories.put(jsonStory);
            }
            JSONObject responseJson = new JSONObject();
            responseJson.put("stories", stories);

            response.getWriter().print(responseJson);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch (Throwable wtf){
            response.getWriter().print(wtf);
            //TODO: add relevant error?
            //TODO: print relevant error to stream?
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}