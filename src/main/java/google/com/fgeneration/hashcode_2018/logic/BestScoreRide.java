package google.com.fgeneration.hashcode_2018.logic;

import java.util.HashMap;
import java.util.Map;

import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Ride;

public abstract class BestScoreRide {

	public Ride getBestRide(Driver driver, CityStatus status) {
		// iterare tutti i ride e calcolare lo score, scegliere best
		// if condizione validità errata allora score -1
		Map<Integer, Driver> scoreMap = new HashMap<>();
		for (Ride ride : status.getRides()) {
			
		}
		
		return null;
	}

	private boolean isValid(Driver driver, Ride ride) {
		return true;
	}

	public abstract int getScore(Driver driver, Ride ride, CityStatus status);

}
