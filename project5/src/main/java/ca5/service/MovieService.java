package ca5.service;

import ca5.model.Movie;
import ca5.model.YearRange;

import java.util.Set;

public interface MovieService {

  public void addMovie(Movie movie);

  public Set<Movie> findMovies(YearRange range);

}
