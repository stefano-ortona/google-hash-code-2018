package google.com.fgeneration.hashcode_2018.model;

public class Ride {

  int id;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((end == null) ? 0 : end.hashCode());
    result = (prime * result) + id;
    result = (prime * result) + maxEndTime;
    result = (prime * result) + minStartTime;
    result = (prime * result) + ((start == null) ? 0 : start.hashCode());
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
    final Ride other = (Ride) obj;
    if (end == null) {
      if (other.end != null) {
        return false;
      }
    } else if (!end.equals(other.end)) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    if (maxEndTime != other.maxEndTime) {
      return false;
    }
    if (minStartTime != other.minStartTime) {
      return false;
    }
    if (start == null) {
      if (other.start != null) {
        return false;
      }
    } else if (!start.equals(other.start)) {
      return false;
    }
    return true;
  }

  public Ride(int id, Intersection start, Intersection end, int minStartTime, int maxEndTime) {
    this.start = start;
    this.end = end;
    this.minStartTime = minStartTime;
    this.maxEndTime = maxEndTime;
    this.id = id;
  }

  Intersection start;

  public Intersection getStart() {
    return start;
  }

  public void setStart(Intersection start) {
    this.start = start;
  }

  public Intersection getEnd() {
    return end;
  }

  public void setEnd(Intersection end) {
    this.end = end;
  }

  public int getMinStartTime() {
    return minStartTime;
  }

  public void setMinStartTime(int minStartTime) {
    this.minStartTime = minStartTime;
  }

  public int getMaxEndTime() {
    return maxEndTime;
  }

  public void setMaxEndTime(int maxEndTime) {
    this.maxEndTime = maxEndTime;
  }
  
  public int getId() {
	  return this.id;
  }

  Intersection end;
  int minStartTime;
  int maxEndTime;

  @Override
  public String toString() {
    return this.id +"_[" + this.getStart().toString() + "," + this.getEnd().toString()  + "," + this.minStartTime + "," + this.maxEndTime + "]";
  }


}
