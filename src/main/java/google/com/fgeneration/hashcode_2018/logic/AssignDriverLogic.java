package google.com.fgeneration.hashcode_2018.logic;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AtomicDouble;

import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Ride;
import google.com.fgeneration.hashcode_2018.utils.Utils;

public class AssignDriverLogic {
  private static final Logger LOGGER = LoggerFactory.getLogger(AssignDriverLogic.class);

  public Map<Integer, List<Ride>> assignAllRides(final CityStatus status, AtomicDouble curScore) {
    final int totPosScore = computeTotPossibleScore(status.getRides(), status.getBonus());

    final Map<Integer, List<Ride>> output = Maps.newHashMap();
    int totScore = 0;
    boolean isFinished = false;
    int totSavedScore = 0;
    for (int instant = 0; instant <= status.getMaxTime(); instant++) {
      if (isFinished) {
        break;
      }
      final List<Driver> nextAvailableDrivers = status.getAvailableDrivers(instant);
      // LOGGER.info("Round {}, there are {} available drivers to assign. (Round {} out of {})", instant,
      // nextAvailableDrivers.size(), instant, status.getMaxTime());
      for (final Driver driver : nextAvailableDrivers) {
        Ride curRide = null;
        curRide = status.assignNextBestRide(driver);
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
          LOGGER.info("Removing driver at position '{}' and time '{}'.", driver.getLastPositon(),
              driver.getNextAvailableTime());
          status.getDrivers().remove(driver);
          if (driver.getSavedScore() > 0) {
            totSavedScore += driver.getSavedScore();
          }
        }
        if (status.getRides().isEmpty()) {
          LOGGER.info("All rides have been satisfied motherfucker!");
          isFinished = true;
          break;
        }
        if (status.getDrivers().isEmpty()) {
          LOGGER.info("No more rides to assign to drivers, sorry!");
          isFinished = true;
          break;
        }
      }
    }
    LOGGER.info("Total score for the current solution: '{}' (Max possible score: '{}')", totScore, totPosScore);
    LOGGER.info("Total score saved for the current solution: '{}'", totSavedScore);
    curScore.set(totScore);
    return output;
  }

  private int computeTotPossibleScore(List<Ride> rides, int bonus) {
    final AtomicInteger totScore = new AtomicInteger(0);
    rides.forEach(r -> totScore.getAndAdd(Utils.getDistance(r.getStart(), r.getEnd()) + bonus));
    return totScore.get();
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
