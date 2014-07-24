package ca5.ui;

public interface ShellView extends View {
  public interface Delegate {
    void addNewMovie();

    void startSlideShow();

    void stopSlideShow();
  }

  void setDelegate(Delegate delegate);

  void show();
}
