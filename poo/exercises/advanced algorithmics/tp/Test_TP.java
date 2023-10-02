public class Test_TP {

    public static void main(String[] args) {
        LList12 L = new LList12();
        int x = 1;
        while (x != 11) {
            L.add(x);
            x++;
        }
        System.out.println("Liste : " + L.toString());

        // --- Exercice 2 ---

        // 1
        System.out.println(L.nbOccurrence(1));

        // 2
        System.out.println(L.nbOccurrencesRec(1));

        // --- Exercice 3 ---

        // 1
        L.carre();
        System.out.println(L.toString());

        // 2
        LList12 L2 = new LList12();
        L.carreFin(L2);
        System.out.println(L2.toString());

        // --- Exercice 4 ---

        // 1 voir construteur LList12

        // 2
        System.out.println(L.nbPlusGrands(5));

        // 3
        L = L.listePlusGrands(50);
        System.out.println(L.toString());

        // --- Exercice 5 ---

        LListCharacter C = new LListCharacter();
        LListCharacter C1 = new LListCharacter();
        x = 1;
        C.add('b');
        while (x != 11) {
            C.add('a');
            //C1.add('a');
            if (x % 2 == 0)
                C1.add('b');
            else
                C1.add('a');
            x++;
        }
        C.dequeue();
        System.out.println(C.toString());
        System.out.println(C1.toString());

        // 1
        System.out.println(C.equals(C1));

        // 2
        LListCharacter C2 = new LListCharacter();
        C.clone(C2);
        System.out.println(C2.toString());

        // 3
        //System.out.println(C.containsAll(C1));
        System.out.println(C.containsAllRec(C1));
    }

}