package org.osgi.demo.javacestmal.monolithic;

/**
 * @javacestmal To correctly implement the ICityManagement interface, this class
 *              needs an access to the internal machinery of the City model
 *              package.
 * 
 * @author pierrick
 * 
 */
public class PartialCityManagement implements IPartialCityManagement {

	private static IPartialCityManagement instance = null;

	public static synchronized IPartialCityManagement instance() {
		if (instance == null) {
			instance = new PartialCityManagement();
		}
		return instance;
	}

	private PartialCityManagement() {

	}

	@Override
	public Street addHouse(final Street street, final House house) {
		street.houses().add(house);
		house.setStreet(street);

		return street;
	}

	@Override
	public City addStreet(final City city, final Street street) {
		city.streets().add(street);
		street.setCity(city);

		return city;
	}

	@Override
	public City replaceStreet(final City city, final Street oldStreet, final Street newStreet) {
		city.streets().remove(oldStreet);
		oldStreet.setCity(null);

		city.streets().add(newStreet);
		newStreet.setCity(city);

		return city;
	}

}
