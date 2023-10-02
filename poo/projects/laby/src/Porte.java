import java.io.Serializable;

public class Porte implements Serializable {
    private Piece devant;
    private Piece derriere;
    private boolean ferme;
    private int numero;
    int x;
    int y;

    public Porte(int xP, int yP) {
        x = xP;
        y = yP;
        ferme = false;
    }

    public Porte(int i, int xP, int yP) {
        numero = i;
        x = xP;
        y = yP;
        ferme = true;
    }

    public void affiche() {
        if (ferme) System.out.println("porte ferm�e Num�ro" + numero);
        else System.out.println("porte ouverte");

    }

    public void ajoutPiece(Piece p1, Piece p2) {
        devant = p1;
        derriere = p2;
    }

    public Piece autrePiece(Piece p) {
        if (devant == p) return derriere;
        else return devant;
    }

    public Piece getDerriere() {
        return derriere;
    }

    public boolean getFerme() {
        return ferme;
    }

    public int getNumero() {
        return numero;
    }

    public void ouvrir() {
        ferme = false;
    }
}
