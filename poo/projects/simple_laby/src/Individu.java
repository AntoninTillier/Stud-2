import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Individu {
    int posX;
    int posY;
    ArrayList<Objet> objet = new ArrayList<Objet>();

    public void collisionMur() {
        if (this.posY < 75)
            this.posY += 3;
        if (this.posY > Frame.panel.getHeight() - 75)
            this.posY -= 3;
        if (this.posX < 75)
            this.posX += 3;
        if (this.posX > Frame.panel.getWidth() - 75)
            this.posX -= 3;
    }

    public void collisionIndividu() {
        for (int i = 0; i < Frame.l.lab.get(Frame.i).individu.size(); i++) {
            Individu ind = Frame.l.lab.get(Frame.i).individu.get(i);
            if (this.posX > ind.posX && this.posX < ind.posX + 100 && this.posY > ind.posY
                    && this.posY < ind.posY + 100) {
                if (this.posY < ind.posY)
                    this.posY += 4;
                if (this.posY > ind.posY)
                    this.posY -= 4;
                if (this.posX < ind.posX)
                    this.posX += 4;
                if (this.posX > ind.posX)
                    this.posX -= 4;
            }
        }
    }

    public void paint_individu(Graphics g) {}
}