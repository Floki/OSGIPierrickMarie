package org.ups.weather.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ups.weather.IWeather;
import org.ups.weather.IWeatherListener;
import org.ups.weather.WeatherType;

public class WeatherImpl implements IWeather {

	float latitude;
	float longitude;
	
	public WeatherImpl() {
		this.latitude = 666;
		this.longitude = 666;
	}
	
	public WeatherImpl(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setLocation(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void addListener(IWeatherListener listener) {
		// TODO Auto-generated method stub
	}

	public void removeListener(IWeatherListener listener) {
		// TODO Auto-generated method stub

	}

	public WeatherType getCurrentWeather() {
		WeatherType type = WeatherType.UNKNOWN;
		try {
			URL urlTmp = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude);
			System.out.println("URL = " + urlTmp);
		     
			BufferedReader in = new BufferedReader(new InputStreamReader(urlTmp.openStream()));
	
	        String stringTmp = in.readLine();
	        
	        String patternStringWeatherId = "\"weather\":\\[\\{\"id\":([0-9]*),";
	
	        Pattern patternWeatherId = Pattern.compile(patternStringWeatherId);
	        Matcher matcherWeather = patternWeatherId.matcher(stringTmp);
	
	        if(matcherWeather.find()) {
	            System.out.println("Id Weather : " + matcherWeather.group(1));
	            System.out.println("Corresponding : " + weatherNameFromId(Integer.parseInt(matcherWeather.group(1))));
	            type = weatherNameFromId(Integer.parseInt(matcherWeather.group(1)));
	        }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return type;
	}

	public WeatherType getWeather(int nbHoursFromNow) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static WeatherType weatherNameFromId(int id) {
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
