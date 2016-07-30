import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class MarchingDude extends JPanel {
  private BufferedImage image;
  protected int x;
  protected int y;

  public MarchingDude(String filePath, int x, int y) {
    super();
    try {
      image = ImageIO.read(new File(filePath));
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
}
