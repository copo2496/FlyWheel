public class SmellyFreshman extends MarchingDude implements Runnable {
  private int ppiPoints;
  private int wynenPoints;
  private long takenAtTime;

  public SmellyFreshman(String filePath, int x, int y) {
    super(filePath, x, y);
    ppiPoints = 100;
    wynenPoints = 0;
    takenAtTime = -1;
  }

  public void takeStep() {
    this.y += 5;
    if (takenAtTime != -1) {
      ppiPoints -= 5;
      if (ppiPoints <= 0) {
        System.out.println("GAME OVER");
        System.exit(0);
      }
    }
    takenAtTime = System.currentTimeMillis();
    repaint();
  }

  @Override
  public void run() {
    while (true) {
      if (takenAtTime == -1) {
        ppiPoints -= 5;
        if (ppiPoints <= 0) {
          System.out.println("GAME OVER");
          System.exit(0);
        }
      }
      else {
        if (System.currentTimeMillis() - takenAtTime >= 100) {
          ppiPoints -= 5;
          if (ppiPoints <= 0) {
            System.out.println("GAME OVER");
            System.exit(0);
          }
        }
        else {
          wynenPoints += 1;
        }
      }
      takenAtTime = -1;
      if (wynenPoints >= 20) {
        ppiPoints += 5;
      }
      if (ppiPoints <= 0) {
        System.out.println("GAME OVER");
        System.exit(0);
      }
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        System.err.println("Thread interrupted");
        System.exit(-1);
      }
    }
  }

}
