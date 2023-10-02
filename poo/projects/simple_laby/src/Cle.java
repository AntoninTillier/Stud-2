import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cle extends Objet {
    BufferedImage image;

    public Cle(int num, int posX, int posY) {
        this.num = num;
        try {
            if (num == 1)
                image = ImageIO.read(new File("cle1.png"));
            if (num == 2)
                image = ImageIO.read(new File("cle2.png"));
            if (num == 3)
                image = ImageIO.read(new File("cle3.png"));
            if (num == 4)
                image = ImageIO.read(new File("cle4.png"));
            if (num == 10)
                image = ImageIO.read(new File("cleB.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.posX = posX;
        this.posY = posY;
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