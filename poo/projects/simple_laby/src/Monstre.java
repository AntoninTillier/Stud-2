import java.awt.Color;
import java.awt.Graphics;

public class Monstre extends Individu {
    int pdv = 50;
    int dmg;

    public Monstre(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void deplace_Monstre() {
        attaque();
        double a = Frame.j.posX - this.posX;
        double b = Frame.j.posY - this.posY;
        double l = Math.sqrt(a * a + b * b);
        a = a / l;
        b = b / l;
        if (l > 100) {
            this.collisionMur();
            this.collisionIndividu();
            this.posX += a * 1.5;
            this.posY += b * 1.5;
        } else {
            this.collisionMur();
            this.collisionIndividu();
            this.posX -= a * 1.5;
            this.posY -= b * 1.5;
        }
    }

    public void colisiondeMonstre(Monstre e) {
        if (Math.pow((this.posX - e.posX), 2) + Math.pow((-this.posY + e.posY), 2) <= (this.pdv * e.pdv * 15)) {
            if (this.posX > e.posX) {
                this.posX += 1;
                e.posX -= 1;
            }
            if (this.posX < e.posX) {
                this.posX -= 1;
                e.posX += 1;
            }
            if (this.posY > e.posY) {
                this.posY += 1;
                e.posY -= 1;
            }
            if (this.posY < e.posY) {
                this.posY -= 1;
                e.posY += 1;
            }
        }
    }

    public void attaque() {
        double a = Frame.j.posX - this.posX;
        double b = Frame.j.posY - this.posY;
        double l = Math.sqrt(a * a + b * b);
        a = a / l;
        b = b / l;
        if (l >= 0 && l <= 40)
            Frame.j.pdv -= 2;
    }

    public void paint_monstre(Graphics g) {
        if (!Frame.kl.pause)
            Frame.balle.animation();
        Frame.balle.paint_balle(g);
        g.setColor(new Color(1, 113, 1));
        g.fillRect(this.posX + ((50 - pdv) / 2) - 25, this.posY + ((50 - pdv) / 2) - 25, pdv, pdv);
    }

}