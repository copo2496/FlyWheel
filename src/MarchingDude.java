import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MarchingDude implements Runnable {
  protected ArrayList<BufferedImage> frames;
  protected GraphicsTool graphicsTool;
  protected int graphicsToolID;
  protected int x;
  protected int y;

  public MarchingDude(String[] filePaths, int x, int y) {
    try {
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

  public void joinGraphics(GraphicsTool graphicsTool) {
    this.graphicsTool = graphicsTool;
    this.graphicsToolID = this.graphicsTool.joinGraphics(frames, x, y);
  }

  @Override
  public void run() {
    int frame = 0;
    while (true) {
      graphicsTool.paint(graphicsToolID, frame++ % 2, x, y);
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        System.exit(-1);
      }
    }
  }
}
