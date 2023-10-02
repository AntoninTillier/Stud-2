import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Frame extends JFrame {

    protected static Graph panel = new Graph();
    protected static GraphMenu panelMenu = new GraphMenu();
    protected static keylistener kl = new keylistener();
    protected static MouseList ml = new MouseList();
    protected static Labyrinthe l = new Labyrinthe();
    protected static Joueur j = new Joueur(100 + (int) (Math.random() * ((1100 - 100) + 1)),
            100 + (int) (Math.random() * (600 - 100) + 1));
    protected static PiouPiou pioupiouJ = new PiouPiou();
    protected static Balle balle = new Balle();
    protected static Pause pause = new Pause();
    protected Sound menu = null;
    protected Sound lab1 = null;
    protected Sound lab2 = null;
    protected static int i = 12;
    protected static boolean propos = false;
    protected static boolean classement = false;
    String n;
    String sauvegarde;
    protected static String[] s;

    public Frame() {
        this.setContentPane(panelMenu);
        int h = 500;
        int l = 750;
        this.setUndecorated(true);
        this.setSize(l, h);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseListener(ml);
        this.addMouseMotionListener(ml);
        this.addKeyListener(kl);
        this.setVisible(true);
        menu();
    }

    public void menu() {
        lab1 = null;
        lab2 = null;
        sauvegarde = load();
        this.setLocationRelativeTo(null);
        j.pdv = 50;
        j.miniBoss = false;
        j.boss = false;
        j.inventaire.clear();
        l.lab.clear();
        boolean f = false;
        while (!f) {
            panelMenu.repaint();
            long start = System.currentTimeMillis();
            if (ml.x > 300 && ml.x < 450 && ml.y > 285 && ml.y < 325 && ml.clic && !classement && !propos) {
                ml.clic = false;
                n = JOptionPane.showInputDialog(null, "Veuillez entrer un nom", "Pseudo", JOptionPane.QUESTION_MESSAGE);
                if (n != null && n.length() > 0) {
                    f = true;
                } else
                    f = false;
            }
            if (ml.x > 0 && ml.x < 150 && ml.y > 420 && ml.y < 480 && ml.clic && !propos) {
                ml.clic = false;
                propos = true;
            }
            if (ml.x > 0 && ml.x < 150 && ml.y > 420 && ml.y < 480 && ml.clic && propos) {
                ml.clic = false;
                propos = false;
            }
            if (ml.x > 550 && ml.x < 740 && ml.y > 20 && ml.y < 80 && ml.clic && !classement) {
                ml.clic = false;
                classement = true;
            }
            if (ml.x > 550 && ml.x < 740 && ml.y > 20 && ml.y < 80 && ml.clic && classement) {
                ml.clic = false;
                classement = false;
            }
            long fin = System.currentTimeMillis() + 15;
            try {
                Thread.sleep(fin - start);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.getContentPane().remove(panelMenu);
        int h = 700;
        int l = 1270;
        this.setSize(l, h);
        jeu();
    }

    public void jeu() {
        menu = null;
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        l.creeLab();
        i = (int) (Math.random() * 12);
        int a = 0;
        while (!j.victoire && j.pdv > 20) {
            if (j.miniBoss && a == 0) {
                j.nbMonstreMort = 0;
                l.creeLab2();
                a++;
            }
            panel.repaint();
            long start = System.currentTimeMillis();
            if (kl.pause) {
                pause.pause();
            } else {
                l.lab.get(i).ouverture();
                j.seDeplacer();
                j.interagirIndividu();
                j.prendreObjet();
                j.ouvrirPorteF();
                j.choiObjet();
                j.enleverObjet();
                j.rechercherPassageS();
            }
            long fin = System.currentTimeMillis() + 15;
            try {
                Thread.sleep(fin - start);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        save();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int h = 500;
        int l = 750;
        this.setSize(l, h);
        this.setContentPane(panelMenu);
        menu();
    }

    public int compt() {
        int point = 0;
        Scanner sc = null;
        try {
            FileReader fis = new FileReader("save.txt");
            sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                if (sc.nextLine().contains(":"))
                    point++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
        return point;
    }

    public String load() {
        s = new String[10];
        int point = compt();
        String var = "";
        Scanner sc = null;
        if (point == 10) {
            try {
                FileReader fis = new FileReader("save.txt");
                sc = new Scanner(fis);
                while (sc.hasNextLine()) {
                    String l = sc.nextLine();
                    if (l.contains("kill")) {
                        var += l + "\n";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                sc.close();
            }
        } else {
            try {
                FileReader fis = new FileReader("save.txt");
                sc = new Scanner(fis);
                while (sc.hasNextLine()) {
                    var += sc.nextLine() + "\n";
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                sc.close();
            }
        }
        try {
            FileReader fis = new FileReader("save.txt");
            sc = new Scanner(fis);
            int i = 0;
            while (sc.hasNextLine()) {
                String l = sc.nextLine();
                s[i] = l;
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
        return var;
    }

    public void save() {
        String s = n + " : ";
        if (j.pdv <= 20) {
            s += "mort";
            if (j.miniBoss)
                s += "\tkill";
            else
                s += "\tvivant";
            if (j.boss)
                s += "\tkill";
            else
                s += "\tvivant";
        }
        if (j.victoire) {
            s += "vivant";
            s += "\tkill";
            s += "\tkill";
        }
        sauvegarde += s;
        try {
            if (!new File("save.txt").exists()) {
                new File("save.txt").createNewFile();
            }
            File fichier = new File("save.txt");
            try {
                FileWriter writer = new FileWriter(fichier);
                try {
                    writer.write(sauvegarde);
                } finally {
                    writer.close();
                }
            } catch (Exception e) {
            }
        } catch (IOException e) {
        }
    }
}