import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

public class Ennemis {
	protected Rac rac = new Rac();
	protected static ArrayList<Rac> ennemisr = new ArrayList<Rac>();
	private int count = 0;
	protected int nbe = 1;
	protected int countDead = 1;
	protected int palier = Integer.valueOf(Bubule.bdd.lvl) - 1;

	public void ennemis() {
		int a = 0;
		count = ennemisr.size();
		if (ennemisr.size() == 0) {
			palier++;
			int rand = (int) (Math.random() * 3);
			Frame.map = new Map(rand);
		}
		if (count == 0) {
			while (a < nbe) {
				int x = 100 + (int) (Math.random() * ((1100 - 100) + 1));
				int y = 100 + (int) (Math.random() * (600 - 100) + 1);
				ennemisr.add(new Rac(x, y, (a % 2 == 0) ? false : true));
				a++;
			}
			nbe++;
		}
		for (int i = 0; i < ennemisr.size(); i++) {
			rac = ennemisr.get(i);
			if (rac.getVie() == 0) {
				ennemisr.remove(rac);
				countDead++;
				if (countDead % 5 == 0) {
					Frame.bubule.lvl++;
					try {
						new Sound(new File("music/Notification.wav")).start();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					try {
						new Sound(new File("music/GainXP.wav")).start();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			double abs = Frame.bubule.getBubuleX() - rac.RacX;
			double ord = Frame.bubule.getBubuleY() - rac.RacY;
			double l = Math.sqrt(abs * abs + ord * ord);
			if (!rac.estTireur)
				rac.deplace_Rac(true);
			else {
				if (l > 200)
					rac.deplace_Rac(true);
				else
					rac.deplace_Rac(false);
				rac.tire();
			}
		}
	}

	public void paint_ennemis(Graphics g) {
		for (int i = 0; i < ennemisr.size(); i++) {
			if (countDead % 5 == 0) {
				Frame.notification.paint_notification(g, 0);
				rac = ennemisr.get(i);
				rac.paint_rac(g);
			} else {
				rac = ennemisr.get(i);
				rac.paint_rac(g);
			}
		}
	}
}