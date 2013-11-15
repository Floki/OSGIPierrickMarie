package org.osgi.demo.javacestmal.monolithic;

import java.util.ArrayList;
import java.util.List;

import org.osgi.demo.javacestmal.utils.ArgumentChecker;

public class City {

	private String name;
	private Integer nbInhabitants;

	private final List<Street> streets;

	City() {
		name = "";
		streets = new ArrayList<Street>();
	}

	String name() {
		return name;
	}

	Integer nbInhabitants() {
		return nbInhabitants;
	}

	City setName(final String name) {

		ArgumentChecker.regexCheker(name, "^\\w{3,}", "City.setName, the name is not valid");

		this.name = name;
		return this;
	}

	City setNbInhabitants(final Integer nbInhabitants) {

		ArgumentChecker.integerIsBetweenBounds(nbInhabitants, 0, Integer.MAX_VALUE,
				"City.setNbInhabitants, bbInhabitants is negativ");

		this.nbInhabitants = nbInhabitants;
		return this;
	}

	List<Street> streets() {
		return streets;
	}
}
