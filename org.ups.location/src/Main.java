import java.util.Scanner;

import org.ups.location.ILocation;
import org.ups.location.impl.LocationImpl;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ILocation location = new LocationImpl();
		System.out.println(location);
		Scanner lectureClavier = new Scanner(System.in);
	    int choix = lectureClavier.nextInt();
	    while(choix != 0) {
	    	System.out.println(location.getLatitude() + "," + location.getLongitude());
	    	choix = lectureClavier.nextInt();
	    }
	}

}
