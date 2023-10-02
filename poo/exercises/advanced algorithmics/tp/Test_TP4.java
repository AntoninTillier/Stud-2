public class Test_TP4 {

    public static void main(String[] args) {

        LList12 l = new LList12();
        for (int i = 0; i < 10; i++) {
            l.add(i);
        }
        System.out.println(l.toString());
        int[] tab = new int[100000];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = i;
        }
        System.out.println();
        // --- Exercice 1 ---

        // question 1
        TabDicho t = new TabDicho(tab);
        long a = System.currentTimeMillis();
        t.contains(8);
        long b = System.currentTimeMillis();
        System.out.println("temps exécution : " + (b - a));

    }
}