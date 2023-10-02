/**
 * @author thomas devogele
 */
public class Monstre extends Individu {
    private int force;
    private int originLife;
    private int life;
    private String nom;
    private int expVal;

    public Monstre(String name, int x, int y, int l, int f, int xp, String sp) {
        super(x, y);
        this.nom = name;
        originLife = l;
        life = l;
        force = f;
        expVal = xp;
        super.sprite = sp;
    }

    public int getExpVal() {
        return expVal;
    }

    public void affiche() {
        System.out.println("Monstre de force : " + force);
    }

    public void deplacer() {
        double[] pos = Frame.PlayerPos;
        if (xI < pos[0]) xI++;
        else xI--;
        if (yI < pos[1]) yI++;
        else yI--;
    }

    public int getForce() {
        return force;
    }

    public int getOriginLife() {
        return originLife;
    }

    public int getLife() {
        return life;
    }

    public void damage(int d) {
        life -= d;
    }

    public String getName() {
        return nom;
    }

}
