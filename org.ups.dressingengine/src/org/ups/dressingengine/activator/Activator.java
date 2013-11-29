/*
 * Activator.java
 */
package org.ups.dressingengine.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.ups.dressingengine.IDressingSuggestion;
import org.ups.dressingengine.impl.DressingSuggestionImpl;
import org.ups.weather.IWeather;
import org.ups.weather.IWeatherListener;



/**
 *
 */
public class Activator implements BundleActivator {
	
	private IDressingSuggestion service = null;
	
	public Activator() {
		this.service = new DressingSuggestionImpl();
	}

	/* (non-javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) {
		try {
			System.out.println("OSGi DRESSING_SUGGESTION : org.ups.dressingengine.Activator.start()");
			
			System.out.println("OSGi DRESSING_SUGGESTION : Connection to the Weather module");
			ServiceReference<?>[] references = context.getServiceReferences(IWeather.class.getName(), "(name=*)");
	
			for (ServiceReference<?> reference : references) {
				((IWeather) context.getService(reference)).addListener((IWeatherListener) this.service);
				((IWeather) context.getService(reference)).getCurrentWeather();
				System.out.println("OSGi DRESSING_SUGGESTION : SunGlasses : " + this.service.sunGlassesNeeded());
				System.out.println("OSGi DRESSING_SUGGESTION : Umbrella : " + this.service.umbrellaNeeded());
				System.out.println("OSGi DRESSING_SUGGESTION : Coat : " + this.service.coatNeeded());
			}
			
			Dictionary<String, String> properties = new Hashtable<String, String>();
			properties.put("name", "org.ups.dressingsuggestion");
			context.registerService(IDressingSuggestion.class.getName(), this.service, properties);
		}
		catch(Exception e) {
			System.out.println("OSGi DRESSING_SUGGESTION : Erreur lors du lancement du module dessing.");
			e.printStackTrace();
		}
	}

	
	public void stop(BundleContext context) throws Exception {
		System.out.println("OSGi LOCATION: org.ups.dressingengine.Activator.start()");
		
		this.service = null;
	}

}
