package google.com.fgeneration.hashcode_2018.logic;

import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Ride;
import google.com.fgeneration.hashcode_2018.utils.Utils;

public class PredictiveScoreRide extends BestScoreRide {

  private static double DISTANCE_WEIGHT = 0.1;
  private static double RIDE_LENGHT_WEIGHT = 0.1;
  private static double BONUS_WEIGHT = 1.;
  private static double WAIT_LENGHT_WEIGHT = 1.;

  @Override
  public double getScore(Driver driver, Ride ride, CityStatus status) {
    final double distanceScore = (DISTANCE_WEIGHT * Utils.getDistance(driver.getLastPositon(), ride.getStart()));

    final double rideLength = (RIDE_LENGHT_WEIGHT * Utils.getDistance(ride.getStart(), ride.getEnd()));

    final double bonus = BONUS_WEIGHT * (Utils.gotBonusForRide(driver, ride) ? status.getBonus() : 0);

    // check if it has to wait, and for how long
    int waitTime = 0;
    final int timeReachDest = driver.getNextAvailableTime()
        + Utils.getDistance(driver.getLastPositon(), ride.getStart());
    if (timeReachDest < ride.getMinStartTime()) {
      waitTime = ride.getMinStartTime() - timeReachDest;
    }
    final double waitScore = WAIT_LENGHT_WEIGHT * waitTime;

    return (rideLength + bonus) - distanceScore - waitScore;
  }

}