import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Clock;

public class FlyWheel {

  class FootballField extends JFrame {

    class MarchingDude extends JPanel {
      private BufferedImage image;
      int x;
      int y;

      public MarchingDude(String filePath, int x, int y) {
        try {
          image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
          System.err.println("Invalid file passed to MarchingDude constructor: " + filePath);
          System.exit(-1);
        }
        this.x = x;
        this.y = y;
      }

      @Override
      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, this.x, this.y, null);
      }
    }

    class SmellyFreshman extends MarchingDude {
      private int ppiPoints;
      private int wynenPoints;

      public SmellyFreshman(String filePath, int x, int y) {
        super(filePath, x, y);
      }

      public void takeStep() {
          this.y += 5;
      }
    }

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

    private FootballField.SmellyFreshman smellyFreshman;

    public FootballField(int width, int height) {
      super();
      this.setSize(width, height);
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.setVisible(true);
    }

    public void tapOff() {
      smellyFreshman = this.new SmellyFreshman("../imgs/spongeBob.png", 0, 0);
      FootballField.FootballFieldKeyListener keyListener = this.new FootballFieldKeyListener();
      this.add(smellyFreshman);
      this.addKeyListener(keyListener);
      smellyFreshman.addKeyListener(keyListener);
      Graphics g = smellyFreshman.getGraphics();
      smellyFreshman.paintComponent(g);

      while (true) {
        this.validate();
        this.repaint();
      }
    }
  }

  public static void main(String args[]) {
    FlyWheel marchingMaroonAndWhite = new FlyWheel();
    FlyWheel.FootballField theLot = marchingMaroonAndWhite.new FootballField(1500, 1000);
    theLot.tapOff();
  }

}
