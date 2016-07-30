public class SmellyFreshman extends MarchingDude implements Runnable {
  private int ppiPoints;
  private int wynenPoints;

  public SmellyFreshman(String filePath, int x, int y) {
    super(filePath, x, y);
    ppiPoints = 100;
  }

  public void takeStep() {
    this.y += 5;
    repaint();
  }

  @Override
  public void run() {
    while (true) {

    }
  }
}
