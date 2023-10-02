import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Piece {
    int num;
    Porte pN = null;
    Porte pS = null;
    Porte pO = null;
    Porte pE = null;
    Porte pB = null;
    boolean passageSecret;
    ArrayList<Objet> objets = new ArrayList<Objet>();
    ArrayList<Monstre> m = new ArrayList<Monstre>();
    ArrayList<Individu> individu = new ArrayList<Individu>();
    BufferedImage image;

    public Piece(boolean passageSecret) {
        this.passageSecret = passageSecret;
    }

    public void ouverture() {
        for (Monstre e : m)
            e.deplace_Monstre();
        if (m.size() > 1)
            for (int i = 0; i < m.size(); i++) {
                Monstre e = m.get(i);
                for (int j = 0; j < m.size() - 1; j++)
                    e.colisiondeMonstre(m.get(j + 1));
            }
        if (passageSecret && Frame.j.secret && m.size() == 0) {
            if (Frame.j.posX > Frame.panel.getWidth() - 675 && Frame.j.posX < Frame.panel.getWidth() - 525
                    && Frame.j.posY > 285 && Frame.j.posY < 435) {
                Frame.j.miniBoss = true;
                Frame.j.posX = 100;
                Frame.j.posY = 100;
                Frame.i = 10;
                Frame.j.secret = false;
            }
        }
        if (pN != null)
            if (!pN.fermer && Frame.j.posX > (Frame.panel.getWidth() - 675)
                    && Frame.j.posX < (Frame.panel.getWidth() - 575) && Frame.j.posY > 35 && Frame.j.posY < 80) {
                Frame.j.posY = 605;
                Frame.i -= 5;
            }
        if (pS != null)
            if (!pS.fermer && Frame.j.posX > (Frame.panel.getWidth() - 675)
                    && Frame.j.posX < (Frame.panel.getWidth() - 575) && Frame.j.posY > (Frame.panel.getHeight() - 80)
                    && Frame.j.posY < Frame.panel.getHeight() - 35) {
                Frame.j.posY = 90;
                Frame.i += 5;
            }
        if (pO != null)
            if (!pO.fermer && Frame.j.posX > 35 && Frame.j.posX < 80 && Frame.j.posY > (Frame.panel.getHeight() - 400)
                    && Frame.j.posY < Frame.panel.getHeight() - 300) {
                Frame.j.posX = 1140;
                Frame.i--;
            }
        if (pE != null)
            if (!pE.fermer && Frame.j.posX > Frame.panel.getWidth() - 80 && Frame.j.posX < Frame.panel.getWidth() - 65
                    && Frame.j.posY > (Frame.panel.getHeight() - 400) && Frame.j.posY < Frame.panel.getHeight() - 300) {
                Frame.j.posX = 90;
                Frame.i++;
            }
        if (pB != null)
            if (!pB.fermer && Frame.j.posX > (Frame.panel.getWidth() - 100) / 2
                    && Frame.j.posX < ((Frame.panel.getWidth() - 100) / 2) + 100
                    && Frame.j.posY > (Frame.panel.getHeight() - 100) / 2
                    && Frame.j.posY < ((Frame.panel.getHeight() - 100) / 2) + 35) {
                Frame.j.posX = 90;
                Frame.i = 25;
            }
    }

    public void paint_piece(Graphics g) {
        if (num != 25) {
            try {
                image = ImageIO.read(new File("map.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(image, 0, 0, Frame.panel.getWidth(), Frame.panel.getHeight(), null);
            g.fillRect(0, 0, Frame.panel.getWidth(), 50);
            g.fillRect(0, 650, Frame.panel.getWidth(), 50);
            g.fillRect(0, 0, 50, Frame.panel.getHeight());
            g.fillRect(1220, 0, 50, Frame.panel.getHeight());
        } else {
            g.setColor(new Color(255, 215, 0));
            g.fillRect(0, 0, Frame.panel.getWidth(), Frame.panel.getHeight());
            g.setColor(new Color(238, 232, 170));
            g.fillRect(0, 0, Frame.panel.getWidth(), 50);
            g.fillRect(0, 650, Frame.panel.getWidth(), 50);
            g.fillRect(0, 0, 50, Frame.panel.getHeight());
            g.fillRect(1220, 0, 50, Frame.panel.getHeight());
        }
        for (int i = 0; i < m.size(); i++) {
            Monstre e = m.get(i);
            e.paint_monstre(g);
        }
        for (int i = 0; i < individu.size(); i++) {
            Individu ind = individu.get(i);
            ind.paint_individu(g);
        }
        if (passageSecret && Frame.j.secret && m.size() == 0) {
            try {
                image = ImageIO.read(new File("passage_secret.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(image, Frame.panel.getWidth() - 675, 285, 150, 150, null);
        }
        if (num != 12) {
            if (pN != null)
                pN.paint_porte(g);
            if (pS != null)
                pS.paint_porte(g);
            if (pO != null)
                pO.paint_porte(g);
            if (pE != null)
                pE.paint_porte(g);
            if (pB != null)
                pB.paint_porte(g);
            for (Objet o : objets)
                o.paint_objet(g);
        } else {
            if (m.size() == 0) {
                if (pB != null)
                    pB.paint_porte(g);
                for (Objet o : objets)
                    o.paint_objet(g);
            }
        }
    }
}