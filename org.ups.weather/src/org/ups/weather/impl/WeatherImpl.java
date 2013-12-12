package org.ups.weather.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ups.location.ILocationListener;
import org.ups.weather.IWeather;
import org.ups.weather.IWeatherListener;
import org.ups.weather.WeatherType;

public class WeatherImpl implements ILocationListener, IWeather  {

	private ArrayList<IWeatherListener> listenerList;
	private WeatherType weather = WeatherType.UNKNOWN;
	
	public WeatherImpl() {
		this.listenerList = new ArrayList<IWeatherListener>();	
		this.weather = WeatherType.UNKNOWN;
	}
	

	public WeatherType getCurrentWeather() {
		return this.weather;
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

	public void locationChanged(float lan, float lon) {
		try {
			URL urlTmp = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+lan+"&lon="+lon);
			System.out.println("URL = " + urlTmp);
		     
			BufferedReader in = new BufferedReader(new InputStreamReader(urlTmp.openStream()));
	
	        String stringTmp = in.readLine();
	        
	        String patternStringWeatherId = "\"weather\":\\[\\{\"id\":([0-9]*),";
	
	        Pattern patternWeatherId = Pattern.compile(patternStringWeatherId);
	        Matcher matcherWeather = patternWeatherId.matcher(stringTmp);
	
	        if(matcherWeather.find()) {
	            this.weather = weatherNameFromId(Integer.parseInt(matcherWeather.group(1)));
	            for(int i = 0; i < this.listenerList.size(); i++) {
	        		this.listenerList.get(i).weatherChanged(this.weather);
	        	}
	        }
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void addListener(IWeatherListener listener) {
		this.listenerList.add(listener);
	}

	public void removeListener(IWeatherListener listener) {
		this.listenerList.remove(listener);
	}
}
