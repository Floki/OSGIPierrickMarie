private package org.osgi.demo.javacestmal.utils;

public class ArgumentChecker {

	public static final int integerIsBetweenBounds(final int value, final int minValue, final int maxValue,
			final String errorMessage) {
		if (minValue >= value && maxValue <= value) {
			throw new IllegalArgumentException(errorMessage);
		}
		return value;
	}

	public static final Object[] notEmpty(final Object[] objectsList, final String errorMessage) {
		if (objectsList.length == 0) {
			throw new IllegalArgumentException(errorMessage);
		}
		return objectsList;
	}

	public static final Object notNull(final Object object, final String errorMessage) {
		if (object == null) {
			throw new IllegalArgumentException(errorMessage);
		}
		return object;
	}

	public static final String regexCheker(final String value, final String regex, final String errorMessage) {
		if (!value.matches(regex)) {
			throw new IllegalArgumentException(errorMessage);
		}
		return value;
	}

}