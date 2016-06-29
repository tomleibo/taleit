package taleit.servlets.stories;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import taleit.ioc.TaleItServer;
import taleit.model.Categories;
import org.json.JSONObject;
import taleit.servlets.TaleitServlet;
import taleit.usecases.Stories.CreateStory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by gur on 1/16/2016.
 */
@WebServlet( name = "CreateStoryServlet", description = "Create story servlet", urlPatterns = {"/rest/stories/create"} )
public class CreateStoryServlet extends TaleitServlet{
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
        Categories category = Categories.getCategoryByString(jsonObject.getString("category"));
        String rootParagraphText = jsonObject.getJSONObject("rootParagraph").getString("text");
        String rootParagraphTitle = jsonObject.getJSONObject("rootParagraph").getString("title");
        CreateStory createStory = new CreateStory(TaleItServer.Instance.getSafeModel(), title, cookie, rootParagraphTitle,
                                                  rootParagraphText, category);

        createStory.perform();

        JSONObject result = new JSONObject();
        result.put("storyId", createStory.getStory().getId());
        result.put("rootId", createStory.getStory().getRoot().getId());
        return result;
    }
}