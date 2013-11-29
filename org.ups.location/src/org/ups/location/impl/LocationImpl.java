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


	private Coordinates computeLocation() {
		Coordinates coordinates = new Coordinates();
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
	            System.out.println("LOCATION : Latitude : " + matcherLatitude.group(1));
	            coordinates.latitude = Float.parseFloat(matcherLatitude.group(1));
	        }
	        else {
	        	System.out.println("LOCATION : Unable to get Latitude.");
	        }
	        if(matcherLongitude.find()) {
	            System.out.println("LOCATION : Longitude : " + matcherLongitude.group(1));
	            coordinates.longitude = Float.parseFloat(matcherLongitude.group(1));
	        }
	        else {
	        	System.out.println("LOCATION : Unable to get Longitude.");
	        }
	    }
		catch(Exception e) {
			System.out.println("LOCATION : Unable to get coordinates");
		}
        return coordinates;
	}
	
	public float getLatitude() {
		return this.computeLocation().latitude;
	}

	public float getLongitude() {
		return this.computeLocation().longitude;
	}

	public void addListener(ILocationListener listener) {
		// TODO Auto-generated method stub

	}

	public void removeListener(ILocationListener listener) {
		// TODO Auto-generated method stub

	}

}
