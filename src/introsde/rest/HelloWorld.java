package introsde.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.net.MalformedURLException;
// for the weather API
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import introsde.ws.Weather;


@Path("/salutation")
public class HelloWorld {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello World in REST";
	}

	// When client wants HTML
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHelloHtml() throws MalformedURLException {
		URL url = new URL("https://final-weather-adapter.herokuapp.com/ws/weather?wsdl");
        // 1st argument service URI, refer to wsdl document above
        // 2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.introsde/", "WeatherImplService");
        Service service = Service.create(url, qname);
        Weather hello = service.getPort(Weather.class);
        String city = "Budapest,hu";
        String result = hello.getWeatherInformationByCity(city);
		System.out.println(result);
		return "<html> " + "<title>" + "Hello Jersey" + "</title>"
				+ "<body><h1>" + "The tempreture in " + city + " is " + result + " C.</body></h1>"
				+ "</html> ";
	}

	// When client wants XML
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHelloJson() {
		return "{ 'salutation': 'Hello World in REST' }";
	}
	
 	// When client wants HTML
  	@GET
  	@Path("/{name}") // you can pass path params to a service
  	@Produces(MediaType.TEXT_HTML)
  	public String sayHelloHtmlToPerson(@PathParam("name") String name,
  			@QueryParam("age") int age) {
  		
  		String printAlsoAge = "";
  		
  		// QueryParams can be used as optional parameters that you use if they are present
  		// 
  		if (age > 0) {
  			printAlsoAge = " You are "+age+ "years old, but don't worry. It's never late to learn!";
  		}
  		
  		return "<html> " + "<title>" + "Hello Jersey" + "</title>"
  				+ "<body><h1>" + "Hello "+name+". Welcome to Jersey REST." +printAlsoAge+ "</body></h1>"
  				+ "</html> ";
  	} 
  	

	
}