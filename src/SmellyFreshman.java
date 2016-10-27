import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;

public class SmellyFreshman extends MarchingDude implements Runnable {
  private int ppiPoints;
  private int wynenPoints;
  private long takenAtTime;
  private int stepsTaken;

  public SmellyFreshman(String[] filePaths, int x, int y) {
    super(filePaths, x, y);
    ppiPoints = 100;
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
    commTool.paint(memberID, stepsTaken++ % 2, this.x, this.y);
  }

  @Override
  public void run() {
    while (true) {
      if (takenAtTime == -1) {
        ppiPoints -= 5;
        if (ppiPoints <= 0) {
          System.out.println("GAME OVER");
          System.exit(0);
        } else if (ppiPoints <= 50) {
          this.commTool.sendMessage(2, new Integer(1));
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
      if (wynenPoints >= 50) {
        ppiPoints += 5;
      }
      if (ppiPoints <= 0) {
        System.out.println("GAME OVER");
        System.exit(0);
      } else if (ppiPoints <= 20) {
        this.commTool.sendMessage(2, new Integer(1));
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
