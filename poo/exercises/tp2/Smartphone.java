
public class Smartphone extends Telephone {
    private String OS;
    private boolean GPS;
    private int photo;

    public Smartphone(String _marque, String _modele, int _numero, String _operateur, String _OS, boolean _GPS, int _photo) {
        super(_marque, _modele, _numero, _operateur);
        this.OS = _OS;
        this.GPS = _GPS;
        this.photo = _photo;
    }

    public String toString() {
        String res = super.toString();
        res += " [" + OS + "] [" + ((GPS) ? "GPS" : "no GPS") + "] [" + photo + " MPX]";
        return res;
    }
}
