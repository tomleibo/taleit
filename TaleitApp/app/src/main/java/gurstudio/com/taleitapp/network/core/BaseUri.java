package gurstudio.com.taleitapp.network.core;

public class BaseUri {
    final String protocol;
    final String host;
    final int port;
    final String appRoute;
    final String baseUri;

    public BaseUri(String protocol, String host, int port, String appRoute){
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.appRoute = appRoute != null? appRoute : "";

        this.baseUri = this.protocol + "://" + this.host + ":" + this.port + this.appRoute;
    }

    @Override
    public String toString(){
        return baseUri;
    }
}
