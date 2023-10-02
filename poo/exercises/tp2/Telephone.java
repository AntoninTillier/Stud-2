
public class Telephone implements Telephoner {
    private String marque;
    private String modele;
    private int numero;
    private String operateur;
    private boolean estBloque;

    public Telephone(String _marque, String _modele, int _numero, String _operateur) {
        marque = _marque;
        modele = _modele;
        numero = _numero;
        operateur = _operateur;
        estBloque = true;
    }

    public String toString() {
        return "[" + numero + "] [" + marque + "] [" + modele + "] [" + numero + "] [" + ((estBloque) ? "locked" : "unlocked") + "]";
    }

    public void unlock() {
        estBloque = false;
    }

    public void lock() {
        estBloque = true;
    }

    public void changeNumero(int num) {
        numero = num;
    }

    public boolean equals(Telephone t) {
        return this.marque.equals(t.marque) && this.modele.equals(t.modele);
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public void telephoner(Telephone t) {
        System.out.println(numero + " etablit une communication avec " + t.getNumero());

    }
}
