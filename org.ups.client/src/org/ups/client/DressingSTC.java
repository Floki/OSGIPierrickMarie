package org.ups.client;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.ups.dressingengine.IDressingSuggestion;

public class DressingSTC implements ServiceTrackerCustomizer {

	private final BundleContext context;

	public DressingSTC(BundleContext context) {
		this.context = context;
	}

	private DressingThread thread;

	@Override
	public Object addingService(ServiceReference reference) {
		System.out.println("DRESSINGSTC : Service de dressing ajouté.");
		IDressingSuggestion service = (IDressingSuggestion) context.getService(reference);
		thread = new DressingThread(service);
		thread.start();
		return service;
	}

	@Override
	public void modifiedService(ServiceReference reference, Object service) {
		System.out.println("DRESSINGSTC : Service de dressing modifié.");
		removedService(reference, service);
		addingService(reference);
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		System.out.println("DRESSINGSTC : Service de dressing disparu.");
		context.ungetService(reference);
		thread.stopThread();
	}

	public static class DressingThread extends Thread {

		private volatile boolean active = true;
		private final IDressingSuggestion service;

		public DressingThread(IDressingSuggestion service) {
			this.service = service;
		}

		public void run() {
			while (active) {
				System.out.println("DRESSINGSTC : Coat      : " + service.coatNeeded());
				System.out.println("DRESSINGSTC : Sun Glass : " + service.sunGlassesNeeded());
				System.out.println("DRESSINGSTC : Umbrella  : " + service.umbrellaNeeded());
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
					System.out.println("DRESSINGSTC : Fin du thread " + e.getMessage());
				}
			}
		}

		public void stopThread() {
			active = false;
		}
	}

} 