package servlets.stories;

import ioc.Server;
import model.Paragraph;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import servlets.TaleitServlet;
import usecases.Stories.ViewStory;

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

        ViewStory usecase = new ViewStory(Server.Instance.getSafeModel(), storyId, paragraphId);

        usecase.perform();
        Paragraph paragraph = usecase.getParagraph();

        //self
        JSONObject responseJson = parseParagraphToJson(paragraph);

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
