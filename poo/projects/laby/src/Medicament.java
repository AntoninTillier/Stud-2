public class Medicament extends Objet {
    private int vie;

    public Medicament(int po, String n, String d, String sp, int s, int xI, int yI) {
        super(po, n, d, sp, xI, yI);
        vie = s;
    }

    public void affiche() {
        System.out.println("Medicament de " + vie + " point(s) " + " poids " + getPoids() + "gr");
    }

    public int getVie() {
        return vie;
    }

}
