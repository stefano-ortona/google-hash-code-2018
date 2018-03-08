package google.com.fgeneration.hashcode_2018;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AtomicDouble;

import google.com.fgeneration.hashcode_2018.logic.AssignDriverLogic;
import google.com.fgeneration.hashcode_2018.logic.BestScoreRide;
import google.com.fgeneration.hashcode_2018.logic.WeightedAverageScore;
import google.com.fgeneration.hashcode_2018.model.CityStatus;
import google.com.fgeneration.hashcode_2018.model.Driver;
import google.com.fgeneration.hashcode_2018.model.Ride;
import google.com.fgeneration.hashcode_2018.model.utils.OutputWriter;

public class MainFilesTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(AssignDriverLogic.class);

  private static BestScoreRide score;

  @BeforeClass
  public static void bringUp() {
    // score = new PredictiveScoreRide();
    score = new WeightedAverageScore(1., 1.);
  }

  @Test
  public void testSetA() {
    final String path = "a_example.in";
    generalTest(path, null);
  }

  @Test
  public void testSetB() {
    final String path = "b_should_be_easy.in";
    generalTest(path, null);
  }

  @Test
  public void testSetC() {
    final String path = "c_no_hurry.in";
    generalTest(path, null);
  }

  @Test
  public void testSetD() {
    final String path = "d_metropolis.in";
    generalTest(path, null);
  }

  @Test
  public void testSetE() {
    final String path = "e_high_bonus.in";
    generalTest(path, null);
  }

  @Test
  public void testSetBWithWeights() {
    final String path = "b_should_be_easy.in";
    generalTest(path, Pair.of(0., 1.));
  }

  @Test
  public void testSetDWithWeights() {
    final String path = "d_metropolis.in";
    generalTest(path, Pair.of(0.5, 0.5));
  }

  @Test
  public void testSetEWithWeights() {
    final String path = "e_high_bonus.in";
    generalTest(path, Pair.of(0.3, 0.7));
  }

  @Test
  public void testSetCWithWeights() {
    final String path = "c_no_hurry.in";
    generalTest(path, Pair.of(0.1, 0.9));
  }

  private void generalTest(final String path, Pair<Double, Double> weights) {
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

    double bestScore = -1;
    Map<Integer, List<Ride>> bestOut = null;
    if (weights == null) {
      for (double i = 0; i <= 1; i += 0.1) {
        LOGGER.info("Iteration '[{},{}]', has to reach '[1,0]'.", i, 1 - i);
        final BestScoreRide score = new WeightedAverageScore(i, 1 - i);
        final CityStatus curStatus = getNewStatus(status, score);
        final AtomicDouble curScore = new AtomicDouble(0);
        final Map<Integer, List<Ride>> out = logic.assignAllRides(curStatus, curScore);
        if (curScore.get() > bestScore) {
          bestScore = curScore.get();
          bestOut = out;
          LOGGER.info("Best score updated to '{}'.", bestScore);
        }

      }
    } else {
      final BestScoreRide score = new WeightedAverageScore(weights.getLeft(), weights.getRight());
      status.setBestScore(score);
      final AtomicDouble curScore = new AtomicDouble(0);
      final Map<Integer, List<Ride>> out = logic.assignAllRides(status, curScore);
      bestScore = curScore.get();
      bestOut = out;
      LOGGER.info("Best score updated to '{}'.", bestScore);
    }
    LOGGER.info("Best possible score among all iterations: '{}'", bestScore);
    try {
      OutputWriter.writeToFile(path + ".out", bestOut);
    } catch (final FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (final UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  private CityStatus getNewStatus(CityStatus status, final BestScoreRide score) {
    final CityStatus newStatus = new CityStatus(score, status.getBonus(), status.getRow(), status.getColumn());
    final List<Driver> drivers = Lists.newArrayList();
    for (final Driver d : status.getDrivers()) {
      final Driver newD = new Driver(d.getId());
      drivers.add(newD);
    }
    newStatus.setDrivers(drivers);
    newStatus.setMaxTime(status.getMaxTime());
    final List<Ride> rides = Lists.newArrayList(status.getRides());
    newStatus.setRides(rides);
    return newStatus;
  }

}
