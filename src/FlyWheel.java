import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FlowLayout;
import java.awt.Color;


public class FlyWheel extends JFrame {
  private SmellyFreshman smellyFreshman;

  class FlyWheelKeyListener implements KeyListener {
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

  public FlyWheel(int height, int width) {
    super();
    this.setSize(height, width);
    this.setLayout(new FlowLayout());
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    FlyWheel marchingMaroonAndWhite = new FlyWheel(1500, 2000);
    marchingMaroonAndWhite.tapOff();
  }

  public void tapOff() {
    FlyWheel.FlyWheelKeyListener keyListener = this.new FlyWheelKeyListener();
    this.addKeyListener(keyListener);

    String smellyFreshmanFilePath = "../imgs/SnarePlayer_Static.png";
    String[] smellyFreshmanFilePaths = new String[2];
    smellyFreshmanFilePaths[0] = "../imgs/SnarePlayer01.png";
    smellyFreshmanFilePaths[1] = "../imgs/SnarePlayer02.png";
    smellyFreshman = new SmellyFreshman(smellyFreshmanFilePath, smellyFreshmanFilePaths, 100, 0);
    this.add(smellyFreshman);
    smellyFreshman.addKeyListener(keyListener);

    String lynchFilePath = "../imgs/MrLynch02.png";
    String[] lynchFilePaths = new String[2];
    lynchFilePaths[0] = "../imgs/MrLynch01.png";
    lynchFilePaths[1] = "../imgs/MrLynch02.png";
    MarchingDude lynch = new MarchingDude(lynchFilePath, lynchFilePaths, 0, 100);
    this.add(lynch);

    this.setVisible(true);

    Graphics smellyFreshmanGraphics = smellyFreshman.getGraphics();
    smellyFreshman.paintComponent(smellyFreshmanGraphics);
    Graphics lynchGraphics = lynch.getGraphics();
    lynch.paintComponent(lynchGraphics);

    this.validate();
    this.repaint();

    Thread t_smellyFreshman = new Thread(smellyFreshman);
    Thread t_lynch = new Thread(lynch);
    t_smellyFreshman.start();
    t_lynch.start();
  }



}
