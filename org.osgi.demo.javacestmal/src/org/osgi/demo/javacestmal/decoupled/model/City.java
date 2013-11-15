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
public class City {

	private String name;
	private Integer nbInhabitants;

	private final List<Street> streets;

	/**
	 * @javacestmal protected constructor, reserved exclusively for this package
	 * 
	 */
	City() {
		name = "";
		streets = new ArrayList<Street>();
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 * 
	 * @return String: the name of the city
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
	 * @return Integer: the number of inhabitant of the city
	 */
	public Integer nbInhabitants() {
		return nbInhabitants;
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 * 
	 * @param String
	 *            : the name of the city
	 * 
	 * @return City: the changed city
	 */
	public City setName(final String name) {

		ArgumentChecker.regexCheker(name, "^.{3,}", "City.setName, the name is not valid");

		this.name = name;
		return this;
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 * 
	 * @return Integer: the number of inhabitant of the city
	 * @return City: the changed city
	 */
	public City setNbInhabitants(final Integer nbInhabitants) {

		ArgumentChecker.integerIsBetweenBounds(nbInhabitants, 0, Integer.MAX_VALUE,
				"City.setNbInhabitants, bbInhabitants is negativ");

		this.nbInhabitants = nbInhabitants;
		return this;
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 * 
	 * @return List<Street>: the list of the streets of the city
	 */
	public List<Street> streets() {
		return streets;
	}

	@Override
	public String toString() {
		StringBuffer toString = new StringBuffer();

		toString.append("City.name=");
		toString.append(name);

		return toString.toString();
	}
}
