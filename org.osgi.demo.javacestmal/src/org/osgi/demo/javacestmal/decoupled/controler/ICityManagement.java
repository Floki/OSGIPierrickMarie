package org.osgi.demo.javacestmal.decoupled.controler;

import org.osgi.demo.javacestmal.decoupled.model.City;
import org.osgi.demo.javacestmal.decoupled.model.House;
import org.osgi.demo.javacestmal.decoupled.model.Street;

/**
 * @javacestmal the complete interface to manipulate the City model.
 *              <b>Problem</b>: to work properly it needs the getters and
 *              setters of the classes City, Street and House. So, these methods
 *              are public whereas they should stay protected and don't be
 *              include in the public API of the City model.
 * 
 * @author pierrick
 * 
 */
public interface ICityManagement {

	public Street addHouse(final Street street, final House house);

	public City addStreet(final City city, final Street street);

	public Boolean destroyHouse(final Street street, final House house);

	public Boolean destroyStreet(final City city, final Street street);

	public Boolean replaceStreet(final City city, final Street oldStreet, final Street newStreet);

}
