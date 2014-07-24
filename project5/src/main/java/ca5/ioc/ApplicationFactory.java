package ca5.ioc;

import ca5.ApplicationController;
import ca5.event.core.EventBus;
import ca5.presenter.ShellPresenter;
import ca5.service.MovieService;
import ca5.service.impl.MovieServiceDummyImpl;
import ca5.ui.ShellView;
import ca5.ui.impl.ShellViewImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ApplicationFactory {
  private static final Logger logger = Logger.getLogger(ApplicationFactory.class.getName());

  private static final int EXECUTOR_THREADS = 5;
  private static final long TIMEOUT = 5;
  private static final TimeUnit TIMEUNIT = TimeUnit.MILLISECONDS;
  private ApplicationController applicationController;
  private EventBus eventBus;
  private ExecutorService executorService;
  private ShellPresenter shellPresenter;

  public ApplicationFactory() {
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        logger.info("Shutting down ExecutorService.");
        try {
          getExecutorService().awaitTermination(TIMEOUT, TIMEUNIT);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });
  }


  public ApplicationController getApplicationController() {
    if (applicationController == null) {
      applicationController = new ApplicationController(getEventBus(), getShellPresenter());
    }
    return applicationController;
  }


  public EventBus getEventBus() {
    if (eventBus == null) {
      eventBus = new EventBus(getExecutorService());
    }
    return eventBus;
  }


  private ShellView getShellView() {
    return new ShellViewImpl();
  }

  private ExecutorService getExecutorService() {
    if (executorService == null) {
      executorService = Executors.newFixedThreadPool(EXECUTOR_THREADS);
    }
    return executorService;
  }

  private MovieService getMovieService() {
    return new MovieServiceDummyImpl();
  }

  private ShellPresenter getShellPresenter() {
    if (shellPresenter == null) {
      ShellView shellView = getShellView();
      shellPresenter = new ShellPresenter(getEventBus(), getMovieService(), shellView);
      shellView.setDelegate(shellPresenter);
    }
    return shellPresenter;
  }
}
