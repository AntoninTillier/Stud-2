import java.util.ArrayList;

public abstract class Animal {
    private String nom;
    private int id;
    private static int ID = 0;
    private char sexe;
    private ArrayList<Animal> enfants = new ArrayList<Animal>();
    private ArrayList<Animal> parents = new ArrayList<Animal>();

    public Animal() {
        id = ID;
        ID++;
        if (Math.random() >= 0.5) {
            sexe = 'M';
        } else {
            sexe = 'F';
        }
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void afficher() {
        if (nom != null) {
            System.out.println("Je m'appelle " + nom);
        }
        System.out.println("Je suis un animal de sexe " + sexe + ".  (id=" + id + ")");
        System.out.print("J'ai comme enfant : ");
        for (int i = 0; i < enfants.size(); i++) {
            System.out.print(enfants.get(i).id + "  ");
        }
        System.out.println();
        System.out.print("J'ai comme parent : ");
        for (int i = 0; i < parents.size(); i++) {
            System.out.print(parents.get(i).id + "  ");
        }
        System.out.println();
    }

    //Ajoute ani comme parent à this
    public void ajouterParent(Animal ani) {
        boolean ok = parents.size() < 2;
        if (parents.size() == 1) {
            ok = ok && (ani.sexe != parents.get(0).sexe);
        }
        if (ok) {
            parents.add(ani);
            ani.enfants.add(this);
        }
    }

    //Ajouter ani comme enfant à this
    public void ajouterEnfant(Animal ani) {
        boolean ok = ani.parents.size() < 2;
        if (ani.parents.size() == 1) {
            ok = ok && (this.sexe != ani.parents.get(0).sexe);
        }
        if (ok) {
            ani.parents.add(this);
            enfants.add(ani);
        }
    }

    //Recherche recursive de l'animal d'identificateur 'plop'
    public Animal rechercher(int plop) {
        if (plop == this.id) {
            return this;
        } else {
            Animal result = null;
            for (int i = 0; i < enfants.size(); i++) {
                if (result == null) {
                    result = enfants.get(i).rechercher(plop);
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Girafe ani0 = new Girafe("Plam");
        //ani1.afficher();
        Girafe ani1 = new Girafe();
        //ani2.afficher();
        Requin ani2 = new Requin();
        Araignee ani3 = new Araignee();
        //ani3.afficher();
        //ani4.afficher();
        ArrayList<Animal> liste = new ArrayList<Animal>();
        liste.add(ani0);
        liste.add(ani1);
        Arbre genea = new Arbre(liste);
        int id = ani0.getId(); // id contient l'identificateur de ani0
        genea.ajouterCommeEnfant(ani2, id);
        id = ani3.getId(); //id contient l'identificateur de ani2
        genea.ajouterCommeParent(ani1, id); // Peut ne pas ajouter ani1 comme parent (sexe choisi aléatoirement lors de la création)
        genea.ajouterCommeEnfant(ani3, id);
        ani3.afficher();
    }
}
