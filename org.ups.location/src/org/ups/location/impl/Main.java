package org.ups.location.impl;
import java.util.Scanner;

import org.ups.location.ILocation;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("LOCATION TEST.");
		ILocation location = new LocationImpl();
		System.out.println(location);
		Scanner lectureClavier = new Scanner(System.in);
	    int choix = lectureClavier.nextInt();
	    while(choix != 0) {
	    	System.out.println(location.getLatitude() + "," + location.getLongitude());
	    	choix = lectureClavier.nextInt();
	    }
	    System.out.println("END LOCATION TEST.");
	}

}
