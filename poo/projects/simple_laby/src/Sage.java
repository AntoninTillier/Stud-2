import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sage extends Monstre {
    BufferedImage image;
    boolean message = false;

    public Sage(int posX, int posY) {
        super(posX, posY);
        try {
            image = ImageIO.read(new File("sage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deplace_Monstre() {
        double a = Frame.j.posX - this.posX;
        double b = Frame.j.posY - this.posY;
        double l = Math.sqrt(a * a + b * b);
        a = a / l;
        b = b / l;
        if (l > 200)
            message = false;
        if (l > 150) {
            this.collisionMur();
            this.posX += a * 1.5;
            this.posY += b * 1.5;
        } else {
            message = true;
            this.collisionMur();
            this.posX -= a * 1.5;
            this.posY -= b * 1.5;
        }
    }

    public void paint_monstre(Graphics g) {
        g.drawImage(image, this.posX, this.posY, 100, 100, null);
        if (message) {
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Il faut savoir manier son tire", this.posX + 10, this.posY + 135);
        }
    }

}