package ca5;

import ca5.ioc.ApplicationFactory;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieDatabase {

  private static final Level ROOT_LOG_LEVEL = Level.ALL;

  public static void main(String[] args) {
    initLogging();

    ApplicationController applicationController = new ApplicationFactory().getApplicationController();
    applicationController.start();
  }

  private static void initLogging() {
    Logger rootLogger = Logger.getLogger("");
    Handler consoleHandler = new ConsoleHandler();
    rootLogger.setUseParentHandlers(false);
    rootLogger.setLevel(ROOT_LOG_LEVEL);
    consoleHandler.setLevel(rootLogger.getLevel());

    rootLogger.fine("Logger initialized");
  }
}
