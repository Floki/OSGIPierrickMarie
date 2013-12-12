package org.ups.weather.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.ups.location.ILocation;
import org.ups.location.ILocationListener;
import org.ups.weather.IWeather;
import org.ups.weather.impl.WeatherImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	private IWeather service = null;

	public Activator() {
		service = new WeatherImpl();
	}

	public void start(final BundleContext context) {
		try {
			System.out.println("OSGi WEATHER : org.ups.weather.Activator.start()");
			Dictionary<String, String> properties = new Hashtable<String, String>();
			properties.put("name", "org.ups.weather");
			context.registerService(IWeather.class.getName(), service, properties);
		}
		catch(Exception e) {
			System.err.println("OSGi WEATHER : Erreur lors du lancement du module weather.");
			e.printStackTrace();
		}
	}

	public void stop(final BundleContext context) throws Exception {
		System.out.println("OSGi WEATHER : org.ups.location.Activator.stop()");
		service = null;
	}

}