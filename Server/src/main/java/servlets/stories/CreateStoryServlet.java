package servlets.stories;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import ioc.Server;
import model.Paragraph;
import model.User;
import org.json.JSONObject;
import usecases.Stories.CreateStory;
import usecases.UseCase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by gur on 1/16/2016.
 */
@WebServlet( name = "CreateStoryServlet", description = "Create story servlet", urlPatterns = {"/rest/stories/create"} )
public class CreateStoryServlet extends HttpServlet{
    @Override
    public void init() throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String content = CharStreams.toString(new InputStreamReader(request.getInputStream(), Charsets.UTF_8));
            JSONObject jsonObject = new JSONObject(content);

            String cookie = jsonObject.getString("cookie"); //TODO: maybe get cookie from cookieStore/cookieJar ?
            String title = jsonObject.getString("title");
            String rootParagraphText = jsonObject.getJSONObject("rootParagraph").getString("text");
            String rootParagraphTitle = jsonObject.getJSONObject("rootParagraph").getString("title");
            UseCase createStory = new CreateStory(Server.Instance.getSafeModel(), title, cookie, rootParagraphTitle, rootParagraphText);

            createStory.perform();

            response.setStatus(HttpServletResponse.SC_OK);
            //TODO: return story id to stream?
        }
        catch (Throwable wtf){
            wtf.printStackTrace();

            //TODO: add relevant error?
            //TODO: print relevant error to stream?
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}