import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.awt.Graphics;

/**
TODO:
the communication mechanism is a mess, make this more organiezed
**/

public class CommTool {
  private Graphics g;
  private ArrayList<ArrayList<BufferedImage>> images;
  private ArrayList<Integer> frameCount;
  private ArrayList<Integer> xs;
  private ArrayList<Integer> ys;
  private int memberCount;
  private ArrayList<BlockingQueue<Integer>> messages;

  public CommTool(Graphics g) {
    this.g = g;
    images = new ArrayList<ArrayList<BufferedImage>>();
    frameCount = new ArrayList<Integer>();
    xs = new ArrayList<Integer>();
    ys = new ArrayList<Integer>();
    messages = new ArrayList<BlockingQueue<Integer>>();
    memberCount = 0;
  }

  public void paint(int member, int frame, int x, int y) {
    frameCount.set(member, frame);
    xs.set(member, x);
    ys.set(member, y);
    for (int i = 0; i < memberCount; i++) {
      BufferedImage image = images.get(i).get(frameCount.get(i));
      int xpos = xs.get(i);
      int ypos = ys.get(i);
      g.drawImage(image, xpos, ypos, null);
    }
  }

  public void sendMessage(int member, int message) {
    messages.get(member).add(message);
  }

  public int join(ArrayList<BufferedImage> images, int x, int y, BlockingQueue<Integer> messageQueue) {
    this.images.add(images);
    this.frameCount.add(0);
    this.xs.add(x);
    this.ys.add(y);
    messages.add(messageQueue);
    this.memberCount++;
    return memberCount - 1;
  }
}
