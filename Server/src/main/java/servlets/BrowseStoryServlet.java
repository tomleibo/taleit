package servlets;

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

            response.setStatus(HttpServletResponse.SC_OK);

            JSONObject responseJson = new JSONObject();
            JSONArray stories = new JSONArray();
            responseJson.put("stories", stories);
            for (Story story: usecase.getStories()){
                JSONObject jsonStory = new JSONObject();
                jsonStory.put("id", story.getId());
                jsonStory.put("title", story.getTitle());
                jsonStory.put("author", story.getUser().getUsername());
                JSONObject jsonRoot = new JSONObject();
                jsonStory.put("root", jsonRoot);
                jsonRoot.put("id", story.getRoot().getId());
                jsonRoot.put("title", story.getRoot().getTitle());
                jsonRoot.put("text", story.getRoot().getText());
            }

            response.getOutputStream().print(responseJson.toString());
        }
        catch (Throwable wtf){
            wtf.printStackTrace();

            //TODO: add relevant error?
            //TODO: print relevant error to stream?
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}