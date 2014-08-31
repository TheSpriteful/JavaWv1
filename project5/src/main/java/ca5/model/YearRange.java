package ca5.model;

public class YearRange {
  private int startYear;
  private int endYear;

  public YearRange(int startYear, int endYear) {
    this.startYear = startYear;
    this.endYear = endYear;
  }

  public int getStartYear() {
    return startYear;
  }

  public int getEndYear() {
    return endYear;
  }

  public boolean contains(int year) {
    return year >= startYear && year <= endYear;
  }

  public String getLabel() {
    return startYear + "-" + endYear;
  }
}
