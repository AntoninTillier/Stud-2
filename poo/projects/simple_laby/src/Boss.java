import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Boss extends Monstre {
    BufferedImage image;

    public Boss(int posX, int posY) {
        super(posX, posY);
        this.pdv = 700;
        try {
            image = ImageIO.read(new File("boss.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deplace_Monstre() {
        attaque();
        double a = Frame.j.posX - this.posX;
        double b = Frame.j.posY - this.posY;
        double l = Math.sqrt(a * a + b * b);
        a = a / l;
        b = b / l;
        if (l > 90) {
            this.collisionMur();
            this.posX += a * 2;
            this.posY += b * 2;
        } else {
            this.collisionMur();
            this.posX -= a * 2;
            this.posY -= b * 2;
        }
    }

    public void attaque() {
        double a = Frame.j.posX - this.posX;
        double b = Frame.j.posY - this.posY;
        double l = Math.sqrt(a * a + b * b);
        a = a / l;
        b = b / l;
        if (l >= 0 && l <= 40)
            Frame.j.pdv -= 6;
    }

    public void paint_monstre(Graphics g) {
        if (this.pdv <= 20) {
            Frame.j.boss = true;
        }
        if (!Frame.kl.pause)
            Frame.balle.animation();
        Frame.balle.paint_balle(g);
        g.setColor(new Color(255, 148, 0));
        g.drawImage(image, this.posX, this.posY, 144, 200, null);
        g.fill3DRect(200, 655, pdv, 40, true);
    }
}