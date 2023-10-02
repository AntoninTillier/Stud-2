public class Test_TP5 {

    public static void main(String[] args) {
        // Exercice 1
        Eratos e = new Eratos(120);
        System.out.println(e.toString());
        e.eratosthene();
        System.out.println(e.toString());

        // Exercice 2
        ListeReel l = new ListeReel();
        for (int i = 0; i < 10; i++) {
            l.add(i * 10.0);
        }
        System.out.println(l.toString());
        System.out.println(l.sommeCarre());
        System.out.println(l.RsommeCarre());
        System.out.println(l.isDec());

    }
}