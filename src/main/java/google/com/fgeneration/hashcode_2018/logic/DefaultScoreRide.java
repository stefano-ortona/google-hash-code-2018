package google.com.fgeneration.hashcode_2018.logic;

import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Ride;
import google.com.fgeneration.hashcode_2018.utils.Utils;

public class DefaultScoreRide extends BestScoreRide {

	@Override
	public double getScore(Driver driver, Ride ride, CityStatus status) {
		double distanceScore = Utils.getDistance(driver.getLastPositon(),  ride.getStart());
		return distanceScore;
	}

}
