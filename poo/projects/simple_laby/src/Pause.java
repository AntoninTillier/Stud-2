import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pause {
    BufferedImage image;

    public void pause() {
        try {
            image = ImageIO.read(new File("retour_jeu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Frame.ml.x >= 20 && Frame.ml.x <= 70 && Frame.ml.y >= 20 && Frame.ml.y <= 70) {
            if (Frame.ml.clic) {
                Frame.kl.pause = false;
            }
        }
    }

    public void paint_pause(Graphics g) {
        g.setColor(new Color(0, 0, 0, 75));
        g.fillRect(0, 0, Frame.panel.getWidth(), Frame.panel.getHeight());
        g.drawImage(image, 20, 20, 50, 50, null);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Vie : " + (Frame.j.pdv - 20), 400, 300);
        g.drawString("Dégat : " + Frame.pioupiouJ.dmg, 400, 370);
    }

}