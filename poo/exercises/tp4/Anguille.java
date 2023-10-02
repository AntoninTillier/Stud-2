
public class Anguille extends Poisson {

    public Anguille() {
        super();
    }

    public Anguille(String nom) {
        super();
        setNom(nom);
    }

    public void afficher() {
        super.afficher();
        System.out.println("Je n'ai pas de machoire.");
    }
}
