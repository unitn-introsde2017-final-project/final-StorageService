package introsde.rest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.glassfish.jersey.client.ClientConfig;

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
		ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget service = client.target(getBaseURI());
        
        System.out.println(userId);

        String user_data = service.path("person/" + userId).request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		return user_data;
	}
	
	@GET
	@Path("WeatherInformation")
	@Produces(MediaType.TEXT_PLAIN)
	public String WeatherInformation(@QueryParam("city") String city) throws MalformedURLException {
        /// Build stuff for SOAP
        URL url = new URL("https://final-weather-adapter.herokuapp.com/ws/weather?wsdl");
        // 1st argument service URI, refer to wsdl document above
        // 2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.introsde/", "WeatherImplService");
        Service service = Service.create(url, qname);
        Weather hello = service.getPort(Weather.class);
        /// ---
        System.out.println(city);
        String result = hello.getWeatherInformationByCity(city);

        return result;
	}
	
	private static URI getBaseURI() {
        //return UriBuilder.fromUri("http://localhost:9002/sdelab").build();
		return UriBuilder.fromUri("https://final-local-database.herokuapp.com/sdelab").build();
    }
}
