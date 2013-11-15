package org.osgi.demo.javacestmal.test;

import org.osgi.demo.javacestmal.monolithic.City;
import org.osgi.demo.javacestmal.monolithic.CityManagement;
import org.osgi.demo.javacestmal.monolithic.CityView;
import org.osgi.demo.javacestmal.monolithic.ModelFactory;
import org.osgi.demo.javacestmal.monolithic.Street;

public class MonolithicMain {
	public static void main(final String[] args) {

		/**
		 * @javacetmal At least, the protected constructor prevents me from creating
		 *             new classes model without using the factory class.
		 */
		City myCity = ModelFactory.instance().createCity("GothamCity");
		Street myStreet = ModelFactory.instance().createStreet("Main Strip");

		/**
		 * @javacestmal The following piece of code is not possible with the
		 *              monolithic package (good point). But classes (model,
		 *              controller and view) are located in the same package.
		 *              It's dirty !
		 */
		// System.out.println("Dirty code");
		// myCity.streets().add(myStreet);
		// CityView.instance().showCity(myCity);
		// System.out.println("%-----------------------------\n");

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
