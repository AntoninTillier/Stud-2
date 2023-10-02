public class Test_TP3 {

    public static void main(String[] args) {

        LListCharacter l = new LListCharacter();
        l.listAlea(20);
        System.out.println(l.toString());

        // --- Exercice 1 ---

        // question 3
        System.out.println(l.frequence().toString());
        System.out.println();

        // --- Exercice 2 ---

        LList12 n = new LList12();
        for (int i = 0; i < 20; i++) {
            n.add(i + 1);
        }
        System.out.println(n.toString());

        // question 1
        System.out.println(l.zip(n).toString());
        System.out.println();

        // --- Exercice 3 ---

        LList12 ll = new LList12();
        ll.add(8);
        ll.add(12);
        ll.add(21);
        ll.add(28);
        //ll.list12Alea(4, 10);
        LList12 lll = new LList12();
        lll.add(2);
        lll.add(11);
        lll.add(20);
        lll.add(22);
        lll.add(27);
        lll.add(33);
        //lll.list12Alea(4, 10);
        System.out.println(ll.toString());
        System.out.println(lll.toString());

        // question 1
        ll.interclassement(lll);
        System.out.println(ll.toString());

    }
}