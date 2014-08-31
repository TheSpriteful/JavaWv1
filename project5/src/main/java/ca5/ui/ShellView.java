package ca5.ui;

import ca5.model.Movie;
import ca5.model.YearRange;

import java.util.Set;

public interface ShellView extends View {

  public interface Delegate {
    void addNewMovie();

    void startSlideShow();

    void stopSlideShow();

    void onYearRangeSelected(YearRange yearRange);
  }

  void setDelegate(Delegate delegate);

  void show();

  void setMovies(Set<Movie> movies);
}
