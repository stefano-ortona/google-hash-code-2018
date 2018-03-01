/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.fgeneration.hashcode_2018;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import google.com.fgeneration.hashcode_2018.logic.AssignDriverLogic;
import google.com.fgeneration.hashcode_2018.logic.BestScoreRide;
import google.com.fgeneration.hashcode_2018.logic.MixedScoreRide;
import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Intersection;
import google.com.fgeneration.hashcode_2018.model.Ride;
import google.com.fgeneration.hashcode_2018.model.utils.OutputWriter;

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
  public void testExample() throws FileNotFoundException, UnsupportedEncodingException {
    System.out.println("TEST PRINT %%% Test 0");
    System.out.println("TEST PRINT %%% ");
    // create drivers
    final Driver driver0 = new Driver(0);
    final Driver driver1 = new Driver(1);
    final List<Driver> drivers = new ArrayList<Driver>();
    drivers.add(driver0);
    drivers.add(driver1);
    // create riders
    final Ride ride0 = new Ride(0, new Intersection(0, 0), new Intersection(1, 3), 2, 9);
    final Ride ride1 = new Ride(1, new Intersection(1, 2), new Intersection(1, 0), 0, 9);
    final Ride ride2 = new Ride(2, new Intersection(2, 0), new Intersection(2, 2), 0, 9);
    final List<Ride> rides = new ArrayList<Ride>();
    rides.add(ride0);
    rides.add(ride1);
    rides.add(ride2);
    // create city status
    final BestScoreRide score = new MixedScoreRide();
    final CityStatus status = new CityStatus(score, 2, 3, 4);
    status.setDrivers(drivers);
    status.setRides(rides);
    status.setMaxTime(10);

    // get the output
    final Map<Driver, List<Ride>> output = logic.assignAllRides(status);

    final List<Ride> takenRides = new ArrayList<Ride>();
    // check the output
    for (final Driver d : output.keySet()) {
      Assert.assertTrue(drivers.contains(d));
      final List<Ride> driver_rides = output.get(d);
      System.out.print("TEST PRINT %%% Driver ");
      System.out.println("TEST PRINT %%% " + d.getId());
      System.out.println("TEST PRINT %%% ");
      for (final Ride r : driver_rides) {
//        Assert.assertFalse(takenRides.contains(r));
        System.out.print("TEST PRINT %%% Rides: ");
        System.out.println("TEST PRINT %%% " + r);
        takenRides.add(r);
//        Assert.assertTrue(takenRides.contains(r));
      }
      System.out.println("TEST PRINT %%% -----------------------------");
      System.out.println("TEST PRINT %%% -----------------------------");
    }
    System.out.println("TEST PRINT %%% ################");
    System.out.println("TEST PRINT %%% ################");
    
    Assert.assertEquals(2, output.keySet().size());
    Assert.assertEquals(1, output.get(driver0).size());
    Assert.assertTrue(output.get(driver0).contains(ride0));
    Assert.assertEquals(1, output.get(driver1).size());
    Assert.assertTrue(output.get(driver1).contains(ride2));
    
    OutputWriter.writeToFile("src/main/resources/google/com/fgeneration/hashcode_2018/output/test.in", output);
  }

}
