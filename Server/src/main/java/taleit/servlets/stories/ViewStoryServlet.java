package taleit.servlets.stories;

import taleit.ioc.TaleItServer;
import taleit.model.Paragraph;
import taleit.model.Story;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import taleit.servlets.TaleitServlet;
import taleit.usecases.Stories.ViewStory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( name = "ViewStoryServlet", description = "View story servlet", urlPatterns = {"/rest/stories/view/*"} )
public class ViewStoryServlet extends TaleitServlet {
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
        String storyId = request.getParameter("storyId");
        String paragraphId = request.getParameter("paragraphId");

        ViewStory usecase = new ViewStory(TaleItServer.Instance.getSafeModel(), storyId, paragraphId);

        usecase.perform();
        Paragraph paragraph = usecase.getParagraph();

        //self
        JSONObject responseJson = parseParagraphToJson(paragraph);

        //containing story
        Story story = usecase.getStory();
        JSONObject jsonStory = new JSONObject();
        jsonStory.put("id", story.getId());
        jsonStory.put("title", story.getTitle());
        jsonStory.put("category", story.getCategory().getValue());
        String imageName = story.getTitle().replace("'", "").replace(" ", "_").toLowerCase();
        String imageURL = "/resources/stories/%s.png";
        jsonStory.put("image", String.format(imageURL, imageName));
        responseJson.put("story", jsonStory);


        //father
        Paragraph father = paragraph.getFather();
        if (father != null) {
            responseJson.put("father", parseParagraphToJson(father));
        }

        //children
        if (paragraph.hasChildren()) {
            responseJson.put("children", getChildrenTree(paragraph, paragraphId == null));
        }

        return responseJson;
    }

    private JSONArray getChildrenTree(Paragraph root, boolean recursive) throws Throwable{
        JSONArray children = new JSONArray();
        for (Paragraph child : root.getChildren()) {
            JSONObject jsonChild = parseParagraphToJson(child);
            if (recursive && child.hasChildren()) {
                jsonChild.put("children", getChildrenTree(child, true));
            }
            children.put(jsonChild);
        }
        return children;
    }

    private JSONObject parseParagraphToJson(Paragraph child) throws JSONException {
        JSONObject jsonChild = new JSONObject();
        jsonChild.put("id", child.getId());
        jsonChild.put("title", child.getTitle());
        jsonChild.put("text", child.getText());
        jsonChild.put("author", child.getUser().getUsername());
        String name  = child.getUser().getName();
        if (name != null){
            jsonChild.put("name", name);
        }
        jsonChild.put("userFacebookId", child.getUser().getFacebookId());
        return jsonChild;
    }
}
