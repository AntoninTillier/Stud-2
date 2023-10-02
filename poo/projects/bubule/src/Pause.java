import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.plaf.LabelUI;
import javax.swing.plaf.metal.MetalLabelUI;

public class Pause {

    protected static int x = 0;
    BufferedImage image;
    public void pause() {
        if (x == 0) {
            Frame.time.setIgnoreRepaint(true);
            Frame.time.setBackground(new Color(255, 255, 255, 0));
            Frame.time.setForeground(Color.white);
            Frame.time.setUI((LabelUI) MetalLabelUI.createUI(Frame.time));
            Frame.time.setBounds(3 * Frame.panel.getWidth() / 4 + 200, 10, 100, 20);
            Frame.panel.add(Frame.time);
            x++;
        }
        Frame.time.run();
        try {
            image = ImageIO.read(new File("images/retour_jeu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Frame.ml.x >= 20 && Frame.ml.x <= 70 && Frame.ml.Y >= 20 && Frame.ml.y <= 70) {
            if (Frame.ml.clic) {
                Frame.kl.pause = false;
                x = 0;
                Frame.panel.remove(Frame.time);
            }
        }
    }

    public void paint_pause(Graphics g) {
        g.setColor(new Color(65, 66, 68, 85));
        g.fillRect(0, 0, Frame.panel.getWidth(), Frame.panel.getHeight());
        g.drawImage(image, 20, 20, 50, 50, null);
        g.setColor(new Color(65, 66, 68, 95));
        g.fillRect(3 * Frame.panel.getWidth() / 4, Frame.panel.getHeight() / 3, 300, 200);
        g.setColor(Color.black);
        if (Frame.ennemis.palier < 1000)
            g.drawString("Palier : " + String.valueOf(Frame.ennemis.palier), 3 * Frame.panel.getWidth() / 4 + 10, Frame.panel.getHeight() / 3 + 35);
        else
            g.drawString("Palier : " + String.valueOf(Frame.ennemis.palier / 1000) + "K", 3 * Frame.panel.getWidth() / 4 + 10, Frame.panel.getHeight() / 3 + 35);
        if (Frame.bubule.lvl < 1000)
            g.drawString("Lvl : " + String.valueOf(Frame.bubule.lvl), 3 * Frame.panel.getWidth() / 4 + 10, Frame.panel.getHeight() / 3 + 65);
        else
            g.drawString("Lvl : " + String.valueOf(Frame.bubule.lvl / 1000) + "K", 3 * Frame.panel.getWidth() / 4 + 10, Frame.panel.getHeight() / 3 + 65);
        if (Frame.ennemis.countDead - 1 < 1000)
            g.drawString("Ennemis tués : " + String.valueOf(Frame.ennemis.countDead - 1), 3 * Frame.panel.getWidth() / 4 + 10, Frame.panel.getHeight() / 3 + 95);
        else
            g.drawString("Ennemis tués : " + String.valueOf((Frame.ennemis.countDead - 1) / 1000) + "K", 3 * Frame.panel.getWidth() / 4 + 10, Frame.panel.getHeight() / 3 + 95);
        if (Frame.bubule.dead < 1000)
            g.drawString("Nb de morts : " + String.valueOf(Frame.bubule.dead), 3 * Frame.panel.getWidth() / 4 + 10, Frame.panel.getHeight() / 3 + 125);
        else
            g.drawString("Nb de morts : " + String.valueOf(Frame.bubule.dead / 1000) + "K", 3 * Frame.panel.getWidth() / 4 + 10, Frame.panel.getHeight() / 3 + 125);
    }
}