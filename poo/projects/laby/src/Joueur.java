import java.io.Serializable;
import java.util.*;

public class Joueur extends Individu implements InterfaceCollectionObjets {
    private Piece position;
    private int poidsMax;
    private int forceMax;
    private int vieMax;
    private int force;
    private int vie;
    private ArrayList<Objet> objets;
    private int objAff = -1;
    private double[] speed = {0, 0};
    int xAtk;
    int yAtk;
    private int degats;
    private int armor;
    private boolean attack = false;
    private String sideAtk;
    private int atkCount = 0;
    private int atkDelay = 0;
    private boolean noDmg = false;
    private int noDmgCount = 0;
    private int lvl;
    private int exp;
    boolean LevelUp = false;

    public Joueur(Piece p, int pm, int lv, int xp, int lf, int fr, int dm, int arm) {
        super(0, 0);
        position = p;
        poidsMax = pm;
        force = fr;
        vie = lf;
        forceMax = force;
        vieMax = vie;
        objets = new ArrayList<Objet>();
        degats = dm;
        armor = arm;
        lvl = lv;
        exp = xp;
    }

    public void accel(boolean n, double s) {
        if (n) speed[0] += s;
        else speed[1] += s;
    }

    public void decel(boolean n) {
        if (n) speed[0] /= 1.2;
        else speed[1] /= 1.2;
    }

    public void stop(boolean n) {
        if (n) speed[0] = 0;
        else speed[1] = 0;
    }

    public double[] getSpeed() {
        return speed;
    }

    public boolean getInvincibility() {
        return noDmg;
    }

    public void startInvincibility() {
        noDmg = true;
        noDmgCount = 40;
    }

    public void noDmgCount() {
        if (noDmgCount > 0) {
            noDmgCount--;
            if (noDmgCount == 0) {
                noDmg = false;
            }
        }
    }

    public int getLevel() {
        return lvl;
    }

    public int getExp() {
        return exp;
    }

    public void gainExp(int e) {
        exp += e;
        if (exp >= lvl * 100) {
            exp -= (lvl * 100);
            lvl++;
            LevelUp = true;
        }
    }

    public void lifeUp() {
        vieMax += 10;
        guerir();
    }

    public void stamUp() {
        forceMax += 10;
        nourrir();
    }

    public void poidsUp() {
        poidsMax += 1;
    }

    public void dmgUp() {
        degats += 2;
    }

    public void armorUp() {
        armor += 2;
    }

    public int getDmg() {
        return degats;
    }

    public int getArmor() {
        return armor;
    }

    public Objet getObjet(int i) {
        return objets.get(i);
    }

    public void removeObjet(Objet o) {
        objets.remove(o);
    }

    public void poseObjet(Objet o) {
        objets.remove(o);
        o.setPosition((int) Frame.PlayerPos[0], (int) Frame.PlayerPos[1]);
        position.addObjet(o);
    }

    public void addObjet(Objet o) {
        objets.add(o);
    }

    public int nbObjets() {
        return objets.size();
    }

    public int getObjAff() {
        return objAff;
    }

    public void setObjAff(int i) {
        this.objAff = i;
    }

    public int getPoidsPorte() {
        int res = 0;
        for (int i = 0; i < nbObjets(); i++) {
            res += objets.get(i).getPoids();
        }
        return res;
    }

    public int getPoidsMax() {
        return poidsMax;
    }

    public void ouvrePorte(Porte po) {
        if (nbObjets() > 0) {
            for (int i = 0; i < nbObjets(); i++) {
                Objet ob = getObjet(i);
                if (ob.getClass().getName().equals("Cle")) {
                    if (ob.getNumero() == po.getNumero()) {
                        Piece piTemp = po.getDerriere();
                        if (po.getDerriere().nord != null) {
                            if (piTemp.nord.getNumero() == po.getNumero()) po.getDerriere().nord.ouvrir();
                        }
                        if (po.getDerriere().sud != null) {
                            if (piTemp.sud.getNumero() == po.getNumero()) po.getDerriere().sud.ouvrir();
                        }
                        if (po.getDerriere().ouest != null) {
                            if (piTemp.ouest.getNumero() == po.getNumero()) po.getDerriere().ouest.ouvrir();
                        }
                        if (po.getDerriere().est != null) {
                            if (piTemp.est.getNumero() == po.getNumero()) po.getDerriere().est.ouvrir();
                        }
                        po.ouvrir();
                        removeObjet(ob);
                    }
                }
            }
        }
    }

    public void deplacer() {
        force--;
    }

    public void setPosition(Piece p) {
        this.position = p;
    }

    public void prendreObjet() {
        int nbObjetDansLaPiece = getPosition().nbObjets();
        if (nbObjetDansLaPiece > 0) {
            Scanner sc = new Scanner(System.in);
            int no;
            do {
                System.out.println("quel numéro d'objet ? (entre 0 et " + (nbObjetDansLaPiece - 1) + ")");
                no = sc.nextInt();
            } while (no < 0 || no >= nbObjetDansLaPiece);
            Objet o = getPosition().getObjet(no);
            int poidsTotal = 0;
            for (int i = 0; i < nbObjets(); i++)
                poidsTotal += getObjet(i).getPoids();
            poidsTotal += o.getPoids();
            if (poidsTotal <= poidsMax) {
                addObjet(o);
                getPosition().removeObjet(o);
            } else
                System.out.println("l'objet est trop lourd");
        }
    }

    public Objet objetI() {
        Scanner sc = new Scanner(System.in);
        int i;
        int nbobj = nbObjets();
        if (nbobj > 0) {
            do {
                System.out.println("quel objet désirez vous ? (numéro de 0 à " + (nbobj - 1) + ")");
                i = sc.nextInt();
            } while (i < 0 || i >= nbobj);
            return getObjet(i);
        } else {
            System.out.println("le joueur n'a pas d'objets");
            return null;
        }
    }

    public void manger(Nourriture n) {
        this.force += n.getForce();
        if (this.force >= this.forceMax) this.force = forceMax;
    }

    public void soigner(Medicament m) {
        this.vie += m.getVie();
        if (this.vie >= this.vieMax) this.vie = vieMax;
    }

    public void combat(Monstre m) {
        if (m.getForce() - armor > 0) vie -= m.getForce() + armor;
    }

    public void combatM(Joueur j) {
        if (j.getDmg() - armor > 0) vie -= j.getDmg() + armor;
    }

    public void guerir() {
        vie = vieMax;
    }

    public void nourrir() {
        force = forceMax;
    }

    public int[] getStats() {
        int res[] = {vie, vieMax, force, forceMax, exp, lvl, poidsMax};
        return res;
    }

    public int getForce() {
        return force;
    }

    public int getVie() {
        return vie;
    }

    public int getVieMax() {
        return vieMax;
    }

    public boolean Attacking() {
        return attack;
    }

    public void createAtk(char s) {
        if (s == 'h') sideAtk = "Haut";
        else if (s == 'b') sideAtk = "Bas";
        else if (s == 'd') sideAtk = "Droite";
        else sideAtk = "Gauche";

        attack = true;
    }

    public String getAtkSide() {
        return sideAtk;
    }

    public boolean keepAtk(int valueMax) {
        if (atkCount <= valueMax) {
            atkCount++;
            return true;
        } else {
            atkCount = 0;
            attack = false;
            return false;
        }
    }

    public void endAtk() {
        attack = false;
    }

    public Piece getPosition() {
        return position;
    }
}
