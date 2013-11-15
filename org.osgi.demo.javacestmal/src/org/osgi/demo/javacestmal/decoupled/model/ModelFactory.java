package org.osgi.demo.javacestmal.decoupled.model;

public class ModelFactory implements IModelFactory {

	private static IModelFactory instance = null;

	public static synchronized IModelFactory instance() {
		if (instance == null) {
			instance = new ModelFactory();
		}
		return instance;
	}

	private ModelFactory() {

	}

	@Override
	public City createCity(final String name) {
		City retCity = new City();
		return retCity.setName(name);
	}

	@Override
	public House createHouse(final String address) {
		House retHouse = new House();
		return retHouse.setAddress(address);
	}

	@Override
	public Street createStreet(final String name) {
		Street retStreet = new Street();
		return retStreet.setName(name);
	}

}
