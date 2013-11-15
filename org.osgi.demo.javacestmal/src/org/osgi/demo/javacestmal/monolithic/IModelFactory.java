package org.osgi.demo.javacestmal.monolithic;

/**
 * @javacestmal Only the methods of this interface should be public. Others
 *              methods should stay protected.
 * 
 * @author pierrick
 * 
 */
public interface IModelFactory {

	public City createCity(final String name);

	public House createHouse(final String address);

	public Street createStreet(final String name);

}
