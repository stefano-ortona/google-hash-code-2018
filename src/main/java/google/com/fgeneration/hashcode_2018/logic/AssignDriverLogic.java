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

  public Map<Driver, List<Ride>> assignAllRides(final CityStatus status) {

    final Map<Driver, List<Ride>> output = Maps.newHashMap();
    int totScore = 0;
    for (int instant = 0; instant < status.getMaxTime(); instant++) {
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
          final List<Ride> curDriverRide = output.getOrDefault(driver, Lists.newArrayList());
          curDriverRide.add(curRide);
        } else {
          // TODO: maybe can remove driver from the list
        }
      }
    }
    LOGGER.info("Total score for the current solution: '{}'.", totScore);
    return output;
  }

}
