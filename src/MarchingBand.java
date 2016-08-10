import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MarchingBand extends JPanel implements Runnable {
  private GraphicsTool graphicsTool;
  private int graphicsToolID;
  private BufferedImage image;
  private ArrayList<MarchingDude> marchingDudes;

  public MarchingBand(String filePath) {
    super();
    marchingDudes = new ArrayList<MarchingDude>();
    try {
      image = ImageIO.read(new File(filePath));
    } catch (IOException e) {
      System.out.println("Invalid file passed to FootballField constructor");
      System.exit(-1);
    }
  }

  public void add(MarchingDude marchingDude) {
    marchingDudes.add(marchingDude);
  }

  public void joinGraphics(GraphicsTool graphicsTool) {
    this.graphicsTool = graphicsTool;
    ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
    images.add(image);
    this.graphicsToolID = this.graphicsTool.joinGraphics(images, 0, 0);
  }

  @Override
  public void paintComponent(Graphics g) {
    try {
      super.paintComponent(g);
      graphicsTool.paint(graphicsToolID, 0, 0, 0);
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
