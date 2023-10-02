public class Test_TP2 {

    public static void main(String[] args) {

        LList12 C = new LList12();
        LList12 C1 = new LList12();
        LList12 C3 = new LList12();
        C3.add(100);
        C3.add(200);
        for (int i = 0; i < 10; i++) {
            C.add(i);
            C1.add(1);
        }

        System.out.println(C.toString());
        System.out.println(C1.toString());
        System.out.println(C3.toString());

        // --- Exercice 1 ---

        // question 1
        System.out.println(C1.isStammered());
        System.out.println(C.isStammeredRec());

        // question 2
        C.stammer();
        System.out.println(C.toString());

        // question 3
        C3.stammerRec();
        System.out.println(C3.toString());

        // question 4
        C3.removeStammer();
        System.out.println(C3.toString());

        // --- Exercice 2 ---

        // question 1
        LList12 L = new LList12();
        L.init0toN(1, 2, 10);
        System.out.println(L.toString());

        // question 2
        LList12 l = new LList12();
        l.init0toNRec(1, 2, 6);
        System.out.println(l.toString());

        // question 3

        LList12 C4 = new LList12();
        C4.initNto0(1, 2, 30);
        System.out.println(C4.toString());

        // question 4
    }

}