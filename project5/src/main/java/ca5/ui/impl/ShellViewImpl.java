package ca5.ui.impl;

import ca5.model.Movie;
import ca5.model.YearRange;
import ca5.ui.ShellView;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class ShellViewImpl implements ShellView {
  private static final int WIDTH = 700;
  private static final int HEIGHT = 550;

  private JFrame topLevelWindow;
  private JTabbedPane movieTabs;
  private JSlider movieSlider;
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

      JPanel container = new JPanel();
      int borderSize = 10;
      container.setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
      topLevelWindow.add(container);

      container.setLayout(new BorderLayout(5, 5));

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


      JToolBar dateRangeToolBar = new JToolBar();
      dateRangeToolBar.setFloatable(false);
      List<YearRange> ranges = new ArrayList<YearRange>();
      ranges.add(new YearRange(1970, 1979));
      ranges.add(new YearRange(1980, 1989));
      ranges.add(new YearRange(1990, 1999));
      ranges.add(new YearRange(2000, 2009));
      ranges.add(new YearRange(2010, 2019));

      final ButtonGroup yearRangeGroup = new ButtonGroup();
      for (final YearRange range : ranges) {
        JRadioButton radio = new JRadioButton(range.getLabel());
        radio.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            onSelectedYearRange(range);
          }
        });
        dateRangeToolBar.add(radio);
        yearRangeGroup.add(radio);
      }
      yearRangeGroup.getElements().nextElement().setSelected(true);

      container.add(dateRangeToolBar, BorderLayout.NORTH);


      movieTabs = new JTabbedPane();
      movieTabs.setTabPlacement(JTabbedPane.RIGHT);
      container.add(movieTabs, BorderLayout.CENTER);


      movieSlider = new JSlider(JSlider.HORIZONTAL, 1, 1, 1);
      movieSlider.setPaintTicks(true);
      movieSlider.setPaintLabels(true);
      Hashtable standardLabels = movieSlider.createStandardLabels(1);
      movieSlider.setLabelTable(standardLabels);
      movieSlider.setSnapToTicks(true);
      container.add(movieSlider, BorderLayout.SOUTH);


      movieSlider.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
          if (movieTabs.getTabCount() < 1) {
            return;
          }
          movieTabs.setSelectedIndex(movieSlider.getValue() - 1);
        }
      });

      movieTabs.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
          movieSlider.setValue(movieTabs.getSelectedIndex() + 1);
        }
      });


    }

    topLevelWindow.setVisible(true);
  }

  @Override
  public void setMovies(Set<Movie> movies) {
    if (movieTabs == null) {
      return;
    }

    movieTabs.removeAll();
    movieSlider.setMinimum(1);
    movieSlider.setMaximum(movies.size());
    movieSlider.setVisible(movies.size() > 0);
    movieSlider.setValue(1);

    for (Movie movie : movies) {
      movieTabs.add(movie.getTitle(), new MovieTab(movie));
    }
  }

  private void onSelectedYearRange(final YearRange yearRange) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        delegate.onYearRangeSelected(yearRange);
      }
    });
  }
}
