package google.com.fgeneration.hashcode_2018.logic;

import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Ride;
import google.com.fgeneration.hashcode_2018.utils.Utils;

public class MixedScoreRide extends BestScoreRide {

  private static double DISTANCE_WEIGHT = 0;
  private static double RIDE_LENGHT_WEIGHT = 1.0;
  private static double BONUS_WEIGHT = 1.0;

  @Override
  public double getScore(Driver driver, Ride ride, CityStatus status) {

    final double maxDistance = status.getColumn() + status.getRow();

    final double distanceScore = DISTANCE_WEIGHT
        * (Utils.getDistance(driver.getLastPositon(), ride.getStart()) / maxDistance);

    final double rideLength = RIDE_LENGHT_WEIGHT * (Utils.getDistance(ride.getStart(), ride.getEnd()));

    final double bonus = BONUS_WEIGHT * (Utils.gotBonusForRide(driver, ride) ? status.getBonus() : 0);

    return (rideLength + bonus) - distanceScore;
  }

}
