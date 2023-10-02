public class ATest {

    public static void main(String[] args) {
        /*
         * ArbreChar u = new ArbreChar('u', null, null); ArbreChar r = new
         * ArbreChar('r', null, null); ArbreChar f = new ArbreChar('f', null, u);
         * ArbreChar t = new ArbreChar('t', r, null); ArbreChar v = new ArbreChar('v',
         * null, null); ArbreChar s = new ArbreChar('s', null, null); ArbreChar e = new
         * ArbreChar('e', v, s); ArbreChar g = new ArbreChar('g', f, t); ArbreChar h =
         * new ArbreChar('h', e, g); // racine h h.Graph();
         *
         * ArbreChar gg = new ArbreChar('g',null,null); ArbreChar ff = new
         * ArbreChar('f',null,null); ArbreChar ee = new ArbreChar('e',gg,null);
         * ArbreChar d = new ArbreChar('d',ee,ff); ArbreChar c = new
         * ArbreChar('c',null,null); ArbreChar b = new ArbreChar('b',c,d); ArbreChar a =
         * new ArbreChar('a',b,null); // racine a a.Graph();
         */

        ArbreChar zzzzz = new ArbreChar('z', null, null);
        ArbreChar zzzz = new ArbreChar('d', null, null);
        ArbreChar zzz = new ArbreChar('c', zzzz, zzzzz);
        ArbreChar zz = new ArbreChar('b', null, null);
        ArbreChar z = new ArbreChar('a', zz, zzz);
        z.Graph();
        System.out.println(z.nbLeafLevel(2));

    }
}