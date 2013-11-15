package org.osgi.demo.javacestmal.monolithic;

import java.util.ArrayList;
import java.util.List;

import org.osgi.demo.javacestmal.utils.ArgumentChecker;

public class Street {

	private String name;
	private final List<House> houses;
	private City city;

	Street() {
		city = null;
		name = "";
		houses = new ArrayList<House>();
	}

	City city() {
		return city;
	}

	List<House> houses() {
		return houses;
	}

	String name() {
		return name;
	}

	Street setCity(final City city) {

		ArgumentChecker.notNull(city, "Street.setCity, city is null");

		this.city = city;
		return this;
	}

	Street setName(final String name) {

		ArgumentChecker.regexCheker(name, "^\\w{3,}$", "City.setName, the name is not valid");

		this.name = name;
		return this;
	}
}
