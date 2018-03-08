package google.com.fgeneration.hashcode_2018.logic;

import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Ride;
import google.com.fgeneration.hashcode_2018.utils.Utils;

public class WeightedAverageScore extends BestScoreRide {

  // best weights for test A,B,E
  double disWeight = 1.;
  double waitWight = 1.;
  double totRideLen = 1.;

  // best score for D
  // double disWeight = 0.4;
  // double waitWight = 0.6;
  // double totRideLen = 0.;

  // best weights for test A,B,E
  // double disWeight = 0.;
  // double waitWight = 1.;
  // double totRideLen = 0.;

  // best weights for test C
  // double disWeight = 1.;
  // double waitWight = 0.;
  // double totRideLen = 0.;

  public WeightedAverageScore(double disWeight, double waitWeight) {
    this.disWeight = disWeight;
    this.waitWight = waitWeight;
  }

  @Override
  public double getScore(Driver driver, Ride ride, CityStatus status) {

    final int toStartDistance = Utils.getDistance(driver.getLastPositon(), ride.getStart());
    final int timeToStart = Math.max(ride.getMinStartTime() - (driver.getNextAvailableTime() + toStartDistance), 0);

    return ((disWeight * toStartDistance) + (waitWight * timeToStart));
  }

}
