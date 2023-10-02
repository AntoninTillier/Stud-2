
public class Groupe {

    // Question 1 - constructeur
    private Musicien[] musiciens;
    private String name;

    public Groupe(String name, Musicien[] musiciens) {
        this.name = name;
        this.musiciens = musiciens;
    }

    // Question 2 - affichage
    public String affiche() {
        String res = "Ladies and Gentlemen, please welcome " + this.name + "\n";
        for (Musicien m : musiciens) {
            res += "on the " + m.affiche() + "\n";
        }
        return res;
    }

    // Question 3 - cout pour embaucher le groupe
    public int getCost() {
        int cout = 0;
        for (Musicien m : musiciens) {
            cout += m.getCout();
        }
        return cout;
    }

    // Question 4 - ajouter un musicien au groupe
    public void recruter(Musicien m) {
        Musicien[] tmp = new Musicien[this.musiciens.length + 1];
        int i = 0;
        for (Musicien mus : this.musiciens) {
            tmp[i] = mus;
            i++;
        }
        tmp[i] = m;
        this.musiciens = tmp;
    }

    // Question 5 - un super groupe est forme de rockstar
    public boolean isSuperGroup() {
        boolean res = true;
        for (Musicien m : this.musiciens) {
            res = res && m.isRockStar();
        }
        return res;
    }

    // Question 6 - reunion de 2 groupes
    public Groupe fusionner(String nouveauNom, Groupe g) {
        Groupe tmp = new Groupe(nouveauNom, this.musiciens);
        for (Musicien m : g.musiciens) {
            tmp.recruter(m);
        }
        return tmp;
    }
}
