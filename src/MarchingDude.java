import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MarchingDude extends JPanel implements Runnable {
  protected BufferedImage image;
  protected ArrayList<BufferedImage> frames;
  protected int x;
  protected int y;

  public MarchingDude(String filePath, String[] filePaths, int x, int y) {
    super();
    frames = new ArrayList<BufferedImage>(filePaths.length);
    try {
      image = ImageIO.read(new File(filePath));
      for (int i = 0; i < filePaths.length; i++) {
        frames.add(ImageIO.read(new File(filePaths[i])));
      }
    } catch (IOException e) {
      System.err.println("Invalid file passed to MarchingDude constructor: " + filePath);
      System.exit(-1);
    }
    this.x = x;
    this.y = y;
  }

  @Override
  public void paintComponent(Graphics g) {
    try {
      super.paintComponent(g);
      g.drawImage(image, this.x, this.y, null);
    } catch (NullPointerException e) {
      System.err.println("Reference g passed to MarchingDude.paintComponent is a null pointer!");
      System.exit(-1);
    }
  }

  @Override
  public void run() {
    int frame = 0;
    while (true) {
      image = frames.get(frame++ % 2);
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        System.out.println("Thread interrupted");
        System.exit(-1);
      }
    }
  }
}
