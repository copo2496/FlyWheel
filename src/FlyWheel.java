import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.KeyEvent;

public class FlyWheel {

  class MarchingDude {
    private int PPI;
    private int wynenPoints;
    private KeyEvent step;

    public MarchingDude() {
      this.PPI = 100;
      this.wynenPoints = 0;
      this.step = new KeyEvent()
    }

    public long takeStep(Clock dermlern) {
      return dermlern.millis();
    }
  }

  private JFrame footballField;
  private MarchingDude smellyFreshman;

  public static void main(String args[]) {

    // TODO Set up the football field
    footballField = new JFrame("footballField");

    // TODO Set up the smelly freshman
    smellyFreshman = new MarchingDude();

    // TODO tap off... begin the show...
    while (true) {

    }

  }

}
