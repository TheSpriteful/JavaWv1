package ca5.ui.impl;

import ca5.model.Movie;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class MovieTab extends JTabbedPane {

  private final Movie movie;

  public MovieTab(Movie movie) {
    this.movie = movie;

    add("Poster", posterTab());
    add("Plot", plotTab());
    add("Details", detailsTab());
  }


  private Component posterTab() {
    ImageIcon imageIcon = new ImageIcon(movie.getPosterUrl());
    final Image posterImage = imageIcon.getImage();


    JPanel panel = new ScalingImagePanel(posterImage);

    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    return scrollPane;
  }

  private Component plotTab() {
    JLabel plot = new JLabel(movie.getPlot());
    plot.setHorizontalAlignment(LEFT);
    plot.setVerticalAlignment(TOP);

    return plot;
  }


  private Component detailsTab() {
    JPanel panel = new JPanel();
    return panel;
  }

  private static class ScalingImagePanel extends JPanel {
    private final Image posterImage;
    private int myWidth;
    private int myHeight;

    public ScalingImagePanel(Image posterImage) {
      this.posterImage = posterImage;
    }

    @Override
    public void paint(Graphics g) {
      myWidth = posterImage.getWidth(this);
      myHeight = posterImage.getHeight(this);
      if (myWidth >= getWidth()) {
        double scale = ((double) getWidth()) / (double) myWidth;
        myWidth = (int) (myWidth * scale);
        myHeight = (int) (myHeight * scale);
      }

      g.drawImage(posterImage, 0, 0, myWidth, myHeight, this);
    }

    @Override
    public Dimension getPreferredSize() {
      Dimension parentsize = super.getPreferredSize();
      return new Dimension(parentsize.width, myHeight);
    }
  }
}
