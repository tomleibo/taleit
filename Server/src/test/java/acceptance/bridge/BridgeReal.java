package acceptance.bridge;

import model.Categories;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Kerzman on 12/23/2015.
 */
public class BridgeReal implements BridgeAPI {

    final int HTTP_OK = 200;

    String cookie = null;

    public boolean signUp(String userName, String password){
        LocalhostClient client = new LocalhostClient();
        JSONObject content = null;
        try {
            content = new JSONObject()
                    .put("username", userName)
                    .put("password", password);
        } catch (JSONException e) {
            throw new RuntimeException();
        }
        HttpResponse response = null;
        try {
            response = client.makeApiPostRequest("/rest/accounts/signup", content.toString());
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return response.getStatusLine().getStatusCode() == HTTP_OK ? true : false;
    }

    public boolean login(String userName, String password){
        LocalhostClient client = new LocalhostClient();
        try {
            JSONObject content = new JSONObject()
                    .put("username", userName)
                    .put("password", password);
            HttpResponse response = client.makeApiPostRequest("/rest/accounts/login", content.toString());

            if (response.getStatusLine().getStatusCode() != HTTP_OK) {
                return false;
            }

            JSONObject responseJson = new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONObject data = null;
            if (responseJson.has("data")) {
                data = responseJson.getJSONObject("data");
                if (data.has("cookie") && data.getString("cookie").length() > 0) {
                    cookie = data.getString("cookie");
                    return true;
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);

        }
        return false;
    }

    public boolean logout(){
        LocalhostClient client = new LocalhostClient();

        JSONObject content = null;
        try {
            content = new JSONObject()
                    .put("cookie", cookie);
            HttpResponse response = client.makeApiPostRequest("/rest/accounts/logout", content.toString());

            if (response.getStatusLine().getStatusCode() != HTTP_OK) {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        cookie = null;
        return true;
    }

    public String createStory(String title, String rootTitle, String rootText, String category){
         LocalhostClient client = new LocalhostClient();
        try {
            JSONObject rootParagrapth = new JSONObject()
                 .put("title", rootTitle)
                 .put("text", rootText);
            JSONObject content = new JSONObject()
                 .put("cookie", cookie)
                 .put("title", title)
                 .put("category", category)
                 .put("rootParagraph", rootParagrapth);
            HttpResponse response = client.makeApiPostRequest("/rest/stories/create", content.toString());
            if (response.getStatusLine().getStatusCode() != HTTP_OK) {
             return null;
            }

            JSONObject responseJson = new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONObject data = null;
            if (responseJson.has("data")) {
                data = responseJson.getJSONObject("data");
                if (data.has("storyId") && data.getString("storyId").length() > 0) {
                    return data.getString("storyId");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    return null;
    }

    public Collection<String> browseStories(){
        LocalhostClient client = new LocalhostClient();
        try{
            HttpResponse response = client.makeApiGetRequest("/rest/stories/browse");

            if (response.getStatusLine().getStatusCode() != HTTP_OK) {
                System.out.println(response.getStatusLine());
                return null;
            }

            JSONObject responseJson = new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONObject data = null;
            if (responseJson.has("data")) {
                data = responseJson.getJSONObject("data");
                Collection<String> result = new ArrayList<String>();
                for (int i = 0; i < data.getJSONArray("stories").length(); i++) {
                    JSONObject storyJson = (JSONObject) data.getJSONArray("stories").get(i);
                    String id = storyJson.getString("id");
                    result.add(id);
                }
                return result;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public String createParagraph(String storyNumber, String paragraphTitle, String paragraphText, String rootParagraphId) {
        LocalhostClient client = new LocalhostClient();
        try {

            JSONObject content = new JSONObject()
                    .put("cookie", cookie)
                    .put("title", paragraphTitle)
                    .put("text", paragraphText)
                    .put("paragraphId", rootParagraphId)
                    .put("storyId", storyNumber);

            HttpResponse response = client.makeApiPostRequest("/rest/stories/continue", content.toString());

            if (response.getStatusLine().getStatusCode() == HTTP_OK){
                JSONObject responseJson = new JSONObject(EntityUtils.toString(response.getEntity()));
                JSONObject data = null;
                if (responseJson.has("data")) {
                    data = responseJson.getJSONObject("data");
                    if (data.has("paragraphId") && data.getString("paragraphId").length() > 0) {
                        return data.getString("paragraphId");
                    }
                }
            }
            else if (response.getStatusLine().getStatusCode() == 500){
                String entity = EntityUtils.toString(response.getEntity());
                System.out.println(entity);
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isParagraphExists(String storyId, String paragraphId) {
        LocalhostClient client = new LocalhostClient();
        try{
            String uriAndParams = "/rest/stories/view" + "?" + "storyId=" + storyId + "&paragraphId=" + paragraphId;
            HttpResponse response = client.makeApiGetRequest(uriAndParams);

            if (response.getStatusLine().getStatusCode() != HTTP_OK) {
                System.out.println(response.getStatusLine());
                throw new RuntimeException();
            }

            JSONObject responseJson = new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONObject data = null;
            if (responseJson.has("data")) {
                data = responseJson.getJSONObject("data");
                return (data.getJSONObject("paragraph").getString("Id").equals(paragraphId));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean initServer (){
        LocalhostClient client = new LocalhostClient();
        try{
            HttpResponse response = client.makeApiGetRequest("/rest/admin/init");

            if (response.getStatusLine().getStatusCode() != HTTP_OK) {
                System.out.println(response.getStatusLine());
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public String getRootParagraph(String storyId) {
        LocalhostClient client = new LocalhostClient();
        try{
            HttpResponse response = client.makeApiGetRequest("/rest/stories/browse");

            if (response.getStatusLine().getStatusCode() != HTTP_OK) {
                System.out.println(response.getStatusLine());
                return null;
            }

            JSONObject responseJson = new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONObject data = null;
            if (responseJson.has("data")) {
                data = responseJson.getJSONObject("data");
                Collection<String> result = new ArrayList<>();
                for (int i = 0; i < data.getJSONArray("stories").length(); i++) {
                    JSONObject storyJson = (JSONObject) data.getJSONArray("stories").get(i);
                    String id = storyJson.getString("id");
                    if (storyId.equals(id)){
                        return storyJson.getJSONObject("root").getString("id");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
