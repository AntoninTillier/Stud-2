
public class Elephant extends Mammifere {

    public Elephant() {
        super();
    }

    public Elephant(String nom) {
        super();
        setNom(nom);
    }

    public void afficher() {
        super.afficher();
        System.out.println("Et j'ai une longue trompe.");
    }
}
