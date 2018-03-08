package google.com.fgeneration.hashcode_2018.logic;

import java.util.List;

import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Ride;
import google.com.fgeneration.hashcode_2018.utils.Utils;

public abstract class BestScoreRide {

  public Ride getBestRide(Driver driver, CityStatus status) {
    if ((status.getRides() == null) || status.getRides().isEmpty()) {
      return null;
    }
    Ride bestRide = null;
    double minBestRide = Double.MAX_VALUE;
    for (final Ride ride : status.getRides()) {
      if (canCompletePreviouslyAssignedRide(driver, ride) && isValid(driver, ride)) {
        final double curScore = getScore(driver, ride, status);
        if (curScore <= minBestRide) {
          minBestRide = curScore;
          bestRide = ride;
        }
      }
    }
    if (bestRide == null) {
      final Ride toReturn = driver.getPreviouslyAssignedRide();
      driver.setPreviouslyAssignedRide(null);
      // return null if no previous ride has been assigned
      return toReturn;
    }

    if (!canCompletePreviouslyAssignedRide(driver, bestRide)) {
      final Ride toReturn = driver.getPreviouslyAssignedRide();
      driver.setPreviouslyAssignedRide(null);
      return toReturn;
    }

    if ((driver.getPreviouslyAssignedRide() == null) && isLastRide(driver, bestRide, status.getRides())) {
      driver.setPreviouslyAssignedRide(bestRide);
      status.getRides().remove(bestRide);
      return getBestRide(driver, status);
    }
    if (driver.getPreviouslyAssignedRide() != null) {
      driver.incrementSavedScore(bestRide, status.getBonus());
    }
    return bestRide;
  }

  private boolean isValid(Driver driver, Ride ride) {
    return ((driver.getNextAvailableTime() + Utils.getDistance(driver.getLastPositon(), ride.getStart())
        + Utils.getDistance(ride.getStart(), ride.getEnd())) < ride.getMaxEndTime());
  }

  public abstract double getScore(Driver driver, Ride ride, CityStatus status);

  private boolean isLastRide(final Driver driver, final Ride curRide, final List<Ride> otherRides) {
    // get new timing
    final int newTime = getNextAvailableTime(driver, curRide);
    final Driver fakeDriver = new Driver(-1);
    fakeDriver.setLastPositon(curRide.getEnd());
    fakeDriver.setNextAvailableTime(newTime);

    for (final Ride oneRide : otherRides) {
      if (oneRide.getId() != curRide.getId()) {
        if (isValid(fakeDriver, oneRide)) {
          // there is at least another satisfiable ride
          return false;
        }
      }
    }
    return true;
  }

  private boolean canCompletePreviouslyAssignedRide(Driver d, Ride ride) {
    if (d.getPreviouslyAssignedRide() == null) {
      return true;
    }

    final Driver fakeDriver = new Driver(-1);
    fakeDriver.setLastPositon(ride.getEnd());
    final int newTime = getNextAvailableTime(d, ride);
    fakeDriver.setNextAvailableTime(newTime);
    return isValid(fakeDriver, d.getPreviouslyAssignedRide());
  }

  private int getNextAvailableTime(Driver driver, Ride ride) {
    final int driverAtPositionStart = driver.getNextAvailableTime()
        + Utils.getDistance(driver.getLastPositon(), ride.getStart());
    int startRide = driverAtPositionStart;
    if (driverAtPositionStart < ride.getMinStartTime()) {
      startRide = ride.getMinStartTime();
    }
    final int nextAvailableTime = startRide + Utils.getDistance(ride.getStart(), ride.getEnd());
    return nextAvailableTime;
  }

}
