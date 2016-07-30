import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FootballField extends JPanel {
  private BufferedImage image;
  private int x;
  private int y;

  public FootballField(String filePath) {
    super();
    try {
      image = ImageIO.read(new File(filePath));
    } catch (IOException e) {
      System.out.println("Invalid file passed to FootballField constructor");
      System.exit(-1);
    }
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
