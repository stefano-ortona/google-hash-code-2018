package google.com.fgeneration.hashcode_2018.logic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Ride;
import google.com.fgeneration.hashcode_2018.utils.Utils;

public abstract class BestScoreRide {

	public Ride getBestRide(Driver driver, CityStatus status) {
		Map<Integer, Ride> scoreMap = new HashMap<>();
		for (Ride ride : status.getRides()) {
			if (isValid(driver, ride)) {
				scoreMap.put(getScore(driver, ride, status), ride);
			} else {
				scoreMap.put(-1, ride);
			}
		}

		int maxScore = Collections.max(scoreMap.keySet());
		if (maxScore == -1) {
			return null;
		}

		return scoreMap.get(maxScore); // return best ride
	}

	private boolean isValid(Driver driver, Ride ride) {
		return driver.getNextAvailableTime() + Utils.getDistance(ride.getStart(), ride.getEnd()) < ride.getMaxEndTime();
	}

	public abstract int getScore(Driver driver, Ride ride, CityStatus status);

}
