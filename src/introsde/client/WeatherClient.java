package introsde.client;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import introsde.ws.Weather;

public class WeatherClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:9003/ws/weather?wsdl");
        // 1st argument service URI, refer to wsdl document above
        // 2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.introsde/", "WeatherImplService");
        Service service = Service.create(url, qname);
        Weather hello = service.getPort(Weather.class);
        System.out.println(hello.getWeatherInformationByCity("Budapest,hu"));
    }
}