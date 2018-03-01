package google.com.fgeneration.hashcode_2018.logic;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Ride;

public class AssignDriverLogic {

  public Map<Driver, List<Ride>> assignAllRides(final CityStatus status) {
    final Map<Driver, List<Ride>> output = Maps.newHashMap();

    // for each instant t
    // for each avaibale driver d at t
    // get next best ride for d
    // adjorn the output map

    return output;

  }

}
