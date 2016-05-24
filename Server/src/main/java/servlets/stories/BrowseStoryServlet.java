package servlets.stories;

import ioc.Server;
import model.Story;
import org.json.JSONArray;
import org.json.JSONObject;
import servlets.TaleitServlet;
import usecases.Stories.BrowseStory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gur on 1/16/2016.
 */
@WebServlet( name = "BrowseStoryServlet", description = "Browse story servlet", urlPatterns = {"/rest/stories/browse"} )
public class BrowseStoryServlet extends TaleitServlet {
    @Override
    public void init() throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }

    @Override
    protected JSONObject handle(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String category = request.getParameter("category");
        BrowseStory usecase = new BrowseStory(Server.Instance.getSafeModel(), category);

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
            jsonStory.put("category", story.getCategory().getValue());
            jsonStory.put("root", jsonRoot);
            String imageName = story.getTitle().replace("'", "").replace(" ", "_").toLowerCase();
            String imageURL = "http://localhost:8080/resources/stories/%s.png";
            jsonStory.put("image", String.format(imageURL, imageName));

            stories.put(jsonStory);
        }
        JSONObject responseJson = new JSONObject();
        responseJson.put("stories", stories);

        return responseJson;
    }
}