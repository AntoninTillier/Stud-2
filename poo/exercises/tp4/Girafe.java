
public class Girafe extends Mammifere {

    public Girafe() {
        super();
    }

    public Girafe(String nom) {
        super();
        setNom(nom);
    }

    public void afficher() {
        super.afficher();
        System.out.println("Et j'ai un long coup.");
    }
}
