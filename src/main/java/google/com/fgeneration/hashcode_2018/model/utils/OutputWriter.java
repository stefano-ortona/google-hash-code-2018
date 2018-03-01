package google.com.fgeneration.hashcode_2018.model.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Ride;

public class OutputWriter {

	public static void writeToFile(String outputFileName, Map<Driver, List<Ride>> output) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");
		for (final Driver d : output.keySet()) {
		  writer.print(d.getId() + " ");
		  final List<Ride> driver_rides = output.get(d);  
		  for (final Ride r : driver_rides) {
		    writer.print(r.getId() + " ");  
		  }
		  writer.println();
		 }
		writer.close();
	}

}
