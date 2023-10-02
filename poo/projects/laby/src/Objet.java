import java.io.Serializable;

public class Objet implements Serializable {
    private int poids;
    private String nom;
    private String desc;
    private String spriteName;
    int x;
    int y;

    public Objet(int masse, String n, String d, String sp, int xI, int yI) {
        poids = masse;
        nom = n;
        desc = d;
        spriteName = sp;
        x = xI;
        y = yI;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getPoids() {
        return poids;
    }

    public String getNom() {
        return nom;
    }

    public String getDesc() {
        return desc;
    }

    public String getSprName() {
        return spriteName;
    }

    public int getNumero() {
        return -1;
    }
}
