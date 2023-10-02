
public class HomoSapiens extends Mammifere {

    public HomoSapiens() {
        super();
    }

    public HomoSapiens(String nom) {
        super();
        setNom(nom);
    }

    public void afficher() {
        super.afficher();
        System.out.println("Et j'ai des capcités cognitives.");
    }
}
