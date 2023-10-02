
public class Escargot extends Mollusque {

    public Escargot() {
        super();
    }

    public Escargot(String nom) {
        super();
        setNom(nom);
    }

    public void afficher() {
        super.afficher();
        System.out.println("J'ai une coque.");
    }
}
