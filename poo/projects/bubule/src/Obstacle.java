import java.io.File;

public class Obstacle {
    public void collision() {
        for (int i = 0; i < Ennemis.ennemisr.size(); i++) {
            Rac rac = Ennemis.ennemisr.get(i);
            if (rac.getRacX() < 25)
                rac.setRacX(rac.getRacX() + 1);
            if (rac.getRacX() > Frame.panel.getWidth() - 25)
                rac.setRacX(rac.getRacX() - 1);
            if (rac.getRacY() < 25)
                rac.setRacY(rac.getRacY() + 1);
            if (rac.getRacY() > Frame.panel.getHeight() - 25)
                rac.setRacY(rac.getRacY() - 1);
        }
        if (Frame.bubule.getBubuleY() < 25) {
            Frame.bubule.setBubuleY(Frame.bubule.getBubuleY() + 3);
        }
        if (Frame.bubule.getBubuleY() > Frame.panel.getHeight() - 25) {
            Frame.bubule.setBubuleY(Frame.bubule.getBubuleY() - 3);
        }
        if (Frame.bubule.getBubuleX() < 25) {
            Frame.bubule.setBubuleX(Frame.bubule.getBubuleX() + 3);
        }
        if (Frame.bubule.getBubuleX() > Frame.panel.getWidth() - 25) {
            Frame.bubule.setBubuleX(Frame.bubule.getBubuleX() - 3);
        }
        contactEnnemis();
    }

    public void contactEnnemis() {
        for (int i = 0; i < Ennemis.ennemisr.size(); i++) {
            Rac rac = Ennemis.ennemisr.get(i);
            if (Ennemis.ennemisr.size() >= 2 && !Frame.ml.bouclier) {
                for (int j = 0; j < Ennemis.ennemisr.size() - 1; j++) {
                    Rac rac2 = Ennemis.ennemisr.get(j + 1);
                    rac.colisiondeRac(rac2);
                }
            }
            if (Math.pow((Frame.bubule.getBubuleX() - rac.getRacX()), 2) + Math.pow((-Frame.bubule.getBubuleY() + rac.getRacY()), 2) <= (rac.getVie() * rac.getVie())) {
                try {
                    new Sound(new File("music/collision.wav")).start();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (Frame.bubule.getBubuleX() > rac.getRacX()) {
                    Frame.bubule.degat(Frame.bubule.getVie(), rac.getdmg());
                    Frame.bubule.setBubuleX(Frame.bubule.getBubuleX() + 6);
                }
                if (Frame.bubule.getBubuleX() < rac.getRacX()) {
                    Frame.bubule.degat(Frame.bubule.getVie(), rac.getdmg());
                    Frame.bubule.setBubuleX(Frame.bubule.getBubuleX() - 6);
                }
                if (Frame.bubule.getBubuleY() > rac.getRacY()) {
                    Frame.bubule.degat(Frame.bubule.getVie(), rac.getdmg());
                    Frame.bubule.setBubuleY(Frame.bubule.getBubuleY() + 6);
                }
                if (Frame.bubule.getBubuleY() < rac.getRacY()) {
                    Frame.bubule.degat(Frame.bubule.getVie(), rac.getdmg());
                    Frame.bubule.setBubuleY(Frame.bubule.getBubuleY() - 6);
                }
            }
        }
    }
}