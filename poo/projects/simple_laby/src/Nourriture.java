import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Nourriture extends Objet {
    BufferedImage image;

    public Nourriture(int upDmg) {
        this.upDmg = upDmg;
        try {
            image = ImageIO.read(new File("nourriture.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Nourriture(int upDmg, int posX, int posY) {
        this.upDmg = upDmg;
        this.posX = posX;
        this.posY = posY;
        try {
            image = ImageIO.read(new File("nourriture.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint_objet(Graphics g) {
        g.drawImage(image, this.posX, this.posY, 35, 35, null);
        if (Frame.ml.x > this.posX && Frame.ml.x < this.posX + 35 && Frame.ml.y > this.posY
                && Frame.ml.y < this.posY + 35) {
            g.setColor(Color.lightGray);
            g.drawRect(posX, posY, 35, 35);
        }
    }
}