import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Joueur extends Individu {
    boolean victoire = false;
    boolean miniBoss = false;
    boolean boss = false;
    protected double dirX = 0;
    protected double dirY = 0;
    int count = 0;
    boolean objet = false;
    boolean secret = false;
    protected int nbMonstreMort;
    int pdv = 50;
    ArrayList<Objet> inventaire = new ArrayList<Objet>();
    ArrayList<Objet> deplaceObjet = new ArrayList<Objet>();

    public Joueur(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void seDeplacer() {
        this.collisionMur();
        this.collisionIndividu();
        if (Frame.kl.up)
            dirY = -1;
        if (Frame.kl.down)
            dirY = 1;
        if (Frame.kl.left)
            dirX = -1;
        if (Frame.kl.right)
            dirX = 1;
        if (dirX * dirY != 0) {
            dirX /= Math.sqrt(2);
            dirY /= Math.sqrt(2);
        }
        this.posX += dirX * 4;
        this.posY += dirY * 4;
    }

    public void rechercherPassageS() {
        if (Frame.kl.recherche) {
            if (Frame.l.lab.get(Frame.i).passageSecret)
                secret = true;
        }
    }

    public void seSoigner(Objet o, int vie) {
        if (pdv < 50)
            pdv += vie;
        if (pdv > 50)
            pdv = 50;
        inventaire.remove(o);
    }

    public void seNourrir(Objet o, int upDmg) {
        Frame.pioupiouJ.dmg += upDmg;
        inventaire.remove(o);
    }

    public void enleverObjet() {
        for (int i = 0; i < inventaire.size(); i++) {
            Objet o = inventaire.get(i);
            if (Frame.ml.x > o.posX && Frame.ml.x < o.posX + 35 && Frame.ml.y > o.posY && Frame.ml.y < o.posY + 35
                    && Frame.ml.clic && !objet) {
                deplaceObjet.add(o);
                inventaire.remove(o);
                objet = true;
            }
        }
        for (int i = 0; i < deplaceObjet.size(); i++) {
            Objet o = deplaceObjet.get(i);
            if (Frame.ml.clic) {
                Frame.ml.tire = false;
                o.posX = Frame.ml.x;
                o.posY = Frame.ml.y;
                count++;
            } else if (count < 45) {
                o.posX = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                o.posY = 100 + (int) (Math.random() * (600 - 100) + 1);
                Frame.l.lab.get(Frame.i).objets.add(o);
                deplaceObjet.remove(o);
                count = 0;
                objet = false;
            } else {
                if (o.posX > 60 && o.posX < 1210 && o.posY > 60 && o.posY < 640) {
                    Frame.l.lab.get(Frame.i).objets.add(o);
                    deplaceObjet.remove(o);
                    count = 0;
                    objet = false;
                } else {
                    o.posX = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                    o.posY = 100 + (int) (Math.random() * (600 - 100) + 1);
                    Frame.l.lab.get(Frame.i).objets.add(o);
                    deplaceObjet.remove(o);
                    count = 0;
                    objet = false;
                }
            }
        }
    }

    public void interagirIndividu() {
        for (int i = 0; i < Frame.l.lab.get(Frame.i).individu.size(); i++) {
            Individu ind = Frame.l.lab.get(Frame.i).individu.get(i);
            String s = ind.getClass().toString();
            double a = Frame.j.posX - ind.posX;
            double b = Frame.j.posY - ind.posY;
            double l = Math.sqrt(a * a + b * b);
            a = a / l;
            b = b / l;
            if (l < 50) {
                if (s.equals("class Medecin") && inventaire.size() < 6 && ind.objet.size() != 0) {
                    inventaire.add(ind.objet.get(0));
                    ind.objet.remove(ind.objet.get(0));
                }
                if (s.equals("class Cuisinier") && inventaire.size() < 6 && ind.objet.size() != 0) {
                    inventaire.add(ind.objet.get(0));
                    ind.objet.remove(ind.objet.get(0));
                }
            }
        }
    }

    public void prendreObjet() {
        if (inventaire.size() < 6) {
            for (int j = 0; j < Frame.l.lab.get(Frame.i).objets.size(); j++) {
                Objet o = Frame.l.lab.get(Frame.i).objets.get(j);
                double a = Frame.j.posX - o.posX;
                double b = Frame.j.posY - o.posY;
                double l = Math.sqrt(a * a + b * b);
                a = a / l;
                b = b / l;
                if (l < 50) {
                    if (Frame.ml.x > o.posX && Frame.ml.x < o.posX + 35 && Frame.ml.y > o.posY
                            && Frame.ml.y < o.posY + 35) {
                        inventaire.add(o);
                        Frame.l.lab.get(Frame.i).objets.remove(o);
                    }
                }
            }
        }
    }

    public void ouvrirPorteF() {
        for (int i = 0; i < inventaire.size(); i++) {
            String s = inventaire.get(i).getClass().toString();
            if (s.equals("class Cle")) {
                Porte p = null;
                if (Frame.l.lab.get(Frame.i).pN != null && Frame.l.lab.get(Frame.i).pN.fermer)
                    p = Frame.l.lab.get(Frame.i).pN;
                if (Frame.l.lab.get(Frame.i).pS != null && Frame.l.lab.get(Frame.i).pS.fermer)
                    p = Frame.l.lab.get(Frame.i).pS;
                if (Frame.l.lab.get(Frame.i).pO != null && Frame.l.lab.get(Frame.i).pO.fermer)
                    p = Frame.l.lab.get(Frame.i).pO;
                if (Frame.l.lab.get(Frame.i).pE != null && Frame.l.lab.get(Frame.i).pE.fermer)
                    p = Frame.l.lab.get(Frame.i).pE;
                if (Frame.l.lab.get(Frame.i).pB != null && Frame.l.lab.get(Frame.i).pB.fermer)
                    p = Frame.l.lab.get(Frame.i).pB;
                if (p != null && nbMonstreMort == Frame.l.totalMonstre && !miniBoss) {
                    double a = Frame.j.posX - p.posX;
                    double b = Frame.j.posY - p.posY;
                    double l = Math.sqrt(a * a + b * b);
                    a = a / l;
                    b = b / l;
                    if (l < 90) {
                        if (p.num == inventaire.get(i).num) {
                            p.fermer = false;
                            inventaire.remove(inventaire.get(i));
                        }
                    }
                } else if (p != null && miniBoss) {
                    double a = Frame.j.posX - p.posX;
                    double b = Frame.j.posY - p.posY;
                    double l = Math.sqrt(a * a + b * b);
                    a = a / l;
                    b = b / l;
                    if (l < 90) {
                        if (p.num == inventaire.get(i).num) {
                            p.fermer = false;
                            inventaire.remove(inventaire.get(i));
                        }
                    }
                }
                if (Frame.l.lab.get(Frame.i).pB != null && p == Frame.l.lab.get(Frame.i).pB
                        && Frame.l.lab.get(Frame.i).pB.fermer) {
                    double a = Frame.j.posX - p.posX;
                    double b = Frame.j.posY - p.posY;
                    double l = Math.sqrt(a * a + b * b);
                    a = a / l;
                    b = b / l;
                    if (l < 90) {
                        if (p.num == inventaire.get(i).num) {
                            p.fermer = false;
                            inventaire.remove(inventaire.get(i));
                        }
                    }
                }
            }
        }
    }

    public void choiObjet() {
        for (int i = 0; i < inventaire.size(); i++) {
            Objet o = inventaire.get(i);
            if (Frame.ml.x > o.posX && Frame.ml.x < o.posX + 35 && Frame.ml.y > o.posY && Frame.ml.y < o.posY + 35) {
                count++;
                if (count == 66) {
                    String s = o.getClass().toString();
                    if (s.equals("class Nourriture"))
                        seNourrir(o, o.upDmg);
                    if (s.equals("class Medicament"))
                        seSoigner(o, o.pdv);
                    count = 0;
                }
            }
        }
    }

    public void paint_joueur(Graphics g) {
        if (!Frame.kl.pause) {
            Frame.pioupiouJ.animation();
            Frame.pioupiouJ.paint_pioupiou(g);
        }
        g.setColor(new Color(181, 23, 0));
        g.fillOval(this.posX + ((50 - pdv) / 2) - 25, this.posY + ((50 - pdv) / 2) - 25, pdv, pdv);
        for (int i = 0; i < inventaire.size(); i++) {
            Objet o = inventaire.get(i);
            o.posX = 10 + 35 * i;
            o.posY = 10;
            o.paint_objet(g);
        }
        if (inventaire.size() == 6) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Max", 225, 30);
        }
        for (int i = 0; i < deplaceObjet.size(); i++) {
            Objet o = deplaceObjet.get(i);
            o.paint_objet(g);
        }
        if (boss) {
            if (victoire) {
                g.setColor(new Color(0, 0, 0, 85));
                g.fillRect(0, 0, Frame.panel.getWidth(), Frame.panel.getHeight());
                g.setColor(new Color(181, 23, 0));
                g.setFont(new Font("Arial", Font.BOLD, 50));
                g.drawString("Vous avez terminé le labyrinthe", Frame.panel.getWidth() - 975,
                        Frame.panel.getHeight() - 375);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                g.setColor(new Color(0, 0, 0, 85));
                g.fillRect(0, 0, Frame.panel.getWidth(), Frame.panel.getHeight());
                g.setColor(new Color(181, 23, 0));
                g.setFont(new Font("Arial", Font.BOLD, 50));
                g.drawString("Vous avez battu le boss", Frame.panel.getWidth() - 925, Frame.panel.getHeight() - 375);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                victoire = true;
            }

        }
        if (!victoire && pdv <= 20) {
            g.setColor(new Color(0, 0, 0, 85));
            g.fillRect(0, 0, Frame.panel.getWidth(), Frame.panel.getHeight());
            g.setColor(new Color(181, 23, 0));
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Vous avez perdu", Frame.panel.getWidth() - 825, Frame.panel.getHeight() - 375);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}