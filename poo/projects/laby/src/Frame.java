import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame extends JFrame {
    private static final long serialVersionUID = 1L;
    public static boolean Timer = false;
    public static int Time = 0;
    public static int TimeF = 0;
    public static int transferCount = 0;
    public static boolean multi = false;
    public static boolean isSer = false;
    public static int nbJ = 1;
    public static ServerSocket server = null;
    public static Socket socket = null;
    public static ArrayList<Socket> otherPlayers = new ArrayList<Socket>();
    public static ArrayList<Info> otherPlayersInfo = new ArrayList<Info>();
    public static boolean isInMenu = true;
    public static boolean isInModeSel = false;
    public static boolean isInMultiSel = false;
    public static boolean End = false;
    public static String LoseCause;
    public static int Wait = 0;
    public static double PlayerPos[] = new double[2];
    //public static double PlayerSpeed[] = new double[2];
    public static double Running[] = new double[2];
    public static double Slow[] = new double[2];
    public static boolean FaceRight = true;
    public static boolean FaceDown = false;
    public static boolean VerticalView = false;
    //	public static boolean FacingRight = true;
    public static int SpriteLevel = 0;
    public static int SpriteCount = 0;
    public static Piece[][] laby;
    private static Piece bossRoom;
    private static Piece exit;
    public static Joueur player;
    public static boolean newLevel = false;
    public static boolean endlessMode;
    public static int level = 1;
    public static keyListener kl = new keyListener();
    public static mouseListener ml = new mouseListener();
    public static Graph panel = new Graph();

    public Frame() {
        this.setTitle("Labyrinthe");
        this.setVisible(true);
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(exit());
        this.setContentPane(panel);
        panel.initFont();
        this.addKeyListener(kl);
        this.addMouseListener(ml);
        newGame(1);
        Trame();
    }

    @SuppressWarnings("static-access")
    public void Trame() {
        Sound menuS = null;
        Sound inGame = null;
        Sound bossFight = null;
        try {
            menuS = new Sound(new File("music/menu.wav"));
            inGame = new Sound(new File("music/laby.wav"));
            bossFight = new Sound(new File("music/boss.wav"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        while (true) {
            if (isInMenu) {
                try {
                    menuS.start();
                } catch (IllegalThreadStateException e) {
                    //e.printStackTrace();
                }
                panel.repaint();
                ml.reset();
                while (ml.clicX != -1 && ml.clicY != -1) {
                    if (ml.clicX > 208 && ml.clicX < 808 && ml.clicY > 279 && ml.clicY < 379) {
                        isInMenu = false;
                        isInModeSel = true;
                        ml.valide();
                    } else if (ml.clicX > 208 && ml.clicX < 808 && ml.clicY > 390 && ml.clicY < 491) {
                        isInMenu = false;
                        multi = true;
                        isInMultiSel = true;
                        ml.valide();
                    } else if (ml.clicX > 208 && ml.clicX < 808 && ml.clicY > 529 && ml.clicY < 629) {
                        try {
                            menuS.stopS();
                        } catch (NullPointerException e) {
                        }
                        System.exit(0);
                    } else if (ml.clicX > 208 && ml.clicX < 499 && ml.clicY > 639 && ml.clicY < 709) {
                        ml.valide();
                        showRules();
                    } else if (ml.clicX > 518 && ml.clicX < 808 && ml.clicY > 639 && ml.clicY < 709) {
                        ml.valide();
                        about();
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ml.reset();
            } else if (isInModeSel) {
                panel.repaint();
                ml.reset();
                while (ml.clicX != -1 && ml.clicY != -1) {
                    if (ml.clicX > 178 && ml.clicX < 377 && ml.clicY > 305 && ml.clicY < 405) {
                        try {
                            menuS.stopS();
                            inGame.start();
                        } catch (NullPointerException e) {
                        }
                        End = false;
                        endlessMode = false;
                        isInModeSel = false;
                        level = 1;
                        newGame(1);
                        ml.valide();
                    } else if (ml.clicX > 407 && ml.clicX < 607 && ml.clicY > 305 && ml.clicY < 405) {
                        try {
                            menuS.stopS();
                            inGame.start();
                        } catch (NullPointerException e) {
                        }
                        End = false;
                        endlessMode = false;
                        isInModeSel = false;
                        level = 6;
                        newGame(1);
                        ml.valide();
                    } else if (ml.clicX > 637 && ml.clicX < 837 && ml.clicY > 305 && ml.clicY < 405) {
                        try {
                            menuS.stopS();
                            inGame.start();
                        } catch (NullPointerException e) {
                        }
                        End = false;
                        endlessMode = false;
                        isInModeSel = false;
                        level = 10;
                        newGame(1);
                        ml.valide();
                    } else if (ml.clicX > 178 && ml.clicX < 498 && ml.clicY > 530 && ml.clicY < 631) {
                        try {
                            menuS.stopS();
                            inGame.start();
                        } catch (NullPointerException e) {
                        }
                        isInModeSel = false;
                        endlessMode = true;
                        newGame(0);
                        ml.valide();
                    } else if (ml.clicX > 518 && ml.clicX < 838 && ml.clicY > 530 && ml.clicY < 631) {
                        try {
                            menuS.stopS();
                            inGame.start();
                        } catch (NullPointerException e) {
                        }
                        isInModeSel = false;
                        endlessMode = true;
                        newGame(1);
                        ml.valide();
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ml.reset();
            } else if (isInMultiSel) {
                panel.repaint();
                ml.reset();
                while (ml.clicX != -1 && ml.clicY != -1) {
                    if (ml.clicX > 177 && ml.clicX < 498 && ml.clicY > 330 && ml.clicY < 430) {
                        isSer = true;
                        isInMultiSel = false;
                        endlessMode = true;
                        newGame(1);
                        startServer();
                        ml.valide();
                    } else if (ml.clicX > 518 && ml.clicX < 838 && ml.clicY > 330 && ml.clicY < 430) {
                        isSer = false;
                        isInMultiSel = false;
                        joinServer();
                        ml.valide();
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ml.reset();
            } else {
                try {
                    try {
                        inGame.stopS();
                    } catch (NullPointerException e) {
                    }
                } catch (IllegalThreadStateException e) {
                    e.printStackTrace();
                }
                if (!End) {
                    if (kl.Inventory) {
                        while (kl.Inventory) {
                            for (int i = 0; i < player.nbObjets(); i++) {
                                if (ml.clicX > 108 + (i * 95) && ml.clicX < 188 + (i * 95) && ml.clicY > 80 && ml.clicY < 160) {
                                    player.setObjAff(i);
                                    ml.reset();
                                    panel.repaint();
                                }
                            }
                            if (player.getObjAff() != -1) {
                                if (ml.clicX > 107 && ml.clicX < 360 && ml.clicY > 641 && ml.clicY < 692) {
                                    player.poseObjet(player.getObjet(player.getObjAff()));
                                    player.setObjAff(-1);
                                }
                            }
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            panel.repaint();
                        }
                        ml.reset();
                        player.setObjAff(-1);
                    }
                    if (kl.Pause) {
                        while (ml.clicX != -1 && ml.clicY != -1 && kl.Pause) {
                            if (ml.clicX > 87 && ml.clicX < 913 && ml.clicY > 199 && ml.clicY < 279) {
                                ml.valide();
                                kl.Pause = false;
                            } else if (ml.clicX > 87 && ml.clicX < 913 && ml.clicY > 331 && ml.clicY < 410) {
                                ml.valide();
                                kl.Pause = false;
                                newGame(1);
                            } else if (ml.clicX > 87 && ml.clicX < 913 && ml.clicY > 460 && ml.clicY < 541) {
                                ml.valide();
                                showRules();
                            } else if (ml.clicX > 87 && ml.clicX < 913 && ml.clicY > 591 && ml.clicY < 671) {
                                System.exit(0);
                            } else {
                                try {
                                    Thread.sleep(1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        ml.reset();
                    }
                    if (player.LevelUp) {
                        while (player.LevelUp) {
                            if (ml.clicX > 290 && ml.clicX < 392 && ml.clicY > 416 && ml.clicY < 518) {
                                ml.valide();
                                player.LevelUp = false;
                                player.lifeUp();
                            } else if (ml.clicX > 440 && ml.clicX < 542 && ml.clicY > 416 && ml.clicY < 518) {
                                ml.valide();
                                player.LevelUp = false;
                                player.stamUp();
                            } else if (ml.clicX > 590 && ml.clicX < 692 && ml.clicY > 416 && ml.clicY < 518) {
                                ml.valide();
                                player.LevelUp = false;
                                player.poidsUp();
                            } else if (ml.clicX > 368 && ml.clicX < 470 && ml.clicY > 570 && ml.clicY < 672) {
                                ml.valide();
                                player.LevelUp = false;
                                player.dmgUp();
                            } else if (ml.clicX > 518 && ml.clicX < 620 && ml.clicY > 570 && ml.clicY < 672) {
                                ml.valide();
                                player.LevelUp = false;
                                player.armorUp();
                            }
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        ml.reset();
                        player.setObjAff(-1);
                    } else {
                        int Buffer = TimerThread.MILLI;
                        if (multi) {
                            if (isSer) {
                                Socket soc = null;
                                try {
                                    soc = server.accept();
                                } catch (SocketTimeoutException e) {
                                } catch (IOException e) {
                                    System.err.println("Probléme de connexion ?");
                                }
                                if (soc != null) {
                                    otherPlayers.add(soc);
                                    System.out.println("Un joueur a rejoint la partie !");
                                    ObjectOutputStream out;
                                    try {
                                        out = new ObjectOutputStream(soc.getOutputStream());
                                        out.flush();
                                        Info inf = new Info(this);
                                        out.writeObject(inf);
                                        out.flush();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
                                        Object objetRecu = null;
                                        try {
                                            while (objetRecu == null) objetRecu = in.readObject();
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                        Info tempI = (Info) objetRecu;
                                        this.otherPlayersInfo.add(tempI);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (transferCount == 3) {
                                    if (otherPlayers.size() > 0) {
                                        for (int i = 0; i < otherPlayers.size(); i++) {
                                            if (otherPlayers.get(i).isClosed() || otherPlayers.get(i) == null) {
                                                otherPlayers.remove(i);
                                            } else {
                                                ObjectOutputStream out;
                                                try {
                                                    out = new ObjectOutputStream(otherPlayers.get(i).getOutputStream());
                                                    out.flush();
                                                    Info inf = new Info(this);
                                                    out.writeObject(inf);
                                                    out.flush();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                try {
                                                    ObjectInputStream in = new ObjectInputStream(otherPlayers.get(i).getInputStream());
                                                    Object objetRecu = null;
                                                    try {
                                                        while (objetRecu == null) objetRecu = in.readObject();
                                                    } catch (ClassNotFoundException e) {
                                                        e.printStackTrace();
                                                    }
                                                    Info tempI = (Info) objetRecu;
                                                    this.otherPlayersInfo.set(0, tempI);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            transferCount = 0;
                                        }
                                    }
                                } else transferCount++;
                            } else {
                                if (transferCount == 3) {
                                    Info objetRecu = null;
                                    try {
                                        while (objetRecu == null) {
                                            try {
                                                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                                                objetRecu = (Info) in.readObject();

                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    } catch (ClassNotFoundException e) {
                                        System.err.println("Probléme lors de la récupération des données");
                                        e.printStackTrace();
                                    }
                                    int[] pos = getPosLaby();
                                    laby = objetRecu.getLaby();
                                    this.otherPlayersInfo.set(0, objetRecu);
                                    player.setPosition(laby[pos[0]][pos[1]]);
                                    ObjectOutputStream out;
                                    try {
                                        out = new ObjectOutputStream(socket.getOutputStream());
                                        out.flush();
                                        Info inf = new Info(this);
                                        out.writeObject(inf);
                                        out.flush();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    transferCount = 0;
                                } else transferCount++;
                            }
                            for (int i = 0; i < this.otherPlayersInfo.size(); i++) {
                                Info inf = this.otherPlayersInfo.get(i);
                                if (inf.player.Attacking()) {
                                    if (inf.player.xAtk + 30 >= PlayerPos[0] && inf.player.xAtk <= PlayerPos[0] + 45 && inf.player.yAtk + 30 >= PlayerPos[1] && inf.player.yAtk <= PlayerPos[1] + 60) {
										player.combatM(inf.player);
                                    }
                                }
                            }
                        }
                        if (player.getPosition().isExit()) {
                            int[] st = player.getStats();
                            if (!endlessMode) {
                                LoseCause = null;
                                End = true;
                                save(st[5], st[4], level, st[6], st[1], st[3], player.getDmg(), player.getArmor());
                            } else {
                                try {
                                    Thread.sleep(20);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                level++;
                                save(st[5], st[4], level, st[6], st[1], st[3], player.getDmg(), player.getArmor());
                                newGame(1);
                            }
                        }
                        if (bossRoom.nbPNJ() == 0 && exit == null) {
                            Piece exit = new Piece(-1, 0, 1000, 0, 800);
                            bossRoom.ajoutPorte(new Porte(500, 150), exit, "Nord");
                            exit.setExit();
                        }
                        if (kl.Run) {
                            Running[0] = 2;
                            Running[1] = .1;
                        } else {
                            Running[0] = 0;
                            Running[1] = 0;
                        }
                        if (player.getPoidsPorte() > player.getPoidsMax()) {
                            Slow[0] = 2.5;
                            Slow[1] = .1;
                        } else {
                            Slow[0] = 0;
                            Slow[1] = 0;
                        }
                        if (kl.Right && player.getSpeed()[0] < (3.5 + Running[0] - Slow[0])) {
                            if (!Timer) {
                                Timer = true;
                                TimeF = TimerThread.MILLI;
                            }
                            if (!kl.Up && !kl.Down) {
                                if (VerticalView)
                                    VerticalView = false;
                                if (!FaceRight)
                                    FaceRight = true;
                            }
                            if (SpriteCount == 9) {
                                if (SpriteLevel == 0)
                                    SpriteLevel++;
                                else if (SpriteLevel == 1)
                                    SpriteLevel++;
                                else if (SpriteLevel == 2)
                                    SpriteLevel++;
                                else if (SpriteLevel == 3) {
                                    SpriteLevel = 0;
                                    player.deplacer();
                                }
                                SpriteCount = 0;
                            } else
                                SpriteCount++;
                            player.accel(true, (.2 + Running[1] - Slow[1]));
                        } else if (kl.Left && player.getSpeed()[0] > (-3.5 - Running[0] + Slow[0])) {
                            if (!Timer) {
                                Timer = true;
                                TimeF = TimerThread.MILLI;
                            }
                            if (!kl.Up && !kl.Down) {
                                if (VerticalView)
                                    VerticalView = false;
                                if (FaceRight)
                                    FaceRight = false;
                            }
                            if (SpriteCount == 9) {
                                if (SpriteLevel == 0)
                                    SpriteLevel++;
                                else if (SpriteLevel == 1)
                                    SpriteLevel++;
                                else if (SpriteLevel == 2)
                                    SpriteLevel++;
                                else if (SpriteLevel == 3) {
                                    SpriteLevel = 0;
                                    player.deplacer();
                                }
                                SpriteCount = 0;
                            } else
                                SpriteCount++;

                            player.accel(true, -(.2 - Running[1] + Slow[1]));
                        } else
                            player.decel(true);
                        if (kl.Down && player.getSpeed()[1] < (3.5 + Running[0] - Slow[0])) {
                            if (!Timer) {
                                Timer = true;
                                TimeF = TimerThread.MILLI;
                            }
                            if (!VerticalView)
                                VerticalView = true;
                            if (!FaceDown)
                                FaceDown = true;
                            if (SpriteCount == 9) {
                                if (SpriteLevel == 0)
                                    SpriteLevel++;
                                else if (SpriteLevel == 1)
                                    SpriteLevel++;
                                else if (SpriteLevel == 2)
                                    SpriteLevel++;
                                else if (SpriteLevel == 3) {
                                    SpriteLevel = 0;
                                    player.deplacer();
                                }
                                SpriteCount = 0;
                            } else
                                SpriteCount++;
                            player.accel(false, (.2 + Running[1] - Slow[1]));
                        } else if (kl.Up && player.getSpeed()[1] > (-3.5 - Running[0] + Slow[0])) {
                            if (!Timer) {
                                Timer = true;
                                TimeF = TimerThread.MILLI;
                            }
                            if (!VerticalView)
                                VerticalView = true;
                            if (FaceDown)
                                FaceDown = false;
                            if (SpriteCount == 9) {
                                if (SpriteLevel == 0)
                                    SpriteLevel++;
                                else if (SpriteLevel == 1)
                                    SpriteLevel++;
                                else if (SpriteLevel == 2)
                                    SpriteLevel++;
                                else if (SpriteLevel == 3) {
                                    SpriteLevel = 0;
                                    player.deplacer();
                                }
                                SpriteCount = 0;
                            } else
                                SpriteCount++;

                            player.accel(false, -(.2 - Running[1] + Slow[1]));
                        } else
                            player.decel(false);
                        int[] roomSize = player.getPosition().getRoomSize();
                        if (PlayerPos[0] + player.getSpeed()[0] >= roomSize[1] - 42) {
                            player.stop(true);
                            PlayerPos[0] = roomSize[1] - 42;
                        }
                        if (PlayerPos[0] + player.getSpeed()[0] <= roomSize[0]) {
                            player.stop(true);
                            PlayerPos[0] = roomSize[0];
                        }
                        if (PlayerPos[1] + player.getSpeed()[1] >= roomSize[3] - 50) {
                            player.stop(false);
                            PlayerPos[1] = roomSize[3] - 50;
                        }
                        if (PlayerPos[1] + player.getSpeed()[1] <= roomSize[2] - 20) {
                            player.stop(false);
                            PlayerPos[1] = roomSize[2] - 20;
                        }
                        if (kl.Ouvrir) {
                            Piece tempRoom = player.getPosition();
                            if (tempRoom.ouest != null) {
                                if (PlayerPos[0] <= tempRoom.ouest.x + 1 && PlayerPos[1] > tempRoom.ouest.y - 70 && PlayerPos[1] < tempRoom.ouest.y + 20) {
                                    if (!tempRoom.ouest.getFerme()) {
                                        kl.Ouvrir = false;
                                        player.setPosition(tempRoom.ouest.getDerriere());
                                        roomSize = player.getPosition().getRoomSize();
                                        PlayerPos[0] = roomSize[1] - 50;
                                        PlayerPos[1] = (roomSize[3] - roomSize[2]) / 2 + roomSize[2] - 30;
                                        continue;
                                    } else {
                                        player.ouvrePorte(player.getPosition().ouest);
                                        kl.Ouvrir = false;
                                    }
                                }
                            }
                            if (tempRoom.est != null) {
                                if (PlayerPos[0] >= tempRoom.est.x - 45 && PlayerPos[1] > tempRoom.est.y - 70 && PlayerPos[1] < tempRoom.est.y + 20) {
                                    if (!tempRoom.est.getFerme()) {
                                        kl.Ouvrir = false;
                                        player.setPosition(tempRoom.est.getDerriere());
                                        roomSize = player.getPosition().getRoomSize();
                                        PlayerPos[0] = roomSize[0] + 10;
                                        PlayerPos[1] = (roomSize[3] - roomSize[2]) / 2 + roomSize[2] - 30;
                                        continue;
                                    } else {
                                        player.ouvrePorte(player.getPosition().est);
                                        kl.Ouvrir = false;
                                    }
                                }
                            }
                            if (tempRoom.sud != null) {
                                if (PlayerPos[1] >= tempRoom.sud.y - 60 && PlayerPos[0] > tempRoom.sud.x - 70 && PlayerPos[0] < tempRoom.sud.x + 30) {
                                    if (!tempRoom.sud.getFerme()) {
                                        kl.Ouvrir = false;
                                        player.setPosition(tempRoom.sud.getDerriere());
                                        roomSize = player.getPosition().getRoomSize();
                                        PlayerPos[0] = (roomSize[1] - roomSize[0]) / 2 + roomSize[0] - 20;
                                        PlayerPos[1] = roomSize[2];
                                        continue;
                                    } else {
                                        player.ouvrePorte(player.getPosition().sud);
                                        kl.Ouvrir = false;
                                    }
                                }
                            }
                            if (tempRoom.nord != null) {
                                if (PlayerPos[1] <= tempRoom.nord.y - 20 && PlayerPos[0] > tempRoom.nord.x - 70 && PlayerPos[0] < tempRoom.nord.x + 30) {
                                    if (!tempRoom.nord.getFerme()) {
                                        kl.Ouvrir = false;
                                        player.setPosition(tempRoom.nord.getDerriere());
                                        roomSize = player.getPosition().getRoomSize();
                                        PlayerPos[0] = (roomSize[1] - roomSize[0]) / 2 + roomSize[0] - 20;
                                        PlayerPos[1] = roomSize[3] - 60;
                                        continue;
                                    } else {
                                        player.ouvrePorte(player.getPosition().nord);
                                        kl.Ouvrir = false;
                                    }
                                }
                            }
                        }
                        if (kl.Take) {
                            if (player.getPosition().nbObjets() > 0) {
                                for (int i = 0; i < player.getPosition().nbObjets(); i++) {
                                    Objet ob = player.getPosition().getObjet(i);
                                    if (PlayerPos[0] > ob.x - 50 && PlayerPos[0] < ob.x + 50 && PlayerPos[1] > ob.y - 60 && PlayerPos[1] < ob.y + 20) {
                                        player.addObjet(ob);
                                        player.getPosition().removeObjet(ob);
                                        kl.Take = false;
                                    }
                                }
                            }
                        }
                        if (kl.Item1 | kl.Item2 | kl.Item3 | kl.Item4 | kl.Item5 | kl.Item6) {
                            int nbCles = 0;
                            for (int i = 0; i < player.nbObjets(); i++) {
                                Objet ob = Frame.player.getObjet(i);
                                if (ob.getClass().getName().equals("Cle")) {
                                    nbCles++;
                                }
                                if (ob.getClass().getName().equals("Nourriture")) {
                                    if ((kl.Item1 && (i - nbCles) == 0) | (kl.Item2 && (i - nbCles) == 1) | (kl.Item3 && (i - nbCles) == 2) | (kl.Item4 && (i - nbCles) == 3) | (kl.Item5 && (i - nbCles) == 4) | (kl.Item6 && (i - nbCles) == 5)) {
                                        player.manger((Nourriture) ob);
                                        player.removeObjet(ob);
                                        kl.Item1 = false;
                                        kl.Item2 = false;
                                        kl.Item3 = false;
                                        kl.Item4 = false;
                                        kl.Item5 = false;
                                        kl.Item6 = false;
                                    }
                                } else if (ob.getClass().getName().equals("Medicament")) {
                                    if ((kl.Item1 && (i - nbCles) == 0) | (kl.Item2 && (i - nbCles) == 1) | (kl.Item3 && (i - nbCles) == 2) | (kl.Item4 && (i - nbCles) == 3) | (kl.Item5 && (i - nbCles) == 4) | (kl.Item6 && (i - nbCles) == 5)) {
                                        player.soigner((Medicament) ob);
                                        player.removeObjet(ob);
                                        kl.Item1 = false;
                                        kl.Item2 = false;
                                        kl.Item3 = false;
                                        kl.Item4 = false;
                                        kl.Item5 = false;
                                        kl.Item6 = false;
                                    }
                                }
                            }
                        }
                        if (player.getPosition().nbPNJ() > 0) {
                            for (int i = 0; i < player.getPosition().nbPNJ(); i++) {
                                Individu pnj = player.getPosition().getPNJ(i);
                                if (pnj.getClass().getName().equals("Monstre")) {
                                    if (player.Attacking()) {
                                        if (player.xAtk + 30 >= pnj.xI && player.xAtk <= pnj.xI + 45 && player.yAtk >= pnj.yI - 60 && player.yAtk <= pnj.yI + 30) {
                                            ((Monstre) pnj).damage(player.getDmg());
                                            player.endAtk();
                                            if (((Monstre) pnj).getLife() <= 0) {
                                                player.gainExp(((Monstre) pnj).getExpVal());
                                                player.getPosition().removePNJ(pnj);

                                            }
                                        }
                                    }
                                    pnj.deplacer();
                                }
                                if (PlayerPos[0] >= pnj.xI - 45 && PlayerPos[0] <= pnj.xI + 45 && PlayerPos[1] < pnj.yI + 40 && PlayerPos[1] > pnj.yI - 50) {
                                    if (pnj.getClass().getName().equals("Monstre") && !player.getInvincibility()) {
                                        player.combat((Monstre) pnj);
                                        if (pnj.xI >= PlayerPos[0] + 25) player.accel(true, -10);
                                        else if (pnj.xI <= PlayerPos[0] - 15) player.accel(true, 10);
                                        if (pnj.yI >= PlayerPos[1] + 45) player.accel(false, -10);
                                        else if (pnj.yI <= PlayerPos[1] - 35) player.accel(false, 10);
                                        player.startInvincibility();
                                    }
                                    if (pnj.getClass().getName().equals("Medecin")) {
                                        if (kl.Interact) {
                                            player.guerir();
                                            kl.Interact = false;
                                        }
                                    }
                                    if (pnj.getClass().getName().equals("Cuisinier")) {
                                        if (kl.Interact) {
                                            player.nourrir();
                                            kl.Interact = false;
                                        }
                                    }
                                }
                            }
                        }
                        if (!player.Attacking()) {
                            if (kl.Attack) {
                                if (VerticalView) {
                                    player.xAtk = (int) PlayerPos[0] + 5;
                                    if (FaceDown) {
                                        player.yAtk = (int) PlayerPos[1] + 40;
                                        player.createAtk('b');
                                    } else {
                                        player.yAtk = (int) PlayerPos[1] - 20;
                                        player.createAtk('h');
                                    }
                                } else {
                                    player.yAtk = (int) PlayerPos[1] + 10;
                                    if (FaceRight) {
                                        player.xAtk = (int) PlayerPos[0] + 50;
                                        player.createAtk('d');
                                    } else {
                                        player.xAtk = (int) PlayerPos[0] - 30;
                                        player.createAtk('g');
                                    }
                                }
                            }
                        } else {
                            if (player.keepAtk(50)) {
                                if (player.getAtkSide().equals("Haut")) player.yAtk -= 4;
                                if (player.getAtkSide().equals("Bas")) player.yAtk += 4;
                                if (player.getAtkSide().equals("Droite")) player.xAtk += 4;
                                if (player.getAtkSide().equals("Gauche")) player.xAtk -= 4;
                            }
                        }
                        if (player.getInvincibility()) player.noDmgCount();
                        PlayerPos[0] += player.getSpeed()[0];
                        PlayerPos[1] += player.getSpeed()[1];
                        if (player.getForce() <= 0) {
                            End = true;
                            LoseCause = "Force";
                        }
                        if (player.getVie() <= 0) {
                            End = true;
                            LoseCause = "Life";
                        }
                        panel.repaint();
                        if (TimerThread.MILLI - Buffer < 10) {
                            try {
                                Thread.sleep(10 - (TimerThread.MILLI - Buffer));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    while (ml.clicX != -1 && ml.clicY != -1) {
                        if (ml.clicX > 87 && ml.clicX < 913 && ml.clicY > 331 && ml.clicY < 410) {
                            ml.valide();
                            End = false;
                            newGame(1);
                        } else if (ml.clicX > 87 && ml.clicX < 913 && ml.clicY > 460 && ml.clicY < 541) {
                            ml.valide();
                            showRules();
                        } else if (ml.clicX > 87 && ml.clicX < 913 && ml.clicY > 591 && ml.clicY < 671) {
                            System.exit(0);
                        } else {
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    ml.reset();
                }
            }
        }
    }

    public static int[] getPosLaby() {
        for (int i = 0; i < laby.length; i++) {
            for (int j = 0; j < laby[i].length; j++) {
                if (player.getPosition() == laby[i][j]) return new int[]{i, j};
            }
        }
        return new int[]{0, 0};
    }

    public static void save(int plLvl, int exp, int diff, int pds, int life, int stam, int dmg, int arm) {
        try {
            if (!new File("sav.txt").exists()) {
                new File("sav.txt").createNewFile();
            }
            final File fichier = new File("sav.txt");
            try {
                FileWriter writer = new FileWriter(fichier);
                try {
                    writer.write(plLvl + " " + exp + " " + diff + " " + pds + " " + life + " " + stam + " " + dmg + " " + arm);
                } finally {
                    writer.close();
                }
            } catch (Exception e) {
                System.out.println("Impossible d'écrire dans le fichier");
            }
        } catch (IOException e) {
            System.out.println("Erreur fichier");
        }
    }

    public static int[] load() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("sav.txt"));
            String[] var = new String[8];
            int[] res = new int[8];
            String line;
            while ((line = in.readLine()) != null) {
                var = line.split(" ");
            }
            for (int i = 0; i < var.length; i++) {
                res[i] = Integer.parseInt(var[i]);
            }
            in.close();
            return res;
        } catch (Exception e) {
            save(1, 0, 1, 10, 100, 100, 5, 0);
            return load();
        }
    }

    public static void newGame(int nw) {
        int[] stats = {1, 0, level, 10, 100, 100, 5, 0};
        if (endlessMode) {
            if (nw == 0) {
                save(1, 0, 1, 10, 100, 100, 5, 0);
            }
            stats = load();
        }
        exit = null;
        End = false;
        PlayerPos[0] = 400; // position initiale du joueur
        PlayerPos[1] = 400;
        String[][] listNourr = {{"Kebab", "3", "50", "Aliment de haute qualité", "sprites_others/kebab.png"},
                {"Twix", "1", "20", "Barre chocolat-caramel", "sprites_others/food.png"},
                {"Tripes", "3", "-30", "Rien que l'odeur vous donne la nausée...", "sprites_others/food.png"}};
        String[][] listMeds = {{"Bouteille d'eau", "2", "20", "Eau de source", "sprites_others/medkit.png"},
                {"Seringue usag�e", "1", Integer.toString((int) (10 * (Math.random() * 6) - 3)), "Peut avoir des effets néfastes...", "sprites_others/syringe.png"}};
        String[][] listFoe = {{"Sbire Rocket", "sprites_foes/rocket_1.png", "20", Integer.toString((int) (Math.random() * (20 - 5 + 1)) + 5), "20"},
                {"Sbire Magma", "sprites_foes/magma_1.png", "20", Integer.toString((int) (Math.random() * (20 - 5 + 1)) + 5), "20"},
                {"Pikachu Sauvage", "sprites_foes/pikachu_1.png", "15", Integer.toString((int) (Math.random() * (25 - 10 + 1)) + 10), "15"},
                {"Rondoudou", "sprites_foes/rondoudou.png", "30", Integer.toString((int) (Math.random() * (25 - 10 + 1)) + 10), "30"}};
        Random r = new Random();
        int numero = 1;
        laby = new Piece[5 + ((int) (level - 1) / 2)][5 + ((int) (level - 1) / 2)];
        int numPorte = 1;
        for (int i = 0; i < laby.length; i++) {
            for (int j = 0; j < laby[i].length; j++) {
                laby[i][j] = new Piece(numero);
                numero++;
            }
        }
        for (int i = 0; i < laby.length; i++) {
            for (int j = 0; j < laby[i].length; j++) {
                int[] roomSize = laby[i][j].getRoomSize();
                if (i != 0) {
                    if (laby[i][j].nord == null) {
                        if (Math.random() < 0.09) {
                            laby[i][j].ajoutPorte(new Porte(numPorte, (roomSize[1] - roomSize[0]) / 2 + roomSize[0], roomSize[2]), laby[i - 1][j], "Nord");
                            numPorte++;
                        } else
                            laby[i][j].ajoutPorte(new Porte((roomSize[1] - roomSize[0]) / 2 + roomSize[0], roomSize[2]), laby[i - 1][j], "Nord");
                    }
                }
                if (i != laby.length - 1) {
                    if (laby[i][j].sud == null) {
                        if (Math.random() < 0.09) {
                            laby[i][j].ajoutPorte(new Porte(numPorte, (roomSize[1] - roomSize[0]) / 2 + roomSize[0], roomSize[3]), laby[i + 1][j], "Sud");
                            numPorte++;
                        } else
                            laby[i][j].ajoutPorte(new Porte((roomSize[1] - roomSize[0]) / 2 + roomSize[0], roomSize[3]), laby[i + 1][j], "Sud");
                    }
                }
                if (j != 0) {
                    if (laby[i][j].ouest == null) {
                        if (Math.random() < 0.09) {
                            laby[i][j].ajoutPorte(new Porte(numPorte, roomSize[0], (roomSize[3] - roomSize[2]) / 2 + roomSize[2]), laby[i][j - 1], "Ouest");
                            numPorte++;
                        } else
                            laby[i][j].ajoutPorte(new Porte(roomSize[0], (roomSize[3] - roomSize[2]) / 2 + roomSize[2]), laby[i][j - 1], "Ouest");
                    }

                }
                if (j != laby[i].length - 1) {
                    if (laby[i][j].est == null) {
                        if (Math.random() < 0.09) {
                            laby[i][j].ajoutPorte(new Porte(numPorte, roomSize[1], (roomSize[3] - roomSize[2]) / 2 + roomSize[2]), laby[i][j + 1], "Est");
                            numPorte++;
                        } else
                            laby[i][j].ajoutPorte(new Porte(roomSize[1], (roomSize[3] - roomSize[2]) / 2 + roomSize[2]), laby[i][j + 1], "Est");
                    }
                }
            }
        }
        bossRoom = new Piece(0, 150, 850, 150, 650);
        int indexPiece;
        double bossDoorSide = Math.random();
        if (bossDoorSide < 0.25) {    // porte au nord
            indexPiece = (int) (Math.random() * laby[0].length);
            int[] roomSize = laby[0][indexPiece].getRoomSize();
            laby[0][indexPiece].ajoutPorteBoss(new Porte(0, (roomSize[1] - roomSize[0]) / 2 + roomSize[0], roomSize[2]), bossRoom, "Nord");
        } else if (bossDoorSide < 0.5) {    // porte au sud
            indexPiece = (int) (Math.random() * laby[laby.length - 1].length);
            int[] roomSize = laby[laby.length - 1][indexPiece].getRoomSize();
            laby[laby.length - 1][indexPiece].ajoutPorteBoss(new Porte(0, (roomSize[1] - roomSize[0]) / 2 + roomSize[0], roomSize[3]), bossRoom, "Sud");
        } else if (bossDoorSide < 0.75) {    // porte a l'ouest
            indexPiece = (int) (Math.random() * laby.length);
            int[] roomSize = laby[indexPiece][0].getRoomSize();
            laby[indexPiece][0].ajoutPorteBoss(new Porte(0, roomSize[0], (roomSize[3] - roomSize[2]) / 2 + roomSize[2]), bossRoom, "Ouest");
        } else {    // porte a l'est
            indexPiece = (int) (Math.random() * laby.length);
            int[] roomSize = laby[indexPiece][laby.length - 1].getRoomSize();
            laby[indexPiece][laby.length - 1].ajoutPorteBoss(new Porte(0, roomSize[1], (roomSize[3] - roomSize[2]) / 2 + roomSize[2]), bossRoom, "Est");
        }
        int nbObjets = 6 + r.nextInt(6) + level;
        for (int t = 0; t < numPorte + nbObjets - 1; t++) {
            int posX = (int) (Math.random() * laby.length);
            int posY = (int) (Math.random() * laby[posX].length);
            int xInt = (int) (Math.random() * (laby[posX][posY].lon2 - laby[posX][posY].lon1)) + laby[posX][posY].lon1;
            int yInt = (int) (Math.random() * (laby[posX][posY].ht2 - laby[posX][posY].ht1)) + laby[posX][posY].ht1;
            if (t < numPorte + 1) {
                laby[posX][posY].addObjet(new Cle(t + 1, "sprites_others/key.png", xInt, yInt));
            } else {
                double type = Math.random();
                if (type < 0.5) {
                    int o = (int) (Math.random() * listNourr.length);
                    laby[posX][posY].addObjet(new Nourriture(Integer.parseInt(listNourr[o][1]), listNourr[o][0], listNourr[o][3], listNourr[o][4], Integer.parseInt(listNourr[o][2]), xInt, yInt));
                } else {
                    int o = (int) (Math.random() * listMeds.length);
                    laby[posX][posY].addObjet(new Medicament(Integer.parseInt(listMeds[o][1]), listMeds[o][0], listMeds[o][3], listMeds[o][4], Integer.parseInt(listMeds[o][2]), xInt, yInt));
                }
            }
        }
        int x = (int) (Math.random() * laby.length);
        int y = (int) (Math.random() * laby[x].length);
        int xI = (int) (Math.random() * (laby[x][y].lon2 - laby[x][y].lon1)) + laby[x][y].lon1 - 30;
        int yI = (int) (Math.random() * (laby[x][y].ht2 - laby[x][y].ht1)) + laby[x][y].ht1 - 30;
        laby[x][y].addObjet(new Cle(0, "sprites_others/bosskey.png", xI, yI));
        int nbIndiv = 10 + r.nextInt(6) + level * 2;
        for (int t = 0; t < nbIndiv; t++) {
            int posX = (int) (Math.random() * laby.length);
            int posY = (int) (Math.random() * laby[posX].length);
            int xInt = (int) (Math.random() * (laby[posX][posY].lon2 - laby[posX][posY].lon1)) + laby[posX][posY].lon1;
            int yInt = (int) (Math.random() * (laby[posX][posY].ht2 - laby[posX][posY].ht1)) + laby[posX][posY].ht1;
            double type = Math.random();
            if (type < 0.7) {
                String[] foeInfo = listFoe[((int) (Math.random() * listFoe.length))];
                laby[posX][posY].addPNJ(new Monstre(foeInfo[0], (laby[posX][posY].lon2 - laby[posX][posY].lon1) / 2 + laby[posX][posY].lon1 + (int) (Math.random() * 100),
                        (laby[posX][posY].ht2 - laby[posX][posY].ht1) / 2 + laby[posX][posY].ht1 + (int) (Math.random() * 100), Integer.parseInt(foeInfo[2]), Integer.parseInt(foeInfo[3]), Integer.parseInt(foeInfo[4]), foeInfo[1]));
            } else if (type < 0.85) {
                laby[posX][posY].addPNJ(new Medecin(xInt, yInt));
            } else {
                laby[posX][posY].addPNJ(new Cuisinier(xInt, yInt));
            }
        }
        bossRoom.addPNJ(new Monstre("Boss Rocket", 460, 400, 40 + (level * 10), 20 + (level * 5), level * 90, "sprites_foes/boss.png"));
        player = new Joueur(laby[3][3], stats[3], stats[0], stats[1], stats[4], stats[5], stats[6], stats[7]);
        level = stats[2];
        newLevel = true;
        panel.repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newLevel = false;
        panel.repaint();
    }

    public static void showRules() {
        JOptionPane.showMessageDialog(null, "Bienvenue dans le Labyrinthe !\nVotre but est de réussir à sortir vivant d'ici, les principaux dangers étant les ennemis et la fatigue..."
                + "\n\nContrôles :\n\t- ZQSD : Déplacements Haut/Gauche/Bas/Droite\n\t- Shift : Courir\n\t- P : Ouvrir une porte (peut nécessiter une clé)\n\t- Enter : Intéragir avec un PNJ"
                + "\n\t- A : Ramasser un objet" + "\n\t- E : Afficher l'inventaire" + "\n\t- Touches 123456 : Utiliser un objet de l'inventaire" + "\n\t- Echap : Pause", "Régles", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void about() {
        JOptionPane.showMessageDialog(null, "lab", "A Propos", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void startServer() {
        try {
            server = new ServerSocket(5500);
            System.out.println("Serveur initialisé !");
        } catch (IOException e) {
            System.err.println("Le port 5500 est déja utilisé ! ");
        }
        try {
            server.setSoTimeout(1);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void joinServer() {
        try {
            //socket = new Socket(ip player, 5500);
            socket = new Socket("localhost", 5500);
            System.out.println("Serveur rejoint !");
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.err.println("Impossible de rejoindre le serveur, reprise de la partie solo.");
            multi = false;
            endlessMode = true;
            newGame(1);
        }
        try {
            ObjectInputStream in = null;
            Info objetRecu = null;
            try {
                while (objetRecu == null) {
                    in = new ObjectInputStream(socket.getInputStream());
                    objetRecu = (Info) in.readObject();
                }
            } catch (ClassNotFoundException e) {
                System.err.println("Problème lors de la récuperation des données");
                e.printStackTrace();
            }
            endlessMode = true;
            newGame(1);
            laby = objetRecu.getLaby();
            otherPlayersInfo.add(objetRecu);
            int[] stats = load();
            player = new Joueur(laby[3][3], stats[3], stats[0], stats[1], stats[4], stats[5], stats[6], stats[7]);
            level = stats[2];
            ObjectOutputStream out;
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                out.flush();
                Info inf = new Info(this);
                out.writeObject(inf);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.err.println("Problème lors de la récupération des données, reprise de la partie solo.");
            multi = false;
            endlessMode = true;
            newGame(1);
        }
        panel.repaint();
    }

    public int exit() {
        try {
            int[] st = player.getStats();
            save(st[5], st[4], level, st[6], st[1], st[3], player.getDmg(), player.getArmor());
        } catch (NullPointerException e) {
        }
        if (multi) {
            try {
                if (!isSer) {
                    if (socket != null)
                        socket.close();
                } else {
                    if (server != null) server.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JFrame.EXIT_ON_CLOSE;
    }
}