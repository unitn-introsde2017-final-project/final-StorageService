package introsde.rest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONObject;

import introsde.ws.Weather;

/**
 * Servlet implementation class StorageService
 */
@Path("/StorageService")
public class StorageService {
	
	// This method is called if TEXT_PLAIN is request
	@GET
	@Path("UserProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public String UserProfile(@QueryParam("userId") int userId) {
		System.out.println("--> UserProfile called with userId = " + userId);
		ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget service = client.target(getBaseURI());
        
        String user_data = service.path("person/" + userId).request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
        System.out.println(user_data);
        
		return user_data;
	}
	
	@GET
	@Path("WeatherInformation")
	@Produces(MediaType.TEXT_PLAIN)
	public String WeatherInformation(@QueryParam("city") String city) throws MalformedURLException {
		System.out.println("--> WeatherInformation called with city = " + city);
        /// Build stuff for SOAP
        URL url = new URL("https://final-weather-adapter.herokuapp.com/ws/weather?wsdl");
        // 1st argument service URI, refer to wsdl document above
        // 2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.introsde/", "WeatherImplService");
        Service service = Service.create(url, qname);
        Weather hello = service.getPort(Weather.class);
        /// ---
        String result = hello.getWeatherInformationByCity(city);
        System.out.println(result);

        return result;
	}
	
	@PUT
    @Path("/addUpdateStepCount") // you can pass path params to a service
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String addUpdateStep(String payload) {
    	System.out.println("--> /addUpdateStepCount called with payload:");
    	System.out.println(payload);
    	
    	// Get JSON from the payload
    	JSONObject json_data = new JSONObject(payload);
    	
    	// Pass the data to the Storage Layer
    	ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget service = client.target(getBaseURI());
        
        String response_text = service.path("steps").request().accept(MediaType.APPLICATION_JSON).put(Entity.json(json_data.toString())).readEntity(String.class);
        
		return response_text;
	}
	
	@POST
    @Path("/UserProfile") // you can pass path params to a service
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String addUpdateProfile(@QueryParam("userId") int userId, String payload) {
    	System.out.println("--> /addUpdateProfile called with userId = "+userId+" and payload:");
    	System.out.println(payload);
    	
    	// Get JSON from the payload
    	JSONObject json_data = new JSONObject(payload);
    	
    	// Pass the data to the Storage Layer
    	ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget service = client.target(getBaseURI());
        
        String response_text = service.path("person/"+userId).request().accept(MediaType.APPLICATION_JSON).post(Entity.json(json_data.toString())).readEntity(String.class);
        
		return response_text;
	}
	
	private static URI getBaseURI() {
        //return UriBuilder.fromUri("http://localhost:9002/sdelab").build();
		return UriBuilder.fromUri("https://final-local-database.herokuapp.com/sdelab").build();
    }
}
