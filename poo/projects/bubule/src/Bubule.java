import java.awt.Color;
import java.awt.Graphics;

public class Bubule {
	protected static JDBC bdd = new JDBC(Frame.id);
	public int BubuleX = 1270 / 2;
	public int BubuleY = 700 / 2;
	public double dirX = 0;
	public double dirY = 0;
	public int Vie = Integer.valueOf(50);
	public int dmg = 10;
	public int lvl = Integer.valueOf(50);
	public int dead = 0;
	public int count = 0;

	public void deplace_Bubule() {
		if (Frame.ennemis.palier % 2 == 0 && count == 0) {
			bdd.update(Frame.id, lvl, Frame.ennemis.palier, Vie);
			count = 1;
			if (count == 1 && Vie < 50)
				setVie(50);
		}
		dirX = 0;
		dirY = 0;
		if (Frame.ennemis.palier % 2 != 0)
			count = 0;
		if (Frame.kl.up) {
			Frame.obstacle.collision();
			dirY = -1;
		}
		if (Frame.kl.down) {
			Frame.obstacle.collision();
			dirY = 1;
		}
		if (Frame.kl.left) {
			Frame.obstacle.collision();
			dirX = -1;
		}
		if (Frame.kl.right) {
			Frame.obstacle.collision();
			dirX = 1;
		}
		if (dirX * dirY != 0) {
			dirX /= Math.sqrt(2);
			dirY /= Math.sqrt(2);
		}
		BubuleX += dirX*4;
		BubuleY += dirY*4;
	}

	public boolean perdu() {
		if (getVie() <= 20) {
			dead++;
			return false;
		} else
			return true;
	}

	public void degat(int Vie, int dmg) {
		setVie(Vie - dmg);
	}

	public int getVie() {
		return Vie;
	}

	public void setVie(int Vie) {
		this.Vie = Vie;
	}

	public int getdmg() {
		return dmg;
	}

	public void setdmg(int dmg) {
		this.dmg = dmg;
	}

	public int getBubuleX() {
		return BubuleX;
	}

	public void setBubuleX(int bubuleX) {
		this.BubuleX = bubuleX;
	}

	public int getBubuleY() {
		return BubuleY;
	}

	public void setBubuleY(int bubuleY) {
		this.BubuleY = bubuleY;
	}

	public void paint_bububle(Graphics g) {
		if (lvl == 10 && Frame.ennemis.countDead == 51) {
			Frame.notification.paint_notification(g, 1);
			setdmg(2);
			Frame.pioupiou.animation();
			Frame.pioupiou.paint_pioupiou(g);
			if (Frame.ml.bouclier) {
				if(lvl >= 15) {
					Frame.bouclier.animation();
					Frame.bouclier.paint_bouclier(g);
				}
			}
			g.setColor(Color.red);
			g.fillOval(getBubuleX() + ((50 - getVie()) / 2) - 25, getBubuleY() + ((50 - getVie()) / 2) - 25, getVie(),
					getVie());
		} else {
			if (!Frame.kl.pause)
				Frame.pioupiou.animation();
			Frame.pioupiou.paint_pioupiou(g);
			if (Frame.ml.bouclier) {
				if(lvl >= 15) {
					Frame.bouclier.animation();
					Frame.bouclier.paint_bouclier(g);
				}
				Frame.pioupiou.paint_pioupiou(g);
			}
			g.setColor(Color.red);
			g.fillOval(getBubuleX() + ((50 - getVie()) / 2) - 25, getBubuleY() + ((50 - getVie()) / 2) - 25, getVie(),
					getVie());
		}

	}
}