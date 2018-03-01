package google.com.fgeneration.hashcode_2018.model;

import java.util.List;

import com.google.common.collect.Lists;

import google.com.fgeneration.hashcode_2018.logic.BestScoreRide;

public class CityStatus {
  List<Driver> drivers;
  List<Ride> rides;
  int maxTime;
  BestScoreRide bestScore;

  public CityStatus(BestScoreRide bestScore) {
    this.bestScore = bestScore;

  }

  public List<Driver> getAvailableDrivers(int time) {
    final List<Driver> drivers = Lists.newArrayList();

    return drivers;
  }

  public Ride assignNextBestRide(Driver driver) {
    final Ride ride = this.bestScore.getBestRide(driver, this);
    if (ride == null) {
      return null;
    }
    this.rides.remove(ride);
    driver.lastPositon = ride.getEnd();
    // TODO - modify next available time of the driver
    return ride;

  }

}
