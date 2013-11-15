package org.osgi.demo.javacestmal.decoupled.controler;

import org.osgi.demo.javacestmal.decoupled.model.City;
import org.osgi.demo.javacestmal.decoupled.model.House;
import org.osgi.demo.javacestmal.decoupled.model.Street;
import org.osgi.demo.javacestmal.utils.ArgumentChecker;

/**
 * @javacestmal To correctly implement the ICityManagement interface, this class
 *              needs an access to the internal machinery of the City model
 *              package.
 * 
 * @author pierrick
 * 
 */
public class CityManagement implements ICityManagement {

	private static ICityManagement instance = null;

	public static synchronized ICityManagement instance() {
		if (instance == null) {
			instance = new CityManagement();
		}
		return instance;
	}

	private CityManagement() {

	}

	@Override
	public Street addHouse(final Street street, final House house) {

		ArgumentChecker.notNull(street, "CityManagement.addHouse, street is null");
		ArgumentChecker.notNull(house, "CityManagement.addHouse, house is null");

		street.houses().add(house);
		house.setStreet(street);

		return street;
	}

	@Override
	public City addStreet(final City city, final Street street) {

		ArgumentChecker.notNull(city, "CityManagement.addStreet, city is null");
		ArgumentChecker.notNull(street, "CityManagement.addStreet, street is null");

		city.streets().add(street);
		street.setCity(city);

		return city;
	}

	@Override
	public Boolean destroyHouse(final Street street, final House house) {

		ArgumentChecker.notNull(street, "CityManagement.destroyHouse, street is null");
		ArgumentChecker.notNull(house, "CityManagement.destroyHouse, house is null");

		Boolean removed = street.houses().remove(house);
		if (removed) {
			house.defaultStreet();
		} else {
			throw new RuntimeException("Failed to remove the house: " + house + " from the street: " + street);
		}

		return removed;
	}

	@Override
	public Boolean destroyStreet(final City city, final Street street) {

		ArgumentChecker.notNull(city, "CityManagement.destroyStreet, city is null");
		ArgumentChecker.notNull(street, "CityManagement.destroyStreet, street is null");

		Boolean removed = city.streets().remove(street);
		if (removed) {
			street.defaultCity();
		} else {
			throw new RuntimeException("Failed to remove the street: " + street + " from the city: " + city);
		}

		return removed;
	}

	@Override
	public Boolean replaceStreet(final City city, final Street oldStreet, final Street newStreet) {

		ArgumentChecker.notNull(city, "CityManagement.replaceStreet, city is null");
		ArgumentChecker.notNull(oldStreet, "CityManagement.replaceStreet, olsStreet is null");
		ArgumentChecker.notNull(newStreet, "CityManagement.replaceStreet, newStreet is null");

		Boolean removed = city.streets().remove(oldStreet);
		if (removed) {
			oldStreet.defaultCity();
			city.streets().add(newStreet);
			newStreet.setCity(city);
		} else {
			throw new RuntimeException("Failed to replace the old street: " + oldStreet + " to the new street: "
					+ newStreet + " from the city: " + city);
		}

		return removed;
	}
}
