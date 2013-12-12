package org.ups.dressingengine.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.ups.dressingengine.IDressingSuggestion;
import org.ups.dressingengine.impl.DressingSuggestionImpl;

public class Activator implements BundleActivator {
	
	private IDressingSuggestion service = null;
	
	public Activator() {
		this.service = new DressingSuggestionImpl();
	}

	public void start(BundleContext context) {
		try {
			System.out.println("OSGi DRESSING_SUGGESTION : org.ups.dressingengine.Activator.start()");			
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
