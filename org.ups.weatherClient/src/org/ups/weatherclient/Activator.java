package org.ups.weatherclient;

import org.ups.weather.*;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	@Override
	public void start(final BundleContext context) throws Exception {
		System.out.println("OSGi WEATHER CLIENT: org.ups.weather.Activator.start()");
		ServiceReference<?>[] references = context.getServiceReferences(IWeather.class.getName(), "(name=*)");

		for (ServiceReference<?> reference : references) {
			System.out.println(((IWeather) context.getService(reference)).getCurrentWeather());
		}
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		System.out.println("OSGi WEATHER CLIENT: org.ups.weather.Activator.stop()");
	}

}
