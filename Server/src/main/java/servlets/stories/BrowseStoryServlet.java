package servlets.stories;

import ioc.Server;
import model.Paragraph;
import model.Story;
import org.json.JSONArray;
import org.json.JSONException;
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

            JSONObject jsonStory = new JSONObject();
            jsonStory.put("likes", story.getLikes());
            jsonStory.put("id", story.getId());
            jsonStory.put("title", story.getTitle());
            jsonStory.put("category", story.getCategory().getValue());
            String imageName = story.getTitle().replace("'", "").replace(" ", "_").toLowerCase();
            String imageURL = "http://localhost:8080/resources/stories/%s.png";
            jsonStory.put("image", String.format(imageURL, imageName));

            JSONObject paragraphTree = new JSONObject();
            buildChildrenTree(story.getRoot(), paragraphTree);
            jsonStory.put("root", paragraphTree);


            stories.put(jsonStory);
        }
        JSONObject responseJson = new JSONObject();
        responseJson.put("stories", stories);

        return responseJson;
    }

    private void buildChildrenTree(Paragraph node, JSONObject tree) throws JSONException {
        tree.put("id", node.getId());
        tree.put("title", node.getTitle());
        tree.put("text", node.getText());
        tree.put("author", node.getUser().getUsername());
        String name  = node.getUser().getName();
        if (name != null){
            tree.put("name", name);
        }
        tree.put("userFacebookId", node.getUser().getFacebookId());

        JSONArray children = new JSONArray();
        tree.put("children", children);
        for (Paragraph child: node.getChildren()){
            JSONObject childTree = new JSONObject();
            children.put(childTree);
            buildChildrenTree(child, childTree);
        }
    }
}