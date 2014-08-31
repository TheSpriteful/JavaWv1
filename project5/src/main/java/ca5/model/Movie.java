package ca5.model;


import java.net.URI;
import java.net.URL;

public class Movie {
  private long id;
  private String title;
  private int year;
  private String plot;
  private String actors;
  private int runningTime;
  private URL posterUrl;

  public Movie() {
  }

  public Movie(String title, int year, String plot, String actors, int runningTime, String posterUrlString) {
    this(title, year, plot, actors, runningTime, convertToUrl(posterUrlString));
  }

  public Movie(String title, int year, String plot, String actors, int runningTime, URL posterUrl) {
    this(0, title, year, plot, actors, runningTime, posterUrl);
  }

  public Movie(long id, String title, int year, String plot, String actors, int runningTime, URL posterUrl) {
    this.id = id;
    this.title = title;
    this.year = year;
    this.plot = plot;
    this.actors = actors;
    this.runningTime = runningTime;
    this.posterUrl = posterUrl;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getPlot() {
    return plot;
  }

  public void setPlot(String plot) {
    this.plot = plot;
  }

  public String getActors() {
    return actors;
  }

  public void setActors(String actors) {
    this.actors = actors;
  }

  public int getRunningTime() {
    return runningTime;
  }

  public void setRunningTime(int runningTime) {
    this.runningTime = runningTime;
  }

  public URL getPosterUrl() {
    return posterUrl;
  }

  public void setPosterUrl(URL posterUrl) {
    this.posterUrl = posterUrl;
  }

  private static URL convertToUrl(String urlString) {
    URL url;
    try {
      url = new URI(urlString).toURL();
    } catch (Exception e) {
      throw new IllegalArgumentException("Unable to parse URL String", e);
    }
    return url;
  }
}
