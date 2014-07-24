package ca5;

import ca5.ioc.ApplicationFactory;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieDatabase {

  private static final Level ROOT_LOG_LEVEL = Level.ALL;

  public static void main(String[] args) {
    initLogging();
    initLAF();

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

  private static void initLAF() {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }
}
