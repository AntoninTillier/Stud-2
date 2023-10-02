
public class Araignee extends Arthropode {

    public Araignee() {
        super();
    }

    public Araignee(String nom) {
        super();
        setNom(nom);
    }

    public void afficher() {
        super.afficher();
        System.out.println("J'ai huit pattes.");
    }
}
