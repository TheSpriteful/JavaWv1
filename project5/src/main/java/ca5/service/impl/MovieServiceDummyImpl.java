package ca5.service.impl;


import ca5.model.Movie;
import ca5.model.YearRange;
import ca5.service.MovieService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MovieServiceDummyImpl implements MovieService {

  private static long movieIndex = 0;
  private List<Movie> memoryDB = new ArrayList<Movie>();

  public MovieServiceDummyImpl() {
    // populate with movies
    addMovie(new Movie("The Godfather II", 1974, "Bunch of random text", "Michael Corleone", 175, "http://4.bp.blogspot.com/-8LAjUzvgSBY/UnqkUpdUFsI/AAAAAAAAZqw/mu7jKw7WczY/s1600/The_Godfather_Part_2-Al-Pacino-Poster.jpg"));
    addMovie(new Movie("Jaws", 1975, "When a gigantic great white shark begins to menace the small island community of Amity, a police chief, a marine scientist and grizzled fisherman set out to stop it.", "Roy Scheider", 124, "http://ia.media-imdb.com/images/M/MV5BNDcxODkyMjY4MF5BMl5BanBnXkFtZTgwOTk5NTc5MDE@._V1_SX640_SY720_.jpg"));

    addMovie(new Movie("Pulp Fiction", 1994, "Text", "Samuel L. Jackson", 168, "http://fc05.deviantart.net/fs71/f/2012/280/c/0/c082dae80a525a88e755fd7288f7ebd1-d5h24sj.jpg"));
    addMovie(new Movie("The Chronicles of Riddick", 2004, "Text", "Vin Diesel", 135, "http://image.tmdb.org/t/p/original/tGNj5xxw5td0SBih1UmuqkHZ8ga.jpg"));
    addMovie(new Movie("Transformers: Age of Extinction", 2014, "text", "Mark Wahlberg", 165, "http://nukethefridge.com/wp-content/uploads/2014/06/transformers__age_of_extinction__2014____poster_by_camw1n-d7i1moa.jpg"));
  }

  @Override
  public void addMovie(Movie movie) {
    movie.setId(movieIndex++);
    memoryDB.add(movie);
  }

  @Override
  public Set<Movie> findMovies(YearRange range) {

    Set<Movie> matchingMovies = new LinkedHashSet<Movie>();

    for (Movie movie : memoryDB) {
      if (range.contains(movie.getYear())) {
        matchingMovies.add(movie);
      }
    }

    return Collections.unmodifiableSet(matchingMovies);
  }
}
