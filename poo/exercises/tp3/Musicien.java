
public class Musicien {
    private String nom;
    private String instrument;
    private boolean rock;
    private boolean jazz;
    private boolean blues;
    private boolean classique;
    private int cout;

    public Musicien(String name, String inst,
                    boolean rk, boolean jz,
                    boolean bl, boolean cl, int cost) {
        this.nom = name;
        this.instrument = inst;
        this.rock = rk;
        this.blues = bl;
        this.jazz = jz;
        this.classique = cl;
        this.cout = cost;
    }

    // Question 1 - affichage
    public String affiche() {
        return this.instrument + ", " + this.nom;
    }

    // Question 2
    public int getCout() {
        return this.cout;
    }

    // Question 3
    public boolean isRockStar() {
        return this.cout > 100000 && this.rock;
    }

    // Question 4
    public boolean sameInstrument(Musicien m) {
        return this.instrument.equals(m.instrument);
    }

}