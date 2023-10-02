
public class Cygne extends Oiseau {

    public Cygne() {
        super();
    }

    public Cygne(String nom) {
        super();
        setNom(nom);
    }

    public void afficher() {
        super.afficher();
        System.out.println("J'ai des plumes blanches");
    }
}
