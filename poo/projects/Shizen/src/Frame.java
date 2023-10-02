import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.LabelUI;
import javax.swing.plaf.TextUI;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.plaf.metal.MetalLabelUI;
import javax.swing.plaf.metal.MetalTextFieldUI;

@SuppressWarnings("serial")
public class Frame extends JFrame {
    public static Graph panel = new Graph();
    protected static BoutonListener bl = new BoutonListener();
    protected static Horloge time = new Horloge();
    protected static Menu menu;

    public static void main(String[] args) {
        new Frame();
    }

    public Frame() {
        menu = new Menu();
        this.setUndecorated(true);
        Shape sh = new RoundRectangle2D.Double(0, 0, 1205, 700, 20, 20);
        this.setShape(sh);
        this.setSize(1205, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        menu.b.addActionListener(bl);
        menu.b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.b.setBackground(new Color(0, 0, 0, 235));
        menu.b.setForeground(Color.white);
        menu.b.setUI((ButtonUI) MetalButtonUI.createUI(menu.b));
        menu.pseudo.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        menu.pseudo.setOpaque(true);
        menu.pseudo.setForeground(Color.darkGray);
        menu.pseudo.setUI((TextUI) MetalTextFieldUI.createUI(menu.pseudo));
        menu.q.addActionListener(bl);
        menu.q.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu.q.setBackground(new Color(150, 0, 0));
        menu.q.setForeground(Color.white);
        menu.q.setUI((ButtonUI) MetalButtonUI.createUI(menu.q));
        menu.save.setBackground(Color.darkGray);
        menu.save.setForeground(Color.gray);
        menu.save.setFont(new Font("Arial", Font.BOLD, 65));
        menu.save.setUI((LabelUI) MetalLabelUI.createUI(menu.save));
        menu.titre.setBackground(Color.darkGray);
        menu.titre.setForeground(Color.gray);
        menu.titre.setFont(new Font("Arial", Font.BOLD, 75));
        menu.titre.setUI((LabelUI) MetalLabelUI.createUI(menu.titre));
        time.setUI((LabelUI) MetalLabelUI.createUI(time));
        menu.ancien.setBackground(Color.darkGray);
        menu.ancien.setForeground(Color.gray);
        menu.ancien.setFont(new Font("Arial", Font.BOLD, 65));
        menu.ancien.setUI((LabelUI) MetalLabelUI.createUI(menu.ancien));
        time.setBounds(1100, 10, 100, 20);
        menu.titre.setBounds(200, 100, 1000, 75);
        menu.pseudo.setBounds(500, 250, 180, 30);
        menu.b.setBounds(425, 285, 180, 30);
        menu.q.setBounds(615, 285, 180, 30);
        menu.save.setBounds(200, 600, 1000, 65);
        menu.ancien.setBounds(100, 500, 1000, 65);
        this.getContentPane().setBackground(new Color(235, 235, 235));
        this.getContentPane().add(time);
        this.getContentPane().add(menu.titre);
        this.getContentPane().add(menu.pseudo);
        this.getContentPane().add(menu.b);
        this.getContentPane().add(menu.q);
        this.getContentPane().add(menu.ancien);
        this.getContentPane().add(menu.save);
        this.setVisible(true);
        changement();
    }

    public Frame(boolean a) {
        this.setTitle("Shizen");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1205, 700);
        this.setLocationRelativeTo(null);
        Mouse ml = new Mouse();
        this.addMouseListener(ml);
        this.setBackground(Color.white);
        ArrayList<Tuile> dispo = new ArrayList<Tuile>();
        for (int i = 0; i < 288; i++) {
            int n = 1 + i % 9;
            Color c = (i < 72) ? new Color(255, 50, 50)
                    : (i < 144) ? new Color(50, 255, 50) : (i < 216) ? new Color(50, 50, 255) : new Color(255, 255, 50);
            dispo.add(new Tuile(c, n));
        }
        for (int i = 0; i < 288; i++) {
            int alea = (int) (dispo.size() * Math.random());
            int x = i % 24;
            int y = (int) (i / 24);
            dispo.get(alea).setPos(x, y);
            panel.tab[x][y] = dispo.get(alea);
            dispo.remove(alea);
        }
        this.setContentPane(panel);
        this.setVisible(true);
        panel.score = 0;
        ArrayList<Tuile> select = new ArrayList<Tuile>();
        boolean back = false;
        while (panel.score < 288 && this.finis()) {
            if (!back) {
                if (panel.posY > 604)
                    back = true;
                else
                    panel.posY++;
            } else if (back) {
                if (panel.posY < 0)
                    back = false;
                else
                    panel.posY--;
            }
            if ((ml.x != 0 && ml.y != 0)) {
                int x = (int) (ml.x / 50);
                int y = (int) (ml.y / 50);
                if (y < 12) {
                    if (panel.tab[x][y].n != -1)
                        if (!panel.tab[x][y].click) {
                            panel.tab[x][y].click = true;
                            select.add(panel.tab[x][y]);
                        } else {
                            panel.tab[x][y].click = false;
                            select.remove(panel.tab[x][y]);
                        }
                }
                ml.reset();
            }
            if (select.size() == 2) {
                Tuile t0 = select.get(0);
                Tuile t1 = select.get(1);
                t0.click = false;
                t1.click = false;
                int distance = Math.abs(t0.x - t1.x) + Math.abs(t0.y - t1.y);
                if (distance <= 3 && t0.n == t1.n && t0.c.equals(t1.c)) {
                    int n0 = t0.x;
                    int n1 = t1.x;
                    t0.supp();
                    t1.supp();
                    panel.score += 2;
                    recentre(panel.tab, n0);
                    recentre(panel.tab, n1);
                    if (verifVide(panel.tab, (int) (this.getWidth() / 50))) {
                        if (this.getWidth() > 400) {
                            this.setSize(this.getWidth() - 50, 700);
                            this.setLocationRelativeTo(null);
                        }
                    }
                    if (verifVide(panel.tab, (int) (this.getWidth() / 50))) {
                        if (this.getWidth() > 400) {
                            this.setSize(this.getWidth() - 50, 700);
                            this.setLocationRelativeTo(null);
                        }
                    }
                }
                select.clear();
            }
            this.repaint();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        boolean x = false;
        while (!x) {
            if (panel.score < 288) {
                panel.victoire = -1;
                Sound fail = null;
                try {
                    fail = new Sound(new File("Fail.wav"));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                fail.run();
                save();
                this.repaint();
                this.setSize(350, 180);
                this.setLocationRelativeTo(null);
                panel.removeAll();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                x = true;
                this.dispose();
                BoutonListener.shizen = false;
                panel.victoire = 0;
                new Frame();
            } else {
                panel.victoire = 1;
                Sound fin = null;
                try {
                    fin = new Sound(new File("fin.wav"));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                fin.run();
                save();
                this.repaint();
                this.setSize(350, 180);
                this.setLocationRelativeTo(null);
                panel.removeAll();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                x = true;
                this.dispose();
                BoutonListener.shizen = false;
                panel.victoire = 0;
                new Frame();
            }
        }
    }

    public boolean finis() {
        boolean fin = false;
        for (int i = 0; i < 24; i++)
            for (int j = 0; j < 12 && (j != 12 || i != 24); j++)
                for (int a = -3; a <= 3; a++)
                    for (int b = -3; b <= 3; b++)
                        if (i + a >= 0 && i + a < 24 && j + b >= 0 && j + b < 12 && (a > 0 || b > 0)
                                && Math.abs(a) + Math.abs(b) <= 3) {
                            Tuile t0 = panel.tab[i][j];
                            Tuile t1 = panel.tab[i + a][j + b];
                            if (t0.n != -1 && t1.n != -1 && t0.n == t1.n && t0.c.equals(t1.c))
                                fin = true;
                        }
        return fin;

    }

    public void changement() {
        while (!BoutonListener.shizen) {
            time.run();
            if (BoutonListener.shizen) {
                this.dispose();
                new Frame(true);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean verifVide(Tuile[][] tab, int n) {
        boolean check = false;
        for (int i = 0; i < n; i++)
            if (tab[i][11].n == -1) {
                check = true;
                rassembleur(tab, i);
            }
        return check;
    }

    public static void rassembleur(Tuile[][] tab, int n) {
        for (int i = n; i < 23; i++)
            for (int j = 0; j < 12; j++) {
                tab[i][j].copy(tab[i + 1][j]);
                tab[i + 1][j].supp();
            }
    }

    public static void recentre(Tuile[][] tab, int n) {
        for (int j = 10; j >= 0; j--)
            if (tab[n][j + 1].n == -1) {
                tab[n][j + 1].copy(tab[n][j]);
                tab[n][j].supp();
            }
    }

    public static String load() {
        String var = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader("sauvgarde.txt"));
            String line = new String();
            while ((line = in.readLine()) != null) {
                var = line;
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error");
        }
        return var;
    }

    public static void save() {
        try {
            if (!new File("sauvgarde.txt").exists()) {
                new File("sauvgarde.txt").createNewFile();
            }
            File fichier = new File("sauvgarde.txt");
            try {
                FileWriter writer = new FileWriter(fichier);
                try {
                    writer.write(BoutonListener.name + " : " + String.valueOf(panel.score));
                } finally {
                    writer.close();
                }
            } catch (Exception e) {
                System.out.println("impossible write");
            }
        } catch (IOException e) {
            System.out.println("error flied");
        }
    }
}