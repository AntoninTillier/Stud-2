import java.util.ArrayList;

public class Labyrinthe {
    ArrayList<Piece> lab = new ArrayList<Piece>();
    int totalMonstre;

    public void creeLab() {
        int a = 0;
        while (a <= 25) {
            lab.add(new Piece((a == 25) ? true : false));
            a++;
        }
        a = 0;
        for (Piece p : lab) {
            p.num = a;
            if (a == 0) {
                p.pS = new Porte(false, 's');
                p.pE = new Porte(false, 'e');
            }
            if (a == 4) {
                p.pS = new Porte(false, 's');
                p.pO = new Porte(false, 'o');
            }
            if (a == 20) {
                p.pN = new Porte(false, 'n');
                p.pE = new Porte(false, 'e');
            }
            if (a == 24) {
                p.pN = new Porte(false, 'n');
                p.pO = new Porte(false, 'o');
            }
            if (a == 12)
                p.pB = new Porte(true, 'b');
            if (a == 5 || a == 10 || a == 15) {
                p.pN = new Porte(false, 'n');
                p.pS = new Porte(false, 's');
                p.pE = new Porte(false, 'e');
            }
            if (a == 9 || a == 14 || a == 19) {
                p.pN = new Porte(false, 'n');
                p.pS = new Porte(false, 's');
                p.pO = new Porte(false, 'o');
            }
            if (a >= 1 && a <= 3 || a >= 21 && a <= 23) {
                if (a >= 21 && a <= 23)
                    p.pN = new Porte(false, 'n');
                else
                    p.pS = new Porte(false, 's');
                p.pO = new Porte(false, 'o');
                p.pE = new Porte(false, 'e');
            }
            if (a == 6 || a == 7 || a == 8 || a == 11 || a == 13 || a == 16 || a == 17 || a == 18) {
                if (a != 17)
                    p.pN = new Porte(false, 'n');
                else
                    p.pN = new Porte(true, 'n');
                if (a != 7)
                    p.pS = new Porte(false, 's');
                else
                    p.pS = new Porte(true, 's');
                if (a != 13)
                    p.pO = new Porte(false, 'o');
                else
                    p.pO = new Porte(true, 'o');
                if (a != 11)
                    p.pE = new Porte(false, 'e');
                else
                    p.pE = new Porte(true, 'e');
            }
            a++;
        }
        a = 0;
        int c = 2;
        for (Piece p : lab) {
            if (a == 25) {
                int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                int y = 100 + (int) (Math.random() * (600 - 100) + 1);
                p.m.add(new MiniBoss(x, y));
            }
            if (a % 5 == 1 && c != 5) {
                int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                int y = 100 + (int) (Math.random() * (600 - 100) + 1);
                p.objets.add(new Cle(c, x, y));
                c++;
            }
            if (a == 12) {
                p.objets.add(new Cle(10, 100, 100));
                p.objets.add(new Nourriture(1, 1150, 600));
                p.objets.add(new Medicament(50, 100, 600));
                int z = 0;
                while (z < 3) {
                    int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                    int y = 100 + (int) (Math.random() * (600 - 100) + 1);
                    p.m.add(new Monstre(x, y));
                    z++;
                }
            }
            int b = (int) (Math.random() * a);
            if (a != 12 && a != 25 && b > 0 && b < 10) {
                int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                int y = 100 + (int) (Math.random() * (600 - 100) + 1);
                p.m.add(new Monstre(x, y));
                if ((a + 1) % 5 == 2 && c == 5) {
                    p.objets.add(new Medicament(50, x, y));
                    c++;
                }
                if (totalMonstre == 1)
                    p.objets.add(new Cle(1, 100, 100));
                totalMonstre++;
                if (a % 9 == 6) {
                    int xx = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                    int yy = 100 + (int) (Math.random() * (600 - 100) + 1);
                    p.m.add(new Monstre(xx, yy));
                    totalMonstre++;
                }
            }
            a++;
        }
        Frame.l.lab.get((int) (Math.random() * 25)).m.add(new Sage(100 + (int) (Math.random() * ((1100 - 100) + 1)),
                100 + (int) (Math.random() * (600 - 100) + 1)));
        if (totalMonstre >= 16) {
            int rand = (int) (Math.random() * 25);
            if (rand == 12)
                rand++;
            int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
            int y = 100 + (int) (Math.random() * (600 - 100) + 1);
            lab.get(rand).objets.add(new Nourriture(1, x, y));
        }
        a = 0;
        while (a < 2) {
            int rand = (int) (Math.random() * 25);
            if (rand == 12)
                rand++;
            int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
            int y = 100 + (int) (Math.random() * (600 - 100) + 1);
            if (a == 0)
                lab.get(rand).individu.add(new Medecin(x, y));
            if (a == 1)
                lab.get(rand).individu.add(new Cuisinier(x, y));
            a++;
        }
    }

