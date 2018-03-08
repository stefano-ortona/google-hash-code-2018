package google.com.fgeneration.hashcode_2018.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import google.com.fgeneration.hashcode_2018.logic.BestScoreRide;

public class CityStatus {
  private static final Logger LOGGER = LoggerFactory.getLogger(CityStatus.class);

  List<Driver> drivers;
  List<Ride> rides;
  int maxTime;
  BestScoreRide bestScore;
  int bonus;
  int row;
  int column;
  int[][] densityScores;

  public CityStatus(BestScoreRide bestScore, int bonus, int row, int column) {
    this.bestScore = bestScore;
    this.bonus = bonus;
    this.row = row;
    this.column = column;
    this.densityScores = new int[row][column];
  }

  public List<Driver> getAvailableDrivers(int time) {
    final List<Driver> drivers = Lists.newArrayList();
    for (final Driver d : this.drivers) {
      if (d.getNextAvailableTime() <= time) {
        drivers.add(d);
      }
    }
    return drivers;
  }

  public Ride assignOrderedRide(Driver driver) {
    if (this.rides.isEmpty()) {
      return null;
    }
    final Ride ride = this.rides.remove(0);
    return ride;
  }

  public Ride assignNextBestRide(Driver driver) {
    final Ride ride = this.bestScore.getBestRide(driver, this);
    if (ride == null) {
      return null;
    }
    this.rides.remove(ride);
    return ride;
  }

  public List<Driver> getDrivers() {
    return drivers;
  }

  public void setDrivers(List<Driver> drivers) {
    this.drivers = drivers;
  }

  public List<Ride> getRides() {
    return rides;
  }

  public void setRides(List<Ride> rides) {
    this.rides = rides;
  }

  public int getMaxTime() {
    return maxTime;
  }

  public void setMaxTime(int maxTime) {
    this.maxTime = maxTime;
  }

  public BestScoreRide getBestScore() {
    return bestScore;
  }

  public void setBestScore(BestScoreRide bestScore) {
    this.bestScore = bestScore;
  }

  public int getBonus() {
    return bonus;
  }

  public void setBonus(int bonus) {
    this.bonus = bonus;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

}
