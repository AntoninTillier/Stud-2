public class Nourriture extends Objet {
    private int force;

    public Nourriture(int po, String n, String d, String sp, int p, int xI, int yI) {
        super(po, n, d, sp, xI, yI);
        force = p;
    }

    public void affiche() {
        System.out.println("Nourriture de " + force + " point(s) de force " + " poids " + getPoids() + "gr");
    }

    public int getForce() {
        return force;
    }
}
