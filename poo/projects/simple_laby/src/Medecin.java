import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Medecin extends Individu {
    BufferedImage image;

    public Medecin(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        try {
            image = ImageIO.read(new File("medecin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.objet.add(new Medicament(25));
    }

    public void paint_individu(Graphics g) {
        g.drawImage(image, this.posX, this.posY, 100, 100, null);
    }

}