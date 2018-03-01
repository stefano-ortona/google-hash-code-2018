package google.com.fgeneration.hashcode_2018.utils;

import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Intersection;
import google.com.fgeneration.hashcode_2018.model.Ride;

public class Utils {

  public static int getDistance(Intersection i1, Intersection i2) {
    return Math.abs(i1.getRow() - i2.getRow()) + Math.abs(i1.getColumn() - i2.getColumn());
  }

  public static boolean gotBonusForRide(final Driver driver, final Ride ride) {
    return (driver.getNextAvailableTime() + Utils.getDistance(driver.getLastPositon(), ride.getStart())) < ride
        .getMinStartTime();
  }

}
