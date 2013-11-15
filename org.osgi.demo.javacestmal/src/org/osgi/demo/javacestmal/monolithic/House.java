package org.osgi.demo.javacestmal.monolithic;

import org.osgi.demo.javacestmal.utils.ArgumentChecker;

public class House {

	private String address;
	private Street street;

	House() {
		setAddress("");
		street = null;
	}

	String getAddress() {
		return address;
	}

	Street getStreet() {
		return street;
	}

	House setAddress(final String address) {

		ArgumentChecker.regexCheker(address, "^(\\w{5,})(\\s\\w{5,}){2,}$",
				"house.setAddress, the address is not valid");

		this.address = address;
		return this;
	}

	House setStreet(final Street street) {

		ArgumentChecker.notNull(street, "House.setStreet, street is null");

		this.street = street;
		return this;
	}
}
