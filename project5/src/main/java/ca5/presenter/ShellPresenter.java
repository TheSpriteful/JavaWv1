package ca5.presenter;


import ca5.event.AddMovieEvent;
import ca5.event.StartSlideShowEvent;
import ca5.event.StopSlideShowEvent;
import ca5.event.core.EventBus;
import ca5.model.Movie;
import ca5.model.YearRange;
import ca5.service.MovieService;
import ca5.ui.ShellView;
import ca5.ui.ShellView.Delegate;

import javax.swing.SwingUtilities;
import java.util.Set;

public class ShellPresenter implements Delegate {
  private final EventBus eventBus;
  private final MovieService service;
  private final ShellView view;

  public ShellPresenter(EventBus eventBus, MovieService service, ShellView view) {
    this.eventBus = eventBus;
    this.service = service;
    this.view = view;
  }

  public void show() {
    final Set<Movie> movies = service.findMovies(new YearRange(1970, 1979));
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        view.show();
        view.setMovies(movies);
      }
    });

  }

  @Override
  public void addNewMovie() {
    eventBus.fire(new AddMovieEvent());
  }

  @Override
  public void startSlideShow() {
    eventBus.fire(new StartSlideShowEvent());
  }

  @Override
  public void stopSlideShow() {
    eventBus.fire(new StopSlideShowEvent());
  }

  @Override
  public void onYearRangeSelected(YearRange yearRange) {
    view.setMovies(service.findMovies(yearRange));
  }
}
