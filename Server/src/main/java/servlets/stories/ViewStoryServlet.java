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

        JSONObject responseJson = new JSONObject();

        //self
        JSONObject paragraphDetails = new JSONObject();
        paragraphDetails.put("Id", paragraph.getId());
        paragraphDetails.put("title", paragraph.getText());
        paragraphDetails.put("text", paragraph.getTitle());
        paragraphDetails.put("author", paragraph.getUser().getUsername());
        responseJson.put("paragraph", paragraphDetails);

        //father
        Paragraph father = paragraph.getFather();
        if (father != null) {
            JSONObject fatherDetails = new JSONObject();
            fatherDetails.put("Id", father.getId());
            fatherDetails.put("title", father.getText());
            fatherDetails.put("text", father.getTitle());
            fatherDetails.put("author", father.getUser().getUsername());
            responseJson.put("father", fatherDetails);
        }

        //children
        if (!paragraph.getChildren().isEmpty()) {
            JSONArray children;
            if (paragraphId != null) {
                children = new JSONArray();
                for (Paragraph child : paragraph.getChildren()) {
                    JSONObject jsonChild = parseJsonchild(child);
                    children.put(jsonChild);
                }
            } else {
                children = getChildrenTree(paragraph);
            }
            responseJson.put("children", children);
        }

        return responseJson;
    }

    private JSONArray getChildrenTree(Paragraph root) throws Throwable{
        JSONArray children = new JSONArray();
        for (Paragraph child : root.getChildren()) {
            JSONObject jsonChild = parseJsonchild(child);
            if (!child.getChildren().isEmpty()) {
                jsonChild.put("children", getChildrenTree(child));
            }
            children.put(jsonChild);
        }
        return children;
    }

    private JSONObject parseJsonchild(Paragraph child) throws JSONException {
        JSONObject jsonChild = new JSONObject();
        jsonChild.put("id", child.getId());
        jsonChild.put("title", child.getTitle());
        jsonChild.put("text", child.getText());
        jsonChild.put("author", child.getUser().getUsername());
        return jsonChild;
    }
}
