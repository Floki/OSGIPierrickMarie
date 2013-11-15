package org.osgi.demo.javacestmal.test;

import org.osgi.demo.javacestmal.decoupled.controler.CityManagement;
import org.osgi.demo.javacestmal.decoupled.model.City;
import org.osgi.demo.javacestmal.decoupled.model.ModelFactory;
import org.osgi.demo.javacestmal.decoupled.model.Street;
import org.osgi.demo.javacestmal.decoupled.view.CityView;

public class DecoupledMain {

	public static void main(final String[] args) {

		/**
		 * @javacetmal At least, the protected constructor disable me to create
		 *             new classes model without use the factory class.
		 */
		City myCity = ModelFactory.instance().createCity("GothamCity");
		Street myStreet = ModelFactory.instance().createStreet("Main Strip");

		/**
		 * @javacestmal This peace of code is incorrect.
		 *              controlers.ICityManagement have to be used to add a
		 *              street to a city. The public API of the City model does
		 *              not forbidden that!
		 */
		System.out.println("Dirty code");
		myCity.streets().add(myStreet);
		CityView.instance().showCity(myCity);
		System.out.println("%-----------------------------\n");

		/**
		 * @javacestmal Good code that uses the real public API. At runtime,
		 *              there is no difference between the two pieces of code.
		 *              But the second one is not dirty!
		 */
		System.out.println("Good code");
		CityManagement.instance().destroyStreet(myCity, myStreet);
		CityManagement.instance().addStreet(myCity, myStreet);
		CityView.instance().showCity(myCity);
		System.out.println("%-----------------------------\n");
	}
}
