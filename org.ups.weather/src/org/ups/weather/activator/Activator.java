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
	
			System.out.println("OSGi WEATHER : Connection to the Location module (Name : "+ILocation.class.getName()+")" );
			
			ServiceReference<?>[] references = context.getServiceReferences(ILocation.class.getName(), "(name=*)");
			if(references == null) {
				System.out.println("OSGi WEATHER : Impossible de contacter le bundle " + ILocation.class.getName() + ", vérifié qu'il soit bien initialisé ou redemarrer OSGi");
			}
	
			for (ServiceReference<?> reference : references) {
				((ILocation) context.getService(reference)).addListener((ILocationListener) this.service);
				((ILocation) context.getService(reference)).getLatitude();
				((ILocation) context.getService(reference)).getLongitude();
				System.out.println("OSGi WEATHER : Current weather is " + this.service.getCurrentWeather());
			}
			
			Dictionary<String, String> properties = new Hashtable<String, String>();
			properties.put("name", "org.ups.weather");
			context.registerService(IWeather.class.getName(), service, properties);
		}
		catch(Exception e) {
			System.out.println("OSGi WEATHER : Erreur lors du lancement du module weather.");
			e.printStackTrace();
		}
	}

	public void stop(final BundleContext context) throws Exception {
		System.out.println("OSGi WEATHER : org.ups.location.Activator.stop()");

		service = null;
	}

}