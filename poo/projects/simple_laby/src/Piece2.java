import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Piece2 extends Piece {
    int zz = 0;

    public Piece2(boolean passageSecret) {
        super(passageSecret);

    }

    public void ouverture() {
        if (this.num == 10 && Frame.j.nbMonstreMort == Frame.l.totalMonstre && zz == 0) {
            this.objets.add(new Cle(10, 300, 300));
            zz++;
        }
        for (Monstre e : m)
            e.deplace_Monstre();
        if (this.m.size() > 1)
            for (int i = 0; i < m.size(); i++) {
                Monstre e = m.get(i);
                for (int j = 0; j < m.size() - 1; j++)
                    e.colisiondeMonstre(m.get(j + 1));
            }
        if (this.passageSecret && Frame.j.secret && m.size() == 0) {
            if (Frame.j.posX > Frame.panel.getWidth() - 675 && Frame.j.posX < Frame.panel.getWidth() - 525
                    && Frame.j.posY > 285 && Frame.j.posY < 435) {
                Frame.j.posX = 100;
                Frame.j.posY = 100;
                Frame.i = 14;
                Frame.j.secret = false;
            }
        }
        if (this.pN != null)
            if (!this.pN.fermer && Frame.j.posX > (Frame.panel.getWidth() - 675)
                    && Frame.j.posX < (Frame.panel.getWidth() - 575) && Frame.j.posY > 35 && Frame.j.posY < 80) {
                Frame.j.posY = 605;
                Frame.i -= 5;
            }
        if (this.pS != null)
            if (!this.pS.fermer && Frame.j.posX > (Frame.panel.getWidth() - 675)
                    && Frame.j.posX < (Frame.panel.getWidth() - 575) && Frame.j.posY > (Frame.panel.getHeight() - 80)
                    && Frame.j.posY < Frame.panel.getHeight() - 35) {
                Frame.j.posY = 90;
                Frame.i += 5;
            }
        if (this.pO != null)
            if (!this.pO.fermer && Frame.j.posX > 35 && Frame.j.posX < 80
                    && Frame.j.posY > (Frame.panel.getHeight() - 400) && Frame.j.posY < Frame.panel.getHeight() - 300) {
                Frame.j.posX = 1140;
                Frame.i--;
            }
        if (this.pE != null)
            if (!this.pE.fermer && Frame.j.posX > Frame.panel.getWidth() - 80
                    && Frame.j.posX < Frame.panel.getWidth() - 65 && Frame.j.posY > (Frame.panel.getHeight() - 400)
                    && Frame.j.posY < Frame.panel.getHeight() - 300) {
                Frame.j.posX = 90;
                Frame.i++;
            }
        if (this.pB != null)
            if (!this.pB.fermer && Frame.j.posX > (Frame.panel.getWidth() - 100) / 2
                    && Frame.j.posX < ((Frame.panel.getWidth() - 100) / 2) + 100
                    && Frame.j.posY > (Frame.panel.getHeight() - 100) / 2
                    && Frame.j.posY < ((Frame.panel.getHeight() - 100) / 2) + 35) {
                Frame.j.posX = 90;
                Frame.i = 13;
            }
    }

    public void paint_piece(Graphics g) {
        if (this.num != 13) {
            try {
                this.image = ImageIO.read(new File("map2.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.setColor(new Color(0, 0, 0, 70));
            g.fillRect(0, 0, Frame.panel.getWidth(), Frame.panel.getHeight());
            g.setColor(Color.black);
            g.drawImage(this.image, 0, 0, Frame.panel.getWidth(), Frame.panel.getHeight(), null);
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
        for (int i = 0; i < this.m.size(); i++) {
            Monstre e = m.get(i);
            String s = e.getClass().toString();
            if (e.pdv <= 5 && s.equals("class Boss")) {
                Frame.j.boss = true;
            }
            e.paint_monstre(g);
        }
        for (int i = 0; i < this.individu.size(); i++) {
            Individu ind = individu.get(i);
            ind.paint_individu(g);
        }
        if (this.passageSecret && Frame.j.secret) {
            try {
                this.image = ImageIO.read(new File("passage_secret.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(this.image, Frame.panel.getWidth() - 675, 285, 150, 150, null);
        }
        if (this.num != 14) {
            if (this.pN != null)
                this.pN.paint_porte(g);
            if (this.pS != null)
                this.pS.paint_porte(g);
            if (this.pO != null)
                this.pO.paint_porte(g);
            if (this.pE != null)
                this.pE.paint_porte(g);
            if (this.pB != null)
                this.pB.paint_porte(g);
            for (Objet o : objets)
                o.paint_objet(g);
        } else {
            if (this.m.size() == 0) {
                if (this.pN != null)
                    this.pN.paint_porte(g);
                if (this.pS != null)
                    this.pS.paint_porte(g);
                if (this.pO != null)
                    this.pO.paint_porte(g);
                if (this.pE != null)
                    this.pE.paint_porte(g);
                if (this.pB != null)
                    this.pB.paint_porte(g);
                for (Objet o : objets)
                    o.paint_objet(g);
            }
        }
    }
}