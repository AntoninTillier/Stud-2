import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Porte {
    boolean fermer;
    char direction;
    int num;
    int posX;
    int posY;

    public Porte(boolean fermer, char direction) {
        this.fermer = fermer;
        this.direction = direction;
        if (this.direction == 'n')
            num = 1;
        if (this.direction == 's')
            num = 2;
        if (this.direction == 'o')
            num = 3;
        if (this.direction == 'e')
            num = 4;
        if (this.direction == 'b')
            num = 10;
    }

    public void paint_porte(Graphics g) {
        g.setColor(Color.lightGray);
        if (direction == 'n') {
            posX = Frame.panel.getWidth() - 675;
            posY = 35;
            if (fermer) {
                g.setColor(Color.blue);
                g.fillRect(posX, posY, 100, 25);
                try {
                    BufferedImage image = ImageIO.read(new File("cle1.png"));
                    g.drawImage(image, posX, posY, 35, 35, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                g.fillRect(posX, posY, 100, 25);
        }
        if (direction == 's') {
            posX = Frame.panel.getWidth() - 675;
            posY = Frame.panel.getHeight() - 60;
            if (fermer) {
                g.setColor(Color.blue);
                g.fillRect(posX, posY, 100, 25);
                try {
                    BufferedImage image = ImageIO.read(new File("cle2.png"));
                    g.drawImage(image, posX, posY, 35, 35, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                g.fillRect(posX, posY, 100, 25);
        }
        if (direction == 'o') {
            posX = 35;
            posY = Frame.panel.getHeight() - 400;
            if (fermer) {
                g.setColor(Color.blue);
                g.fillRect(posX, posY, 25, 100);
                try {
                    BufferedImage image = ImageIO.read(new File("cle3.png"));
                    g.drawImage(image, posX, posY, 35, 35, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                g.fillRect(posX, posY, 25, 100);

        }
        if (direction == 'e') {
            posX = Frame.panel.getWidth() - 65;
            posY = Frame.panel.getHeight() - 400;
            if (fermer) {
                g.setColor(Color.blue);
                g.fillRect(posX, posY, 25, 100);
                try {
                    BufferedImage image = ImageIO.read(new File("cle4.png"));
                    g.drawImage(image, posX, posY, 35, 35, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                g.fillRect(posX, posY, 25, 100);
        }
        if (direction == 'b') {
            posX = (Frame.panel.getWidth() - 100) / 2;
            posY = (Frame.panel.getHeight() - 100) / 2;
            if (fermer) {
                g.setColor(new Color(255, 215, 0));
                g.fillRect(posX, posY, 100, 25);
                try {
                    BufferedImage image = ImageIO.read(new File("cleB.png"));
                    g.drawImage(image, posX, posY, 35, 35, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                g.fillRect(posX, posY, 100, 25);
        }
    }
}