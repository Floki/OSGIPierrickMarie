package org.ups.client;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.ups.location.ILocation;
import org.ups.weather.IWeather;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		Scanner in = new Scanner(System.in);
		displayMenu();
		int choice = in.nextInt();
		while(choice != 0) {
			switch(choice) {
				case 1:
					ILocation locationObject = ((ILocation) serviceObject("ILocation"));
					if(locationObject != null) {
						System.out.println("Votre position est (" + locationObject.getLatitude() + ',' + locationObject.getLongitude() +")");
					}
				break;
				case 2:
					IWeather weatherObject = ((IWeather) serviceObject("ILocation"));
					if(weatherObject != null) {
						System.out.println("La météo est " + weatherObject.getCurrentWeather());
					}
				break;
				case 3:
					
				break;
			}
			displayMenu();
			choice = in.nextInt();
		}
	}
	
	private Object serviceObject(String name) throws InvalidSyntaxException {
		Object returnedObject = null;
		ServiceReference<?>[] references = context.getServiceReferences(name, "(name=*)");

		for (ServiceReference<?> reference : references) {
			returnedObject = context.getService(reference);
		}
		
		return returnedObject;
	}
	
	private void displayMenu() {
		System.out.println("OSGI CLIENT UPS : Menu");
		System.out.println("			1 : afficher votre position GPS");
		System.out.println("			2 : information sur la météo");
		System.out.println("			3 : conseil d'habillement");
		System.out.println("			0 : quitter");
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
