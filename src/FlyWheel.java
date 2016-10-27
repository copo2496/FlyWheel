import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.sql.*;

public class FlyWheel {
  static final String DRIVER = "com.mysql.jdbc.Driver";
  static final String URL = "jdbc:mysql://localhost/flywheel?useSSL=false";
  static final String USER = "root";
  static final String PASS = "foshizleA5";
  static Connection CONN;

  public static void __init_db__() {
    Connection conn = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(URL,USER,PASS);
    } catch (SQLException se) {
      se.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static MarchingBand __init_game__() {
    JFrame flyWheel = new JFrame();
    flyWheel.setSize(1500,2500);
    flyWheel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    MarchingBand marchingMaroonAndWhite = new MarchingBand("../imgs/BGTestv02.png");
    flyWheel.add(marchingMaroonAndWhite);

    String smellyFreshmanFiles[] = new String[3];
    smellyFreshmanFiles[0] = "../imgs/SnarePlayer_Static.png";
    smellyFreshmanFiles[1] = "../imgs/SnarePlayer01.png";
    smellyFreshmanFiles[2] = "../imgs/SnarePlayer02.png";
    SmellyFreshman smellyFreshman = new SmellyFreshman(smellyFreshmanFiles, 500, -5);
    marchingMaroonAndWhite.add(smellyFreshman);

    String[] lynchFiles = new String[5];
    lynchFiles[0] = "../imgs/MrLynch02.png";
    lynchFiles[1] = "../imgs/MrLynch01.png";
    lynchFiles[2] = "../imgs/MrLynch02.png";
    lynchFiles[3] = "../imgs/AngryLynch01.png";
    lynchFiles[4] = "../imgs/AngryLynch02.png";
    MarchingDude lynch = new MarchingDude(lynchFiles, -5, 50);
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

    Graphics g = marchingMaroonAndWhite.getGraphics();
    CommTool commTool = new CommTool(g);
    marchingMaroonAndWhite.join(commTool);
    smellyFreshman.join(commTool);
    lynch.join(commTool);

    return marchingMaroonAndWhite;
  }

  public static void main(String[] args) {
    __init_db__();
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        MarchingBand band = __init_game__();
        Thread t = new Thread(band);
        t.start();
      }
    });
  }
}
