package servlets.stories;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import ioc.Server;
import model.Paragraph;
import model.Story;
import org.json.JSONArray;
import org.json.JSONObject;
import servlets.TaleitServlet;
import usecases.Stories.BrowseStory;
import usecases.Stories.CreateStory;
import usecases.Stories.ViewStory;
import usecases.UseCase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

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

        //children
        if (!paragraph.getChildren().isEmpty()) {
            for (Paragraph child : paragraph.getChildren()) {
                JSONObject jsonChild = new JSONObject();
                jsonChild.put("id", child.getId());
                jsonChild.put("title", child.getTitle());
                jsonChild.put("text", child.getText());
                jsonChild.put("author", child.getUser().getUsername());
            }
            JSONArray children = new JSONArray();
            responseJson.put("children", children);
        }

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

        return responseJson;
    }
}
