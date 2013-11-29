package org.ups.locationclient;

import org.ups.location.ILocation;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	@Override
	public void start(final BundleContext context) throws Exception {
		ServiceReference<?>[] references = context.getServiceReferences(ILocation.class.getName(), "(name=*)");

		for (ServiceReference<?> reference : references) {
			float latitude = ((ILocation) context.getService(reference)).getLatitude();
			float longitude = ((ILocation) context.getService(reference)).getLongitude();
			System.out.println(latitude + "," + longitude);
		}
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
	}

}
