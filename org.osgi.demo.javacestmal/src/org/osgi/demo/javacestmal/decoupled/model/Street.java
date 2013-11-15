package org.osgi.demo.javacestmal.decoupled.model;

import java.util.ArrayList;
import java.util.List;

import org.osgi.demo.javacestmal.utils.ArgumentChecker;

/**
 * @javacestmal To implement controlers.ICityManagement or
 *              controlers.IPartialCityManagement interfaces, I have to change
 *              the visibility of this class to public
 * 
 * @author pierrick
 * 
 */
public class Street {

	private String name;
	private final List<House> houses;
	private City city;

	/**
	 * @javacestmal protected constructor, reserved exclusively for this package
	 * 
	 */
	Street() {
		city = null;
		name = "";
		houses = new ArrayList<House>();
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 * 
	 * @return City: the city which contains the street
	 */
	public City city() {
		return city;
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 */
	public void defaultCity() {
		city = null;
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 * 
	 * @return List<House>: the list of the houses of the street
	 */
	public List<House> houses() {
		return houses;
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 * 
	 * @return String: the name of the street
	 */
	public String name() {
		return name;
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 * 
	 * @param City
	 *            : the city which contains the street
	 * @return Street: the changed street
	 */
	public Street setCity(final City city) {

		ArgumentChecker.notNull(city, "Street.setCity, city is null");

		this.city = city;
		return this;
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 * 
	 * @param City
	 *            : the name of the street
	 * @return Street: the changed street
	 */
	public Street setName(final String name) {

		ArgumentChecker.regexCheker(name, "^.{3,}$", "City.setName, the name is not valid");

		this.name = name;
		return this;
	}

	@Override
	public String toString() {
		StringBuffer toString = new StringBuffer();

		toString.append("Street.name=");
		toString.append(name);

		return toString.toString();
	}
}
