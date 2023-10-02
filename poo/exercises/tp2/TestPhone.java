
public class TestPhone {

    public static void main(String[] args) {
        Telephone t = new Telephone("Samsung", "A9", 06666665, "Mauve");
        System.out.println(t);
        Telephone s = new Smartphone("Ape", "iFone", 123456789, "RFS", "Robot", true, 4);
        System.out.println(s);
        s.telephoner(t);
    }

}
