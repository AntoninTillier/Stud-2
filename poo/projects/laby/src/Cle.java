public class Cle extends Objet {
    private int numero;
    public Cle(int n, String sp, int xI, int yI) {
        super(1, "Clé " + n, "Ouvre la porte portant le numéro " + n, sp, xI, yI);
        numero = n;
    }
    public void affiche() {
        System.out.println("cle numéroté : " + numero + " poids " + getPoids() + "gr");
    }

    public int getNumero() {
        return numero;
    }
}
