import java.io.Serializable;
import java.util.*;

public class Piece implements InterfaceCollectionObjets, Serializable {
    private boolean sortie;
    private int num;
    int lon1;
    int lon2;
    int ht1;
    int ht2;
    private boolean bossR = false;
    Porte nord;
    Porte sud;
    Porte ouest;
    Porte est;
    private ArrayList<Objet> objets = new ArrayList<Objet>();
    private ArrayList<Individu> pnjs = new ArrayList<Individu>();

    public Piece(int n) {
        this.num = n;
        lon1 = (int) (Math.random() * (300 - 100) + 100);
        lon2 = (int) (Math.random() * (900 - 500) + 500);
        ht1 = (int) (Math.random() * (300 - 100) + 100);
        ht2 = (int) (Math.random() * (700 - 450) + 450);
    }

    public Piece(int n, int l1, int l2, int h1, int h2) {
        this.num = n;
        lon1 = l1;
        lon2 = l2;
        ht1 = h1;
        ht2 = h2;
        bossR = true;
    }

    public int getNum() {
        return num;
    }

    public boolean getType() {
        return bossR;
    }

    public Objet getObjet(int i) {
        return objets.get(i);
    }

    public void removeObjet(Objet o) {
        objets.remove(o);
    }

    public void addObjet(Objet o) {
        objets.add(o);
    }

    public int nbObjets() {
        return objets.size();
    }

    public Individu getPNJ(int i) {
        return pnjs.get(i);
    }

    public void addPNJ(Individu i) {
        pnjs.add(i);
    }

    public void removePNJ(Individu i) {
        pnjs.remove(i);
    }

    public int nbPNJ() {
        return pnjs.size();
    }

    public int[] getRoomSize() {
        int[] res = {lon1, lon2, ht1, ht2};
        return res;
    }

    public boolean tresorDansLaPiece() {
        boolean resu = false;
        for (int i = 0; i < nbObjets(); i++) {
            Objet o = getObjet(i);
            String s = o.getClass().getName();

            if (s.equals("Tresor"))
                resu = true;
        }
        return resu;
    }

    public void setExit() {
        this.sortie = true;
    }

    public boolean isExit() {
        return sortie;
    }

    public void ajoutPorte(Porte po, Piece pi, String s) {
        po.ajoutPiece(this, pi);
        int roomSize[] = pi.getRoomSize();
        if (s.equals("Nord")) {
            nord = po;
            if (po.getFerme()) {
                pi.ajoutPorteBoss(new Porte(po.getNumero(), (roomSize[1] - roomSize[0]) / 2 + roomSize[0], roomSize[3]), this, "Sud");
            }
        }
        if (s.equals("Sud")) {
            sud = po;
            if (po.getFerme()) {
                pi.ajoutPorteBoss(new Porte(po.getNumero(), (roomSize[1] - roomSize[0]) / 2 + roomSize[0], roomSize[2]), this, "Nord");
            }
        }
        if (s.equals("Est")) {
            est = po;
            if (po.getFerme()) {
                pi.ajoutPorteBoss(new Porte(po.getNumero(), roomSize[0], (roomSize[3] - roomSize[2]) / 2 + roomSize[2]), this, "Ouest");
            }
        }
        if (s.equals("Ouest")) {
            ouest = po;
            if (po.getFerme()) {
                pi.ajoutPorteBoss(new Porte(po.getNumero(), roomSize[1], (roomSize[3] - roomSize[2]) / 2 + roomSize[2]), this, "Est");
            }
        }
    }

    public void ajoutPorteBoss(Porte po, Piece pi, String s) {
        po.ajoutPiece(this, pi);
        if (s.equals("Nord")) {
            nord = po;
        }
        if (s.equals("Sud")) {
            sud = po;
        }
        if (s.equals("Est")) {
            est = po;
        }
        if (s.equals("Ouest")) {
            ouest = po;
        }
    }

    public Piece pieceVoisine() {
        System.out.println("Tapez une lettre (N (Nord), E (Est), S (Sud), O (Ouest))");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char r = s.charAt(0);
        return pieceVoisine(r);
    }

    public Porte getPorte(char r) {
        switch (r) {
            case 'N':
                return nord;
            case 'E':
                return est;
            case 'S':
                return sud;
            case 'O':
                return ouest;
            default:
                System.out.println("Vous devez taper une des 4 lettres suivantes N, E, S, O");
                return null;
        }
    }

    public Piece pieceVoisine(char r) {
        Porte po = getPorte(r);
        if (po == null) {
            System.out.println("Erreur");
            return this;
        } else {
            if (!po.getFerme()) {
                return po.autrePiece(this);
            } else {
                System.out.println("Pas de Porte ou Porte fermée");
                return this;
            }
        }
    }
}