    public void creeLab2() {
        lab.clear();
        totalMonstre = 0;
        int a = 0;
        while (a < 25) {
            if (a >= 1 && a <= 4 || a == 6 || a == 9 || a >= 10 && a <= 11 || a >= 13 && a <= 14 || a == 16 || a == 19
                    || a >= 21 && a <= 24)
                lab.add(new Piece2((a == 10) ? true : false));
            else
                lab.add(null);
            a++;
        }
        a = 0;
        for (Piece p : lab) {
            if (p != null) {
                p.num = a;
                if (a >= 1 && a <= 4) {
                    if (a == 1) {
                        p.pS = new Porte(false, 's');
                        p.pE = new Porte(false, 'e');
                    } else if (a == 4) {
                        p.pS = new Porte(false, 's');
                        p.pO = new Porte(false, 'o');
                    } else {
                        p.pO = new Porte(false, 'o');
                        p.pE = new Porte((a == 2) ? true : false, 'e');
                    }
                }
                if (a == 6 || a == 9 || a == 16 || a == 19) {
                    p.pN = new Porte(false, 'n');
                    p.pS = new Porte(false, 's');
                }
                if (a >= 10 && a <= 11 || a == 14) {
                    if (a == 10)
                        p.pE = new Porte(false, 'e');
                    else if (a == 11) {
                        p.pN = new Porte(false, 'n');
                        p.pS = new Porte(false, 's');
                        p.pO = new Porte(false, 'o');
                    } else {
                        if (a == 14) {
                            p.pN = new Porte(false, 'n');
                            p.pS = new Porte(false, 's');
                            p.pB = new Porte(true, 'b');
                        } else {
                            p.pN = new Porte(false, 'n');
                            p.pS = new Porte(false, 's');
                        }
                    }
                }
                if (a >= 21 && a <= 24) {
                    if (a == 21) {
                        p.pN = new Porte(false, 'n');
                        p.pE = new Porte(false, 'e');
                    } else if (a == 24) {
                        p.pN = new Porte(false, 'n');
                        p.pO = new Porte(false, 'o');
                    } else {
                        p.pO = new Porte(false, 'o');
                        p.pE = new Porte((a == 22) ? true : false, 'e');
                    }
                }
                a++;
            } else
                a++;
        }
        a = 0;
        int c = 0;
        for (Piece p : lab) {
            if (a >= 1 && a <= 4 || a == 6 || a == 9 || a >= 10 && a <= 11 || a >= 13 && a <= 14 || a == 16 || a == 19
                    || a >= 21 && a <= 24) {
                if (a != 13) {
                    if (a == 10) {
                        int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                        int y = 100 + (int) (Math.random() * (600 - 100) + 1);
                        p.objets.add(new Medicament(50, x, y));
                    } else {
                        int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                        int y = 100 + (int) (Math.random() * (600 - 100) + 1);
                        p.m.add(new Monstre(x, y));
                        if (a == 21 && c == 0) {
                            p.objets.add(new Cle(4, x, y));
                            c++;
                        }
                        totalMonstre++;
                        if (a % 9 == 6) {
                            int xx = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                            int yy = 100 + (int) (Math.random() * (600 - 100) + 1);
                            p.m.add(new Monstre(xx, yy));
                            totalMonstre++;
                        }
                    }
                } else {
                    int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                    int y = 100 + (int) (Math.random() * (600 - 100) + 1);
                    p.m.add(new Boss(x, y));
                }
            }
            a++;
        }
        int r = (int) (Math.random() * 3);
        if (r == 0) {
            r = 6;
            lab.get(r).objets.add(new Cle(4, 100, 100));
        } else
            lab.get(r).objets.add(new Cle(4, 100, 100));
        a = 0;
        while (a < 2) {
            int rand = (int) (Math.random() * 25);
            if (rand != 0 && rand != 5 && rand != 7 && rand != 8 && rand != 12 && rand != 13 && rand != 15 && rand != 17
                    && rand != 18 && rand != 20) {
                int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
                int y = 100 + (int) (Math.random() * (600 - 100) + 1);
                if (a == 0)
                    lab.get(rand).individu.add(new Medecin(x, y));
                if (a == 1)
                    lab.get(rand).individu.add(new Cuisinier(x, y));
                a++;
            } else {
                if (a > 0)
                    a--;
                else
                    a = 0;
            }
        }
    }
}