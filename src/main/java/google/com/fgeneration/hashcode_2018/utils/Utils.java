package google.com.fgeneration.hashcode_2018.utils;

import google.com.fgeneration.hashcode_2018.model.Intersection;

public class Utils {

	public static int getDistance(Intersection i1, Intersection i2) {
		return Math.abs(i1.getRow()-i2.getRow()) + Math.abs(i1.getColumn()-i2.getColumn());
	}

}
