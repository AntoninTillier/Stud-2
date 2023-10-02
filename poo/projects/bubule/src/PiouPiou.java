import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Antonin Tillier
 *
 * @author Antonin Tillier
 */

public class PiouPiou {
    private int PiouPiouX;
    private int PiouPiouY;
    int bufftire = 0;
    int cadence = 30;
    double vitesse = 0.09;
    double angle = getAngle(Frame.ml.x, Frame.ml.y, Frame.bubule.getBubuleX(), Frame.bubule.getBubuleY()) + Math.PI / 2;
    protected ArrayList<PiouPiou> pioupiou = new ArrayList<PiouPiou>();

    public PiouPiou() {}

    public PiouPiou(int PiouPiouX, int PiouPiouY) {
        this.PiouPiouX = PiouPiouX;
        this.PiouPiouY = PiouPiouY;
    }

    public double getAngle(double mlx, double mly, double x, double y) {
        if (x > mlx) {
            if (y > mly) {
                return Math.atan((y - mly) / (x - mlx)) + Math.PI / 2;
            } else if (y < mly) {
                return Math.atan((x - mlx) / (mly - y));
            } else {
                return Math.PI / 2;
            }
        } else if (x < mlx) {
            if (y > mly) {
                return Math.atan((mlx - x) / (y - mly)) + Math.PI;
            } else if (y < mly) {
                return Math.atan((mly - y) / (mlx - x)) + 3 * Math.PI / 2;
            } else {
                return 3 * Math.PI / 2;
            }
        } else {
            if (y > mly) {
                return Math.PI;
            } else if (y < mly) {
                return 0;
            } else {
                return 0;
            }
        }
    }

    public void animation() {
        if (Frame.bubule.lvl == 5)
            cadence = 20;
        if (!Frame.ml.tire)
            bufftire = 0;
        if (Frame.ml.tire) {
            bufftire++;
            if (bufftire % cadence == 0) {
                pioupiou.add(new PiouPiou(Frame.bubule.getBubuleX(), Frame.bubule.getBubuleY()));
                try {
                    new Sound(new File("music/pioupiou.wav")).start();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        for (int i = 0; i < pioupiou.size(); i++) {
            PiouPiou p = pioupiou.get(i);
            if (p.PiouPiouX < 0 || p.PiouPiouX > Frame.panel.getWidth()) {
                pioupiou.remove(p);
            }
            if (p.PiouPiouY < 0 || p.PiouPiouY > Frame.panel.getHeight()) {
                pioupiou.remove(p);
            }
            for (int j = 0; j < Ennemis.ennemisr.size(); j++) {
                Rac rac = Ennemis.ennemisr.get(j);
                if (Math.pow((p.PiouPiouX - rac.getRacX()), 2)
                        + Math.pow((-p.PiouPiouY + rac.getRacY()), 2) <= (20 * rac.getVie())) {
                    rac.degat(rac.getVie(), Frame.bubule.getdmg());
                    pioupiou.remove(p);
                }
                if (Frame.kl.pause) {
                    pioupiou.remove(p);
                }
            }
        }
    }

    public void paint_pioupiou(Graphics g) {
        for (int i = 0; i < pioupiou.size(); i++) {
            PiouPiou p = pioupiou.get(i);
            g.setColor(Color.red);
            g.fillOval(p.PiouPiouX - 10, p.PiouPiouY - 10, 20, 20);
            p.PiouPiouX += (int) (vitesse * Math.toDegrees(Math.cos(p.angle)));
            p.PiouPiouY += (int) (vitesse * Math.toDegrees(Math.sin(p.angle)));

        }
    }
}