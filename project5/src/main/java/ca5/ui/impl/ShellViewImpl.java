package ca5.ui.impl;

import ca5.ui.ShellView;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShellViewImpl implements ShellView {
  private static final int WIDTH = 700;
  private static final int HEIGHT = 550;

  private JFrame topLevelWindow;
  private Delegate delegate;

  @Override
  public void setDelegate(Delegate delegate) {
    this.delegate = delegate;
  }

  @Override
  public void show() {
    if (topLevelWindow == null) {
      topLevelWindow = new JFrame();
      topLevelWindow.setSize(WIDTH, HEIGHT);
      topLevelWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JMenuBar menuBar = new JMenuBar();
      JMenu editMenu = new JMenu("Edit");
      JMenuItem addMovie = new JMenuItem("Add New Movie...");
      addMovie.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          delegate.addNewMovie();
        }
      });
      editMenu.add(addMovie);
      menuBar.add(editMenu);

      JMenu slideshowMenu = new JMenu("Slideshow");
      JMenuItem startSlideShow = new JMenuItem("Start Slideshow");
      startSlideShow.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          delegate.startSlideShow();
        }
      });
      JMenuItem stopSlideShow = new JMenuItem("Stop Slideshow");
      stopSlideShow.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          delegate.stopSlideShow();
        }
      });
      slideshowMenu.add(startSlideShow);
      slideshowMenu.add(stopSlideShow);
      menuBar.add(slideshowMenu);

      topLevelWindow.setJMenuBar(menuBar);
    }

    topLevelWindow.setVisible(true);
  }
}
