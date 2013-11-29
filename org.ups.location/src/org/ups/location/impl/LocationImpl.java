package org.ups.location.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ups.location.ILocation;
import org.ups.location.ILocationListener;

public class LocationImpl implements ILocation {

	// Last geolocation time (only do a new geolocation after 2 secondes)
	private long lastTime = new Date().getTime();
	
	// Coordinates
	private float latitude = 666;
	private float longitude = 666;
	
	private void computeLocation() {
		// Only do a new geolocation after 2 seconds from the last one
		// Improve performance when try to get latitude and longitude coordinates
		if(new Date().getTime() - lastTime > 2000) {
			return;
		}
		try
	    {
	    	// Use FreeGeoIP service for geolocation
	        URL urlFreeGeoIP = new URL("http://freegeoip.net/json/");
	        // To get returned JSON
			String stringTmp = "";
	        BufferedReader in = null;
	        
	        // Latitude and longitude
	        latitude = 666;
	        longitude = 666;
	        
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
	            System.out.println("LOCATION : Latitude : " + matcherLatitude.group(1));
	            latitude = Float.parseFloat(matcherLatitude.group(1));
	        }
	        else {
	        	System.out.println("LOCATION : Unable to get Latitude.");
	        	return;
	        }
	        if(matcherLongitude.find()) {
	            System.out.println("LOCATION : Longitude : " + matcherLongitude.group(1));
	            longitude = Float.parseFloat(matcherLongitude.group(1));
	        }
	        else {
	        	System.out.println("LOCATION : Unable to get Longitude.");
	        	return;
	        }
	        lastTime = new Date().getTime();
	    }
		catch(Exception e) {
			System.out.println("LOCATION : Unable to get coordinates");
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
		// TODO Auto-generated method stub

	}

	public void removeListener(ILocationListener listener) {
		// TODO Auto-generated method stub

	}

}
