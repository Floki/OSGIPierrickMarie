import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.*;
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	    try
	    {
	    	// Get Location
	        URL urlTmp;
			String stringTmp = "";
	        BufferedReader in = null;
	        float latitude = 666;
	        float longitude = 666;
	        
	        urlTmp = new URL("http://freegeoip.net/json/");
	        
	        in = new BufferedReader(new InputStreamReader(urlTmp.openStream()));

	        stringTmp = in.readLine();
	        	        
	        String patternStringLatitude = "\"latitude\":([0-9\\.]*),";
	        String patternStringLongitude = "\"longitude\":([0-9\\.]*),";

	        Pattern patternLatitude = Pattern.compile(patternStringLatitude);
	        Matcher matcherLatitude = patternLatitude.matcher(stringTmp);
	        Pattern patternLongitude = Pattern.compile(patternStringLongitude);
	        Matcher matcherLongitude = patternLongitude.matcher(stringTmp);

	        if(matcherLatitude.find()) {
	            System.out.println("Latitude : " + matcherLatitude.group(1));
	            latitude = Float.parseFloat(matcherLatitude.group(1));
	        }
	        if(matcherLongitude.find()) {
	            System.out.println("Longitude : " + matcherLongitude.group(1));
	            longitude = Float.parseFloat(matcherLongitude.group(1));
	        }
	        
	        // Get weather
	        urlTmp = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude);
	     
	        in = new BufferedReader(new InputStreamReader(urlTmp.openStream()));

	        stringTmp = in.readLine();
	        
	        String patternStringWeatherId = "\"weather\":\\[\\{\"id\":([0-9]*),";

	        Pattern patternWeatherId = Pattern.compile(patternStringWeatherId);
	        Matcher matcherWeather = patternWeatherId.matcher(stringTmp);

	        if(matcherWeather.find()) {
	            System.out.println("Id Weather : " + matcherWeather.group(1));
	            System.out.println("Corresponding : " + weatherNameFromId(Integer.parseInt(matcherWeather.group(1))));
	        }	        
	        
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    } 
	}
	
	public static WeatherType weatherNameFromId(int id) {
		if(id >= 200 && id < 300) {
			return WeatherType.SHOWERS;
		}
		else if(id >= 300 && id < 600){
			return WeatherType.RAINY;
		}
		else if(id >= 600 && id < 700){
			return WeatherType.SNOW;
		}
		else if((id >= 700 && id < 800) || id > 802) {
			return WeatherType.CLOUDY;
		}
		else if(id >= 800 && id <= 802) {
			return WeatherType.SHINY;
		}
		return WeatherType.UNKNOWN;
	}

}
