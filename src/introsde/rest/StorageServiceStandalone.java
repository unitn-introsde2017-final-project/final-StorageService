package introsde.rest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class StorageServiceStandalone
{
    /*public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
    {
        String protocol = "http://";
        String port = ":5700/";
        String hostname = InetAddress.getLocalHost().getHostAddress();
        if (hostname.equals("127.0.0.1") || hostname.equals("127.0.1.1"))
        {
            hostname = "localhost";
        }

        URI baseUrl = new URI(protocol + hostname + port);

        ResourceConfig rc = new ResourceConfig(HelloWorld.class);
        JdkHttpServerFactory.createHttpServer(baseUrl, rc);
        System.out.println("server starts on " + baseUrl + "\n [kill the process to exit]");
    }*/
	
	public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException{
        String PROTOCOL = "http://";
        String HOSTNAME = InetAddress.getLocalHost().getHostAddress();
        System.out.println(HOSTNAME);
        if (HOSTNAME.equals("127.0.0.1") || HOSTNAME.equals("127.0.1.1"))
        {
            HOSTNAME = "localhost";
        }
        String PORT = "9001";
        //String BASE_URL = "/sdelab/";

        // We need this so the App will run on Heroku properly where we got the assigned port
        // in an environment value named PORT
        if(String.valueOf(System.getenv("PORT")) != "null") {
            PORT = String.valueOf(System.getenv("PORT"));
        }

        // Assemble the final endpoint URL
        URI baseUrl = new URI(PROTOCOL + HOSTNAME + ":" + PORT + "/");
        // End publish the endpoint
        //ResourceConfig rc = new ResourceConfig(HelloWorld.class);
        JdkHttpServerFactory.createHttpServer(baseUrl, createApp());
        System.out.println("server starts on " + baseUrl + "\n [kill the process to exit]");
    }
	
	public static ResourceConfig createApp() {
    	System.out.println("Starting StorageService REST services...");
        return new StorageServiceConfig();
    }
}