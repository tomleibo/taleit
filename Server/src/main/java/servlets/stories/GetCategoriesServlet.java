package servlets.stories;

import model.Categories;
import org.json.JSONArray;
import org.json.JSONObject;
import servlets.TaleitServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gur on 1/16/2016.
 */
@WebServlet( name = "GetCategoriesServlet", description = "Get Categories Servlet", urlPatterns =
        {"/rest/stories/categories"} )
public class GetCategoriesServlet extends TaleitServlet {
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
        JSONArray categoriesJsn= new JSONArray();

        for (Categories category: Categories.values()){
            JSONObject jsonRoot = new JSONObject();
            jsonRoot.put("name", category.getValue());
            jsonRoot.put("image", category.getImageUrl());
            jsonRoot.put("hover", category.getImageHoverUrl());

            categoriesJsn.put(jsonRoot);
        }
        JSONObject responseJson = new JSONObject();
        responseJson.put("categories", categoriesJsn);

        return responseJson;
    }
}