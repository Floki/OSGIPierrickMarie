package org.osgi.demo.javacestmal.decoupled.view;

import org.osgi.demo.javacestmal.decoupled.model.City;
import org.osgi.demo.javacestmal.decoupled.model.House;
import org.osgi.demo.javacestmal.decoupled.model.Street;

public class CityView implements ICityView {

	private static ICityView instance = null;

	public static synchronized ICityView instance() {
		if (instance == null) {
			instance = new CityView();
		}
		return instance;
	}

	private CityView() {

	}

	@Override
	public void showCity(final City city) {
		StringBuffer buffer = new StringBuffer();

		buffer.append(city);
		buffer.append("\n");

		for (Street street : city.streets()) {
			buffer.append(street);

			for (House house : street.houses()) {
				buffer.append(house);
			}
		}

		System.out.println(buffer.toString());
	}

}
