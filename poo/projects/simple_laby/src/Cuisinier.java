import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cuisinier extends Individu {
    BufferedImage image;

    public Cuisinier(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        try {
            image = ImageIO.read(new File("cuisinier.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.objet.add(new Nourriture(2));
    }

    public void paint_individu(Graphics g) {
        g.drawImage(image, this.posX, this.posY, 100, 100, null);
    }
}