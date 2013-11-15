package org.osgi.demo.javacestmal.decoupled.model;

import org.osgi.demo.javacestmal.utils.ArgumentChecker;

/**
 * @javacestmal To implement controlers.ICityManagement or
 *              controlers.IPartialCityManagement interfaces, I have to change
 *              the visibility of this class to public
 * 
 * @author pierrick
 * 
 */
public class House {

	private String address;
	private Street street;

	/**
	 * @javacestmal constructeur protected par defaut
	 * 
	 * @param city
	 */
	House() {
		setAddress("");
		street = null;
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 * 
	 * @return String: the address of the house
	 */
	public String address() {
		return address;
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 */
	public void defaultStreet() {
		street = null;
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 * 
	 * @param String
	 *            : the address of the house
	 * @return House: the changed house
	 */
	public House setAddress(final String address) {

		ArgumentChecker.regexCheker(address, "^(\\w{5,})(\\s\\w{5,}){2,}$",
				"house.setAddress, the address is not valid");

		this.address = address;
		return this;
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 * 
	 * @param Street
	 *            : the street which contains the house
	 * @return house: the changed house
	 */
	public House setStreet(final Street street) {

		ArgumentChecker.notNull(street, "House.setStreet, street is null");

		this.street = street;
		return this;
	}

	/**
	 * @javacestmal <b>Problem</b>: to correctly implement the interfaces
	 *              controlers.ICityManagement or
	 *              controlers.IPartialCityManagement this method have to be
	 *              public. So, the public API of this package includes this
	 *              method whereas it should not be!
	 * 
	 * @return Street: the street which contains the houe
	 */
	public Street street() {
		return street;
	}

	@Override
	public String toString() {
		StringBuffer toString = new StringBuffer();

		toString.append("House.address=");
		toString.append(address);

		return toString.toString();
	}
}
