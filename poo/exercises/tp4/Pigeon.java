
public class Pigeon extends Oiseau {

    public Pigeon() {
        super();
    }

    public Pigeon(String nom) {
        super();
        setNom(nom);
    }

    public void afficher() {
        super.afficher();
        System.out.println("J'ai des plumes grises.");
    }
}
