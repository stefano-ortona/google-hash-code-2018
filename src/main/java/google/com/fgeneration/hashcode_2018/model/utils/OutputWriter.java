package google.com.fgeneration.hashcode_2018.model.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import google.com.fgeneration.hashcode_2018.model.Ride;

public class OutputWriter {

  public static void writeToFile(String outputFileName, Map<Integer, List<Ride>> output)
      throws FileNotFoundException, UnsupportedEncodingException {
    final PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");
    int iter = 0;
    for (final int d : output.keySet()) {
      iter++;
      final List<Ride> driver_rides = output.get(d);
      writer.print(driver_rides.size() + " ");
      for (final Ride r : driver_rides) {
        writer.print(r.getId() + " ");
      }
      if (iter < output.size()) {
        writer.println();
      }
    }
    writer.close();
  }

}
