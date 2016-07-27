import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.KeyEvent;


public class FlyWheel {

  class FootballField extends JFrame {

    class MarchingDude implements Runnable {
      private int PPI;
      private int wynenPoints;
      private KeyEvent step;

      public MarchingDude() {
        this.PPI = 100;
        this.wynenPoints = 0;
        this.step = new KeyEvent();
      }

      public long takeStep(Clock dermlern) {
        return dermlern.millis();
      }

      public void run() {
        while (PPI > 0) {

        }
      }
    }

    private FootballField.MarchingDude smellyFreshman;

    public FootballField(int width, int height) {
      super();
      this.setSize(width, height);
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.setVisible(true);
    }
  }

  public static void main(String args[]) {
    FlyWheel marchingMaroonAndWhite = new FlyWheel();
    FlyWheel.FootballField theLot = marchingMaroonAndWhite.new FootballField(600, 400);
  }

}
