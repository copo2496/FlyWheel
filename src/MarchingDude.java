import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;

public class MarchingDude implements Runnable {
  protected ArrayList<BufferedImage> frames;
  protected CommTool commTool;
  protected int memberID;
  protected int x;
  protected int y;
  protected BlockingQueue<Integer> messages;

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
    messages = new LinkedBlockingQueue();
  }

  public void join(CommTool commTool) {
    this.commTool = commTool;
    this.memberID = this.commTool.join(frames, x, y, messages);
  }

  @Override
  public void run() {
    int frame = 0;
    while (true) {
      Integer msg;
      while ((msg = messages.poll()) != null) {
        if (msg == 1) {
          frames.set(0, frames.get(2));
          frames.set(1, frames.get(3));
        }
      }
      commTool.paint(memberID, frame++ % 2, x, y);
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
