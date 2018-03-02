package google.com.fgeneration.hashcode_2018;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Lists;

import google.com.fgeneration.hashcode_2018.logic.AssignDriverLogic;
import google.com.fgeneration.hashcode_2018.logic.BestScoreRide;
import google.com.fgeneration.hashcode_2018.logic.WeightedAverageScore;
import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Ride;
import google.com.fgeneration.hashcode_2018.model.utils.OutputWriter;

public class MainFilesTest {

  private static BestScoreRide score;

  @BeforeClass
  public static void bringUp() {
    // score = new PredictiveScoreRide();
    score = new WeightedAverageScore();
  }

  @Test
  public void testSetA() {
    final String path = "a_example.in";
    generalTest(path);
  }

  @Test
  public void testSetB() {
    final String path = "b_should_be_easy.in";
    generalTest(path);
  }

  @Test
  public void testSetC() {
    final String path = "c_no_hurry.in";
    generalTest(path);
  }

  @Test
  public void testSetD() {
    final String path = "d_metropolis.in";
    generalTest(path);
  }

  @Test
  public void testSetE() {
    final String path = "e_high_bonus.in";
    generalTest(path);
  }

  private void generalTest(final String path) {
    final UtilsFile frB = new UtilsFile(path);

    final CityStatus status = new CityStatus(score, frB.getBonusAmount(), frB.getRowAmount(), frB.getColumnAmount());
    final List<Driver> drivers = Lists.newArrayList();
    for (int i = 0; i < frB.getDriversAmount(); i++) {
      final Driver d = new Driver(i);
      drivers.add(d);
    }
    status.setDrivers(drivers);
    status.setMaxTime(frB.getStepsAmount());
    status.setRides(frB.getRides());
    final AssignDriverLogic logic = new AssignDriverLogic();
    final Map<Integer, List<Ride>> out = logic.assignAllRides(status);
    try {
      OutputWriter.writeToFile(path + ".out", out);
    } catch (final FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (final UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
