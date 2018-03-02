package google.com.fgeneration.hashcode_2018.logic;

import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Ride;
import google.com.fgeneration.hashcode_2018.utils.Utils;

public class WeightedAverageScore extends BestScoreRide {

  double disWeight = 1.;
  double waitWight = 1.;

  @Override
  public double getScore(Driver driver, Ride ride, CityStatus status) {

    final int toStartDistance = Utils.getDistance(driver.getLastPositon(), ride.getStart());
    final int timeToStart = Math.max(ride.getMinStartTime() - (driver.getNextAvailableTime() + toStartDistance), 0);

    return (disWeight * toStartDistance) + (waitWight * timeToStart);
  }

}
