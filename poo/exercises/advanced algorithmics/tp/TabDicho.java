public class TabDicho {

    private int[] tab;

    public TabDicho() {
    }

    public TabDicho(int size) {
        this.tab = new int[size];
    }

    public TabDicho(int[] tab) {
        this.tab = tab;
    }

    public void contains(int elem) {
        rechercheDicho(tab, elem, 0, tab.length, 0);
    }

    public void rechercheDicho(int[] tab, int elem, int d, int f, int ind) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + "  ");
        }
        System.out.println();
        ind = (tab.length) / 2;

        if (elem == tab[ind]) {
            System.out.println("elem trouvé");
        } else if (tab.length == 1 && elem != tab[ind]) {
            System.out.println("La valeur n'est pas dans le tableau");
        } else {
            if (elem > tab[ind]) {
                d = tab.length / 2;
                int[] tab1 = new int[d];
                for (int j = 0; j < tab1.length; j++) {
                    tab1[j] = tab[j + (tab.length + 1) / 2];
                }
                rechercheDicho(tab1, elem, d, f, ind);
            } else if (elem < tab[ind]) {
                f = tab.length / 2;
                int[] tab2 = new int[f];
                for (int i = 0; i < f; i++) {
                    tab2[i] = tab[i];
                }
                rechercheDicho(tab2, elem, d, f, ind);
            }
        }
    }

}