package google.com.fgeneration.hashcode_2018.model;

public class Driver {
  int id;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + id;
    result = (prime * result) + ((lastPositon == null) ? 0 : lastPositon.hashCode());
    result = (prime * result) + nextAvailableTime;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Driver other = (Driver) obj;
    if (id != other.id) {
      return false;
    }
    if (lastPositon == null) {
      if (other.lastPositon != null) {
        return false;
      }
    } else if (!lastPositon.equals(other.lastPositon)) {
      return false;
    }
    if (nextAvailableTime != other.nextAvailableTime) {
      return false;
    }
    return true;
  }

  int nextAvailableTime;
  Intersection lastPositon;

  public Driver(int id) {
    nextAvailableTime = 0;
    lastPositon = new Intersection(0, 0);
    this.id = id;
  }

  public int getNextAvailableTime() {
    return nextAvailableTime;
  }

  public void setNextAvailableTime(int nextAvailableTime) {
    this.nextAvailableTime = nextAvailableTime;
  }

  public Intersection getLastPositon() {
    return lastPositon;
  }

  public void setLastPositon(Intersection lastPositon) {
    this.lastPositon = lastPositon;
  }

  @Override
  public String toString() {
    return "[Pos:" + this.lastPositon + ", nextTime:" + this.nextAvailableTime + "]";
  }

}
