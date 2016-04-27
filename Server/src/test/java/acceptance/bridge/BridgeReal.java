package acceptance.bridge;

import exceptions.InvalidUseCaseParameterException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
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
            if (responseJson.has("cookie") && responseJson.getString("cookie").length() > 0) {
                cookie = responseJson.getString("cookie");
                return true;
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

    public String createStory(String title, String rootTitle, String rootText){
         LocalhostClient client = new LocalhostClient();
        try {

             JSONObject rootParagrapth = new JSONObject()
                     .put("title", rootTitle)
                     .put("text", rootText);
             JSONObject content = new JSONObject()
                     .put("cookie", cookie)
                     .put("title", title)
                     .put("rootParagraph", rootParagrapth);
             HttpResponse response = client.makeApiPostRequest("/rest/stories/create", content.toString());

             if (response.getStatusLine().getStatusCode() != HTTP_OK) {
                 return null;
             }

             JSONObject responseJson = new JSONObject(EntityUtils.toString(response.getEntity()));
             if (responseJson.has("id") && responseJson.getString("id").length() > 0){
                 return responseJson.getString("id");
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

            Collection<String> result = new ArrayList<String>();

            for (int i = 0; i < responseJson.getJSONArray("stories").length(); i++){
                JSONObject storyJson = (JSONObject) responseJson.getJSONArray("stories").get(i);
                String id = storyJson.getString("id");
                result.add(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ArrayList<String>();
    }

    public String createParagraph(String storyNumber, String paragraphTitle, String paragraphText) {
        return null;
    }

    public boolean isParagraphExists(String storyNumber, String i) {
        return false;
    }
}
