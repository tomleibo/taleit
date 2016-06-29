import taleit.model.Categories;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by gur on 3/11/2016.
 */
public class ManualTests {
    final String server;
    String cookie;
    private String appname = "/server.core-1.0-SNAPSHOT";

    public ManualTests(){
        this.server = "127.0.0.1";
    }

    public ManualTests(String server){
        this.server = server;
    }

    public JSONObject signup(String username, String password) throws IOException, JSONException {
        JSONObject content = new JSONObject();
        content.put("username", username);
        content.put("password", password);
        JSONObject object = new JSONObject(makeRequest("/rest/accounts/signup", content.toString(), "POST"));

        return object;
    }

    public JSONObject login(String username, String password) throws IOException, JSONException {
        JSONObject content = new JSONObject();
        content.put("username", username);
        content.put("password", password);
        JSONObject object = new JSONObject(makeRequest("/rest/accounts/login", content.toString(), "POST"));

        this.cookie = object.getJSONObject("data").getString("cookie");
        return object;
    }

    public JSONObject logout(String cookie) throws IOException, JSONException {
        JSONObject content = new JSONObject();
        content.put("cookie", cookie);
        JSONObject object = new JSONObject(makeRequest("/rest/accounts/logout", content.toString(), "POST"));

        this.cookie = null;
        return object;
    }

    public JSONObject browse() throws IOException, JSONException {
        JSONObject content = new JSONObject();
        JSONObject object = new JSONObject(makeRequest("/rest/stories/browse", content.toString(), "GET"));
        return object;
    }

    public JSONObject getCategories() throws IOException, JSONException {
        JSONObject content = new JSONObject();
        JSONObject object = new JSONObject(makeRequest("/rest/stories/categories", content.toString(), "GET"));
        return object;
    }

    public JSONObject create(String cookie, String title, String text, String rootTitle, String rootText, Categories
            category) throws
            IOException, JSONException{
        JSONObject content = new JSONObject();
        content.put("cookie", cookie);
        content.put("title", title);
        content.put("text", text);
        content.put("category", category.getValue());
        JSONObject root = new JSONObject();
        content.put("rootParagraph", root);
        root.put("text",rootText);
        root.put("title",rootTitle);

        String bla = makeRequest("/rest/stories/create", content.toString(), "POST");

        JSONObject object = new JSONObject(bla);
        return object;
    }


    public void update(String udid, String regkey, String address, String username) throws IOException, JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", new JSONObject());
        jsonObject.getJSONObject("data").put("device", new JSONObject());
        jsonObject.getJSONObject("data").getJSONObject("device").put("udid", udid);
        jsonObject.getJSONObject("data").getJSONObject("device").put("regkey", regkey);
        jsonObject.getJSONObject("data").getJSONObject("device").put("address", address);
        jsonObject.getJSONObject("data").getJSONObject("device").put("username", username);

        makeRequest("/api/rest/push/devices/update", jsonObject.toString(), "POST");
    }

    public void push(String udid, String title, String message) throws IOException, JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", new JSONObject());
        jsonObject.getJSONObject("data").put("command", new JSONObject());
        jsonObject.getJSONObject("data").getJSONObject("command").put("udid", udid);
        jsonObject.getJSONObject("data").getJSONObject("command").put("type", "popup");
        jsonObject.getJSONObject("data").getJSONObject("command").put("title", title);
        jsonObject.getJSONObject("data").getJSONObject("command").put("message", message);

        makeRequest("/api/rest/push/commands/popup", jsonObject.toString(), "POST");
    }

    public void remove(String udid) throws IOException, JSONException {
        makeRequest("/api/rest/push/devices/delete/" + udid, null, "POST");
    }

    public void list_single(String udid) throws IOException, JSONException {
        makeRequest("/api/rest/push/devices/list_single/" + udid, null, "GET");
    }

    private String makeRequest(String route, String content, String method) throws IOException {
        String uri = "http://" + server + ":8080" + route;
        HttpRequest request = createHttpRequest(uri, method);

        if (content != null && request instanceof HttpEntityEnclosingRequestBase){

            ((HttpEntityEnclosingRequestBase)request).setEntity(new StringEntity(content, "UTF8"));
        }

        /////////////////
        BasicClientCookie cookie = new BasicClientCookie("access_token", "strawberryfieldsforever");
        Calendar exp = Calendar.getInstance();
        exp.add(Calendar.MINUTE, 60);

        cookie.setDomain(server);
        cookie.setPath("/");

        /////////////

        DefaultHttpClient client = new DefaultHttpClient();
        client.setCookieStore(new BasicCookieStore());
        client.getCookieStore().addCookie(cookie);
        client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2109);


        HttpHost host = new HttpHost(server,8080);
        HttpResponse response = client.execute(host, request);
        System.out.println(response.getStatusLine().toString());
        String result = EntityUtils.toString(response.getEntity(), "UTF8");
        System.out.println(result);
        return result;
    }

    private String makeRequest(String route, String method) throws IOException {
        return makeRequest(route, null, method);
    }

    private HttpRequest createHttpRequest(String url, String method){
        HttpRequest request = null;

        switch (method.toUpperCase()){
            case "GET":
                request = new HttpGet(url);
                break;
            case "POST":
                request = new HttpPost(url);
                break;
            case "DELETE":
                request = new HttpDelete(url);
                break;
            case "PUT":
                request = new HttpPut(url);
                break;
            case "HEAD":
                request = new HttpHead(url);
                break;
            default:
                break;
        }

        request.setHeader("Content-Type", "application/json");

        return request;
    }
}
