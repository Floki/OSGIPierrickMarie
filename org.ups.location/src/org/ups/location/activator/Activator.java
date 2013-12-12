package org.ups.location.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.ups.location.ILocation;
import org.ups.location.impl.LocationImpl;

public class Activator implements BundleActivator {

	private ILocation service = null;

	public Activator() {
		this.service = new LocationImpl();
	}

	public void start(final BundleContext context)  {
		try {
			System.out.println("OSGi LOCATION: org.ups.location.Activator.start() (Name : "+ILocation.class.getName()+")");
			Dictionary<String, String> properties = new Hashtable<String, String>();
			properties.put("name", "org.ups.location");
			context.registerService(ILocation.class.getName(), this.service, properties);
		}
		catch(Exception e){
			System.err.println("OSGi LOCATION : Erreur lors du lancement du module location.");
			e.printStackTrace();
		}
	}

	public void stop(final BundleContext context) throws Exception {
		System.out.println("OSGi LOCATION: org.ups.location.Activator.stop()");
		this.service = null;
	}
}
