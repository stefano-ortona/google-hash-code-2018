package google.com.fgeneration.hashcode_2018.model;

import java.util.Objects;

public class Intersection {

  int row;

  public Intersection(int row, int column) {
    this.row = row;
    this.column = column;
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

  int column;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Intersection that = (Intersection) o;
    return row == that.row &&
            column == that.column;
  }

  @Override
  public int hashCode() {

    return Objects.hash(row, column);
  }

  @Override
  public String toString() {
    return "Intersection[" + this.row + "," + this.column + "]";
  }


}
