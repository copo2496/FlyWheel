import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MarchingBand extends JPanel implements Runnable {
  private BufferedImage image;
  private ArrayList<MarchingDude> marchingDudes;
  private JLabel score;
  private int x;
  private int y;

  public MarchingBand(String filePath) {
    super();
    marchingDudes = new ArrayList<MarchingDude>();
    score = new JLabel();
    try {
      image = ImageIO.read(new File(filePath));
    } catch (IOException e) {
      System.out.println("Invalid file passed to FootballField constructor");
      System.exit(-1);
    }
  }

  public void add(MarchingDude marchingDude) {
    marchingDudes.add(marchingDude);
    marchingDude.setMyBand(this);
  }

  @Override
  public void paintComponent(Graphics g) {
    try {
      super.paintComponent(g);
      g.drawImage(image, this.x, this.y, null);
      for (MarchingDude m : marchingDudes) {
        m.paint(g);
      }
    } catch (NullPointerException e) {
      System.err.println(e);
      System.exit(-1);
    }
  }

  @Override
  public void run() {
    ArrayList<Thread> threads = new ArrayList<Thread>(marchingDudes.size());
    for (MarchingDude m : marchingDudes) {
      Thread t = new Thread(m);
      threads.add(t);
      t.start();
    }
  }

}
