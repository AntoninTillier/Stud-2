import java.util.ArrayList;

public class Arbre {

    private ArrayList<Animal> racines;

    public Arbre(ArrayList<Animal> racines) {
        this.racines = racines;
    }

    public void ajouterCommeEnfant(Animal enfant, int parent) {
        if (rechercher(parent) != null) { //Verifie que l'appelle de rechercher() aboutit bien à un resultat
            enfant.ajouterParent(this.rechercher(parent));
        }
    }

    public void ajouterCommeParent(Animal parent, int enfant) {
        if (rechercher(enfant) != null) { //Verifie que l'appelle de rechercher() aboutit bien à un resultat
            parent.ajouterEnfant(this.rechercher(enfant));
        }
    }

    public Animal rechercher(int id) {
        Animal result = null;
        for (int i = 0; i < racines.size(); i++) {
            if (result == null) {
                result = racines.get(i).rechercher(id);
            }
        }
        return result;
    }
}
