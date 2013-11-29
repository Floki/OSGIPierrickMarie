/*
 * DressingSuggestion.java
 */
package org.ups.dressingengine.impl;

import java.util.ArrayList;
import java.util.List;

import org.ups.dressingengine.IDressingSuggestion;
import org.ups.dressingengine.IDressingSuggestionListener;
import org.ups.weather.IWeatherListener;
import org.ups.weather.WeatherType;



/**
 *
 */
public class DressingSuggestionImpl implements IDressingSuggestion, IWeatherListener {
	
	
	private boolean coat = false;
	private boolean sunglasses = false;
	private boolean umbrella = false;

	private List<IDressingSuggestionListener> listeners = new ArrayList<IDressingSuggestionListener>();

	public boolean sunGlassesNeeded() {
		return this.sunglasses;
	}


	public boolean umbrellaNeeded() {
		return this.umbrella;
	}

	public boolean coatNeeded() {
		return this.coat;
	}

	


	public void addListener(IDressingSuggestionListener listener) {
		this.listeners.add(listener);
	}


	public void removeListener(IDressingSuggestionListener listener) {
		this.listeners.remove(listener);
	}


	/* (non-javadoc)
	 * @see org.ups.weather.IWeatherListener#weatherChanged(org.ups.weather.WeatherType)
	 */
	public void weatherChanged(WeatherType newWeather) {
		switch(newWeather) {
			case CLOUDY:
				this.coat = false;
				this.sunglasses = false;
				this.umbrella = false;
				break;
			case RAINY:
				this.coat = true;
				this.sunglasses = false;
				this.umbrella = true;
				break;
			case SHINY:
				this.coat = false;
				this.sunglasses = true;
				this.umbrella = false;
				break;
			case SHOWERS:
				this.coat = true;
				this.sunglasses = false;
				this.umbrella = true;
				break;
			case SNOW:
				this.coat = true;
				this.sunglasses = false;
				this.umbrella = false;
				break;
			case UNKNOWN:
				this.coat = false;
				this.sunglasses = false;
				this.umbrella = false;
				break;
			default:
				this.coat = false;
				this.sunglasses = false;
				this.umbrella = false;
				break;
			
		}
		
		
	}

}
