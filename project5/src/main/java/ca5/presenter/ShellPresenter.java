package ca5.presenter;


import ca5.event.AddMovieEvent;
import ca5.event.StartSlideShowEvent;
import ca5.event.StopSlideShowEvent;
import ca5.event.core.EventBus;
import ca5.ui.ShellView;
import ca5.ui.ShellView.Delegate;

public class ShellPresenter implements Delegate {
  private final EventBus eventBus;
  private final ShellView view;

  public ShellPresenter(EventBus eventBus, ShellView view) {
    this.eventBus = eventBus;
    this.view = view;
  }

  public void show() {
    view.show();
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
}
