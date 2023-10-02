import java.awt.Color;
import java.awt.Graphics;

public class MiniBoss extends Monstre {

    public MiniBoss(int posX, int posY) {
        super(posX, posY);
        this.pdv = 500;
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
            Frame.j.pdv -= 5;
    }

    public void paint_monstre(Graphics g) {
        if (!Frame.kl.pause)
            Frame.balle.animation();
        Frame.balle.paint_balle(g);
        g.setColor(new Color(255, 148, 0));
        g.fillOval(this.posX, this.posY, 50, 25);
        g.fill3DRect(300, 5, pdv, 40, true);
    }

}