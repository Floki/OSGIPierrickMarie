package org.ups.client;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.ups.dressingengine.IDressingSuggestion;
import org.ups.location.ILocation;
import org.ups.weather.IWeather;
import org.ups.weather.IWeatherListener;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private ServiceTracker serviceTracker;


	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) {
		ServiceReference<?>[] references;
		Activator.context = bundleContext;
		Scanner in = new Scanner(System.in);
		displayMenu();
		int choice = in.nextInt();
		while(choice != 0) {
			switch(choice) {
				case 1:
					try {
						references = context.getServiceReferences(ILocation.class.getName(), "(name=*)");
						if(references == null) {
							System.out.println("OSGi CLIENT UPS : Impossible de contacter le bundle " + ILocation.class.getName() + ", vérifié qu'il soit bien initialisé ou redemarrer OSGi");
						}
						float latitude = 666;
						float longitude = 666;
						for (ServiceReference<?> reference : references) {
							latitude = ((ILocation) context.getService(reference)).getLatitude();
							longitude = ((ILocation) context.getService(reference)).getLongitude();
						}
						if(latitude == 666 || longitude == 666) {
							System.out.println("			   Impossible de retrouver votre position.");
						}
						else {
							System.out.println("			   Votre position est (" + latitude + ',' + longitude +")");
						}
					}
					catch(Exception e) {
						System.out.println("			   Impossible de retrouver votre position");
					}
				break;
				case 2:
					try {
						references = context.getServiceReferences(IWeather.class.getName(), "(name=*)");
						if(references == null) {
							System.out.println("OSGi CLIENT UPS : Impossible de contacter le bundle " + IWeather.class.getName() + ", vérifié qu'il soit bien initialisé ou redemarrer OSGi");
						}
				
						for (ServiceReference<?> reference : references) {
							System.out.println("			  Le temps est " + ((IWeather) context.getService(reference)).getCurrentWeather());	
						}
					}
					catch(Exception e) {
						System.out.println("			   Impossible de retrouver la météo du jour");
					}
				break;
				case 3:
					try {
						references = context.getServiceReferences(IDressingSuggestion.class.getName(), "(name=*)");
						if(references == null) {
							System.out.println("OSGi CLIENT UPS : Impossible de contacter le bundle " + IDressingSuggestion.class.getName() + ", vérifié qu'il soit bien initialisé ou redemarrer OSGi");
						}
				
						for (ServiceReference<?> reference : references) {
							boolean umbrella = ((IDressingSuggestion) context.getService(reference)).umbrellaNeeded();
							boolean glasses = ((IDressingSuggestion) context.getService(reference)).sunGlassesNeeded();
							boolean coat = ((IDressingSuggestion) context.getService(reference)).coatNeeded();
							System.out.println("			  Nous vous conseillons : ");
							if(umbrella) {
								System.out.print("			  	De prendre un parapluie, ");
							}
							else {
								System.out.print("			  	De ne pas prendre un parapluie, ");
							}
							if(coat) {
								System.out.println("de prendre un manteau, ");
							}
							else {
								System.out.println("de ne pas prendre un manteau, ");
							}
							if(glasses) {
								System.out.println("et de prendre des lunettes de soleil.");	
							}
							else {
								System.out.println("et de ne pas prendre des lunettes de soleil.");
							}
						}
					}
					catch(Exception e) {
						System.out.println("			   Impossible de retrouver les conseils du jour");
					}
				break;
				case 4:
					try {
						// Register directly with the service
						if(serviceTracker == null) {
						    DressingSTC dressingTracker = new DressingSTC(context);
						    serviceTracker = new ServiceTracker(context, IDressingSuggestion.class.getName(), dressingTracker);
						    serviceTracker.open();
						}
						else {
							serviceTracker.close();
							serviceTracker = null;
						}
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				break;
			}
			displayMenu();
			choice = in.nextInt();
		}
		in.close();
	}
	
	private void displayMenu() {
		System.out.println("OSGI CLIENT UPS : Menu");
		System.out.println("			1 : afficher votre position GPS");
		System.out.println("			2 : information sur la météo");
		System.out.println("			3 : conseil d'habillement");
		System.out.println("			4 : démarrer/arrêter le ServiceTrackerCustomizer d'habillement");
		System.out.println("			0 : quitter");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		serviceTracker.close();
	}

}
