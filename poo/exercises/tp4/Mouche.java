
public class Mouche extends Arthropode {

    public Mouche() {
        super();
    }

    public Mouche(String nom) {
        super();
        setNom(nom);
    }

    public void afficher() {
        super.afficher();
        System.out.println("J'ai six pattes.");
    }
}
