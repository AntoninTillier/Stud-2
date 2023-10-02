import java.awt.Color;

public class Tuile {
    Color c;
    int n;
    boolean click;
    int x;
    int y;

    public Tuile(Color c, int n) {
        this.c = c;
        this.n = n;
        click = false;
        this.x = 0;
        this.y = 0;
    }

    public void copy(Tuile t) {
        this.c = t.c;
        this.n = t.n;
    }

    public void supp() {
        this.c = new Color(0, 0, 0, 0);
        this.n = -1;
        click = false;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        if (click) {
            int r = this.c.getRed();
            int g = this.c.getGreen();
            int b = this.c.getBlue();
            return new Color(r, g, b, 99);
        } else
            return c;
    }
}