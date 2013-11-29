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
	public void start(BundleContext context) throws Exception {
		System.out.println("OSGi DRESSING_SUGGESTION : org.ups.dressingengine.Activator.start()");
		
		System.out.println("OSGi DRESSING_SUGGESTION : Connection to the Location module");
		ServiceReference<?>[] references = context.getServiceReferences(IWeather.class.getName(), "(name=*)");

		for (ServiceReference<?> reference : references) {
			((IWeather) context.getService(reference)).addListener((IWeatherListener) this.service);
		}
		
		Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put("name", "org.ups.dressingsuggestion");
		context.registerService(IDressingSuggestion.class.getName(), this.service, properties);
	}

	
	public void stop(BundleContext context) throws Exception {
		System.out.println("OSGi LOCATION: org.ups.dressingengine.Activator.start()");
		
		this.service = null;
	}

}
