import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SmellyFreshman extends MarchingDude implements Runnable {
  private int ppiPoints;
  private int wynenPoints;
  private long takenAtTime;
  private int stepsTaken;

  public SmellyFreshman(String[] filePaths, int x, int y) {
    super(filePaths, x, y);
    ppiPoints = 10000;
    wynenPoints = 0;
    takenAtTime = -1;
    stepsTaken = 0;
  }

  public void takeStep() {
    long temp = System.currentTimeMillis();
    if (takenAtTime != -1) {
      ppiPoints -= 5;
      if (ppiPoints <= 0) {
        System.out.println("GAME OVER");
        System.exit(0);
      }
    }
    takenAtTime = temp;
    graphicsTool.paint(graphicsToolID, stepsTaken++ % 2, this.x, this.y);
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
