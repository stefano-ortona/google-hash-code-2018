package google.com.fgeneration.hashcode_2018.logic;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Ride;
import google.com.fgeneration.hashcode_2018.utils.Utils;

public class AssignDriverLogic {
  private static final Logger LOGGER = LoggerFactory.getLogger(AssignDriverLogic.class);

  public Map<Integer, List<Ride>> assignAllRides(final CityStatus status) {

    final Map<Integer, List<Ride>> output = Maps.newHashMap();
    int totScore = 0;
    boolean isFinished = false;
    for (int instant = 0; instant < status.getMaxTime(); instant++) {
      if (isFinished) {
        break;
      }
      final List<Driver> nextAvailableDrivers = status.getAvailableDrivers(instant);
      LOGGER.info("Round {}, there are {} available drivers to assign. (Round {} out of {})", instant,
          nextAvailableDrivers.size(), instant, status.getMaxTime());
      for (final Driver driver : nextAvailableDrivers) {
        final Ride curRide = status.assignNextBestRide(driver);
        if (curRide != null) {
          totScore += Utils.getDistance(curRide.getStart(), curRide.getEnd());
          if (Utils.gotBonusForRide(driver, curRide)) {
            totScore += status.getBonus();
          }
          this.modifyDriver(driver, curRide);
          final List<Ride> curDriverRide = output.getOrDefault(driver.getId(), Lists.newArrayList());
          curDriverRide.add(curRide);
          output.put(driver.getId(), curDriverRide);
        } else {
          status.getDrivers().remove(driver);
        }
        if (status.getRides().isEmpty()) {
          LOGGER.info("All rides have been satisfied motherfucker!");
          isFinished = true;
          break;
        }
      }
    }
    LOGGER.info("Total score for the current solution: '{}'.", totScore);
    return output;
  }

  private void modifyDriver(Driver driver, Ride ride) {
    final int driverAtPositionStart = driver.getNextAvailableTime()
        + Utils.getDistance(driver.getLastPositon(), ride.getStart());
    int startRide = driverAtPositionStart;
    if (driverAtPositionStart < ride.getMinStartTime()) {
      startRide = ride.getMinStartTime();
    }
    final int nextAvailableTime = startRide + Utils.getDistance(ride.getStart(), ride.getEnd());
    driver.setNextAvailableTime(nextAvailableTime);
    driver.setLastPositon(ride.getEnd());
  }

}
