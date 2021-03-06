package org.ups.location.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ups.location.ILocation;
import org.ups.location.ILocationListener;

public class LocationImpl implements ILocation {

	private ArrayList<ILocationListener> listenerList;
    float latitude;
    float longitude;
	
	public LocationImpl() {
		this.listenerList = new ArrayList<ILocationListener>();
        this.latitude = 666;
        this.longitude = 666;
	}
	
	private void computeLocation() {
		try
	    {
	    	// Use FreeGeoIP service for geolocation
	        URL urlFreeGeoIP = new URL("http://freegeoip.net/json/");
	        // To get returned JSON
			String stringTmp = "";
	        BufferedReader in = null;

	        
	        // Get informations from FreeGeoIP
	        in = new BufferedReader(new InputStreamReader(urlFreeGeoIP.openStream()));
	        stringTmp = in.readLine();
	        	        
	        // Retrieve latitude and longitude in JSON returned value
	        String patternStringLatitude = "\"latitude\":([0-9\\.]*),";
	        String patternStringLongitude = "\"longitude\":([0-9\\.]*),";

	        Pattern patternLatitude = Pattern.compile(patternStringLatitude);
	        Matcher matcherLatitude = patternLatitude.matcher(stringTmp);
	        Pattern patternLongitude = Pattern.compile(patternStringLongitude);
	        Matcher matcherLongitude = patternLongitude.matcher(stringTmp);

	        if(matcherLatitude.find()) {
	            //System.out.println("LOCATION : Latitude : " + matcherLatitude.group(1));
	            this.latitude = Float.parseFloat(matcherLatitude.group(1));
	        }
	        else {
	        	System.err.println("LOCATION : Unable to get Latitude.");
	        }
	        if(matcherLongitude.find()) {
	            //System.out.println("LOCATION : Longitude : " + matcherLongitude.group(1));
	            this.longitude = Float.parseFloat(matcherLongitude.group(1));
	        }
	        else {
	        	System.err.println("LOCATION : Unable to get Longitude.");
	        }
	        if(this.latitude != 666 && this.longitude != 666) {
	        	for(int i = 0; i < this.listenerList.size(); i++) {
	        		this.listenerList.get(i).locationChanged(this.latitude, this.longitude);
	        	}
	        }
	    }
		catch(Exception e) {
			System.err.println("LOCATION : Unable to get coordinates");
		}
	}
	
	public float getLatitude() {
		this.computeLocation();
		return this.latitude;
	}

	public float getLongitude() {
		this.computeLocation();
		return this.longitude;
	}

	public void addListener(ILocationListener listener) {
		this.listenerList.add(listener);
	}

	public void removeListener(ILocationListener listener) {
		this.listenerList.remove(listener);
	}
}
