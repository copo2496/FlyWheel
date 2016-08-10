import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics;

public class GraphicsTool {
  private Graphics g;
  private ArrayList<ArrayList<BufferedImage>> images;
  private ArrayList<Integer> frameCount;
  private ArrayList<Integer> xs;
  private ArrayList<Integer> ys;
  private int memberCount;

  public GraphicsTool(Graphics g) {
    this.g = g;
    images = new ArrayList<ArrayList<BufferedImage>>();
    frameCount = new ArrayList<Integer>();
    xs = new ArrayList<Integer>();
    ys = new ArrayList<Integer>();
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

  public int joinGraphics(ArrayList<BufferedImage> images, int x, int y) {
    this.images.add(images);
    this.frameCount.add(0);
    this.xs.add(x);
    this.ys.add(y);
    this.memberCount++;
    return memberCount - 1;
  }
}
