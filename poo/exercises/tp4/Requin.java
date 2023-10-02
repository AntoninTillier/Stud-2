
public class Requin extends Poisson {

    public Requin() {
        super();
    }

    public Requin(String nom) {
        super();
        setNom(nom);
    }

    public void afficher() {
        super.afficher();
        System.out.println("J'ai un cartilage.");
    }
}
