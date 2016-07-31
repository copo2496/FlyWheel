import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;


public class FlyWheel extends JFrame {

  public FlyWheel(int height, int width) {
    super();
    this.setSize(height, width);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {

      public void run() {
        JFrame flyWheel = new FlyWheel(1500, 2000);
        MarchingBand marchingMaroonAndWhite = new MarchingBand("../imgs/BGTestv02.png");
        flyWheel.add(marchingMaroonAndWhite);

        String smellyFreshmanFiles[] = new String[3];
        smellyFreshmanFiles[0] = "../imgs/SnarePlayer_Static.png";
        smellyFreshmanFiles[1] = "../imgs/SnarePlayer01.png";
        smellyFreshmanFiles[2] = "../imgs/SnarePlayer02.png";
        SmellyFreshman smellyFreshman = new SmellyFreshman(smellyFreshmanFiles, 450, -5);
        marchingMaroonAndWhite.add(smellyFreshman);

        String[] lynchFiles = new String[3];
        lynchFiles[0] = "../imgs/MrLynch02.png";
        lynchFiles[1] = "../imgs/MrLynch01.png";
        lynchFiles[2] = "../imgs/MrLynch02.png";
        MarchingDude lynch = new MarchingDude(lynchFiles, -5, 200);
        marchingMaroonAndWhite.add(lynch);

        class KeyInput implements KeyListener {
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
              smellyFreshman.takeStep();
            }
          }
          public void keyReleased(KeyEvent e) { return; }
          public void keyTyped(KeyEvent e) { return; }
        }
        KeyInput keys = new KeyInput();
        flyWheel.addKeyListener(keys);
        marchingMaroonAndWhite.addKeyListener(keys);

        flyWheel.setVisible(true);

        Thread t = new Thread(marchingMaroonAndWhite);
        t.start();
      }

    });
  }
}
