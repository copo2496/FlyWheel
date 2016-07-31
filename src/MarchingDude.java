import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MarchingDude implements Runnable {
  protected MarchingBand myBand;
  protected BufferedImage currentFrame;
  protected ArrayList<BufferedImage> frames;
  protected int x;
  protected int y;

  public MarchingDude(String[] filePaths, int x, int y) {
    try {
      currentFrame = ImageIO.read(new File(filePaths[0]));
      frames = new ArrayList<BufferedImage>(filePaths.length - 1);
      for (int i = 1; i < filePaths.length; i++) {
        frames.add(ImageIO.read(new File(filePaths[i])));
      }
    } catch (IOException e) {
      System.exit(-1);
    }
    this.x = x;
    this.y = y;
  }

  public void setMyBand(MarchingBand myBand) {
    this.myBand = myBand;
  }

  public void paint(Graphics g) throws NullPointerException {
    g.drawImage(currentFrame, this.x, this.y, null);
  }

  @Override
  public void run() {
    int frame = 0;
    while (true) {
      currentFrame = frames.get(frame++ % 2);
      myBand.repaint();
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        System.exit(-1);
      }
    }
  }
}
