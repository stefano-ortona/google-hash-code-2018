/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.fgeneration.hashcode_2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import google.com.fgeneration.hashcode_2018.logic.AssignDriverLogic;
import google.com.fgeneration.hashcode_2018.logic.DefaultScoreRide;
import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Intersection;
import google.com.fgeneration.hashcode_2018.model.Ride;

/**
 * Unit test
 */
public class AppTest {
  private static AssignDriverLogic logic;

  @BeforeClass
  public static void bringUp() {
	logic = new AssignDriverLogic();
  }
  
  @Before
  public void eachTest() {
	
  }

  @Test
  public void testExample() {
	  System.out.println("TEST PRINT %%% Test 0");
	  System.out.println("TEST PRINT %%% ");
	  // create drivers
	  Driver driver0 = new Driver(0);
	  Driver driver1 = new Driver(1);
	  List<Driver> drivers = new ArrayList<Driver>();
	  drivers.add(driver0);
	  drivers.add(driver1);
	  // create riders
	  Ride ride0 = new Ride(0, new Intersection(0, 0), new Intersection(3, 2), 2, 9);
	  Ride ride1 = new Ride(1, new Intersection(2, 1), new Intersection(0, 0), 0, 9);
	  Ride ride2 = new Ride(1, new Intersection(0, 2), new Intersection(2, 0), 0, 9);
	  List<Ride> rides = new ArrayList<Ride>();
	  rides.add(ride0);
	  rides.add(ride1);
	  rides.add(ride2);
	  // create city status
	  CityStatus status = new CityStatus(new DefaultScoreRide(), 2, 3, 4);
	  status.setDrivers(drivers);
	  status.setRides(rides);
	  status.setMaxTime(10);
	  
      // get the output
	  Map<Driver, List<Ride>> output = logic.assignAllRides(status);
	  
	  List <Ride> takenRides = new ArrayList<Ride>();
	  // check the output
	  for(Driver d:output.keySet()) {
		Assert.assertTrue(drivers.contains(d));
		List<Ride> driver_rides = output.get(d);
		System.out.print("TEST PRINT %%% Driver ");
		System.out.println("TEST PRINT %%%" + d);
		System.out.println("TEST PRINT %%% ");
		for(Ride r:driver_rides) {
			Assert.assertFalse(takenRides.contains(r));
			System.out.print("TEST PRINT %%% Rides: ");
			System.out.println("TEST PRINT %%%" + r);
			takenRides.add(r);
			Assert.assertTrue(takenRides.contains(r));
		}
		System.out.println("TEST PRINT %%% -----------------------------");
		System.out.println("TEST PRINT %%% -----------------------------");
	  }
	  System.out.println("TEST PRINT %%% ################");
	  System.out.println("TEST PRINT %%% ################");
	  
  }

}
