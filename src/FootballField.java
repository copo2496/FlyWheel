import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Clock;

public class FootballField extends JFrame {
  private SmellyFreshman smellyFreshman;
  private Clock dermlern;

  class FootballFieldKeyListener implements KeyListener {
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        smellyFreshman.takeStep();
      }
    }

    public void keyReleased(KeyEvent e) {
      return;
    }

    public void keyTyped(KeyEvent e) {
      return;
    }
  }

  public FootballField(int width, int height) {
    super();
    super.setSize(width, height);
    super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  public void tapOff() {
    smellyFreshman = new SmellyFreshman("../imgs/spongeBob.png", 0, 0);
    FootballField.FootballFieldKeyListener keyListener = this.new FootballFieldKeyListener();
    this.add(smellyFreshman);
    this.addKeyListener(keyListener);
    smellyFreshman.addKeyListener(keyListener);
    this.setVisible(true);

    Graphics g = smellyFreshman.getGraphics();
    smellyFreshman.paintComponent(g);

    Thread t = new Thread(smellyFreshman);
    t.start();
  }
}
