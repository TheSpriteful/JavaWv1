package ca5;

import ca5.event.AddMovieEvent;
import ca5.event.core.EventBus;
import ca5.event.core.Subscribe;
import ca5.presenter.ShellPresenter;

import java.util.logging.Logger;

public class ApplicationController {
  private static final Logger logger = Logger.getLogger(ApplicationController.class.getName());

  private final ShellPresenter shellPresenter;


  public ApplicationController(EventBus eventBus, ShellPresenter shellPresenter) {
    this.shellPresenter = shellPresenter;
    eventBus.register(this);
  }

  public void start() {
    shellPresenter.show();
  }

  @Subscribe
  public void onAddMovie(AddMovieEvent event) {
    logger.severe("AddMovieEvent fired");
  }
}
