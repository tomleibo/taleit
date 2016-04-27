package acceptance.bridge;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Created by gur on 1/19/2016.
 */
public class LocalhostClient {
    static final String HOST = "localhost";
    static final String PROTOCOL = "http";
    static final int PORT = 8080;
    static final String APPLICATION = "server.core-1.0-SNAPSHOT";

    public HttpResponse makeApiPostRequest(String httpPath, String content) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, InvalidKeyException {
        HttpClient client = new DefaultHttpClient();
        final BasicHttpContext context = createNewHttpContext();
        final HttpHost host = createHost();

        String uri = createURI(httpPath);
        HttpPost request = (HttpPost) createHttpRequest(uri, HttpMethod.POST);
        request.setEntity(new StringEntity(content));

        HttpResponse response = client.execute(host, request, context);

        return response;
    }

    public HttpResponse makeApiGetRequest(String httpPath) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, InvalidKeyException {
        HttpClient client = new DefaultHttpClient();
        final BasicHttpContext context = createNewHttpContext();
        final HttpHost host = createHost();

        String uri = createURI(httpPath);
        HttpRequest request = createHttpRequest(uri, HttpMethod.GET);

        return client.execute(host, request, context);
    }

    private String createURI(String route){
        //todo dropped APPLICATION becuase didn't know what it is for... it fixed the 404 problem.... Gur?
//        String uri = PROTOCOL + "://" + HOST + ":" + PORT + "/" + APPLICATION + route;
        String uri = PROTOCOL + "://" + HOST + ":" + PORT + "/" + route;

        return uri;
    }

    private HttpRequest createHttpRequest(String url, HttpMethod method){
        // only supports get/post at the moment
        org.apache.http.HttpRequest request = method.equals(HttpMethod.GET) ? new HttpGet(url): new HttpPost(url);

        request.setHeader("Content-Type", "TEXT");

        return request;
    }

    /**
     *
     * @return default implementation of HTTP context
     */
    private BasicHttpContext createNewHttpContext() {
        return new BasicHttpContext();
    }

    private HttpHost createHost(){
        String host = "localhost";
        int port = 8080;
        String protocol = "http";
        return new HttpHost(host, port, protocol);
    }

    public enum HttpMethod { POST, GET }
}
