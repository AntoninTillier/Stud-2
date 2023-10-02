import java.awt.Color;
import java.awt.Graphics;

public class Rac {
	public double RacX;
	public double RacY;
	public int Vie = 50;
	public int dmg = 2;
	public int cadence = 50;
	public int c;
	public boolean estTireur = false;

	public Rac() {}

	public Rac(int RacX, int RacY, boolean t) {
		this.RacX = RacX;
		this.RacY = RacY;
		estTireur = t;
	}

	public void deplace_Rac(boolean avance) {
		double a = Frame.bubule.getBubuleX() - RacX;
		double b = Frame.bubule.getBubuleY() - RacY;
		double l = Math.sqrt(a * a + b * b);
		a = a / l;
		b = b / l;
		if (avance) {
			Frame.obstacle.collision();
			RacX += a * 2;
			RacY += b * 2;
		} else {
			Frame.obstacle.collision();
			RacX -= a * 2;
			RacY -= b * 2;
		}
	}

	public void tire() {
		if (c == cadence) {
			c = 0;
			if (Frame.bubule.dirX != 0 || Frame.bubule.dirY != 0) {
				double angle1 = Math.acos(Frame.bubule.dirX);
				if (Frame.bubule.dirY < 0)
					angle1 = -angle1;
				double angle2 = getAngle(Frame.bubule.BubuleX, Frame.bubule.BubuleY, RacX, RacY);
				double angle_predi = 2 * angle2 - angle1 + Math.PI;
				double a = RacX + Math.cos(angle_predi) * 10;
				double b = RacY + Math.sin(angle_predi) * 10;
				if (angle_predi - Math.PI < Math.PI / 2 && angle_predi - Math.PI > -Math.PI / 2)
					Frame.b.add(new Balle(RacX, RacY, a, b));
				else
					Frame.b.add(new Balle(RacX, RacY, Frame.bubule.BubuleX, Frame.bubule.BubuleY));
			} else
				Frame.b.add(new Balle(RacX, RacY, Frame.bubule.BubuleX, Frame.bubule.BubuleY));
		} else
			c++;
	}

	public double getAngle(double eX, double eY, double jX, double jY) {
		double a = jX - eX;
		double b = jY - eY;
		double l = Math.sqrt(a * a + b * b);
		double rad = Math.acos(a / l);
		if (b < 0)
			rad = -rad;
		return rad;
	}

	public void colisiondeRac(Rac rac) {
		if (Math.pow((this.getRacX() - rac.getRacX()), 2)
				+ Math.pow((-this.getRacY() + rac.getRacY()), 2) <= (this.getVie() * rac.getVie() * 15)) {
			if (this.getRacX() > rac.getRacX()) {
				this.setRacX(this.getRacX() + 1);
				rac.setRacX(rac.getRacX() - 1);
			}
			if (this.getRacX() < rac.getRacX()) {
				this.setRacX(this.getRacX() - 1);
				rac.setRacX(rac.getRacX() + 1);
			}
			if (this.getRacY() > rac.getRacY()) {
				this.setRacY(this.getRacY() + 1);
				rac.setRacX(rac.getRacX() - 1);
			}
			if (this.getRacY() < rac.getRacY()) {
				this.setRacY(this.getRacY() - 1);
				rac.setRacX(rac.getRacX() + 1);
			}
		}
	}

	public void degat(int Vie, int dmg) {
		if (Vie <= 20)
			setVie(0);
		else
			setVie(Vie - dmg);
	}

	public int getdmg() {
		return dmg;
	}

	public void setdmg(int dmg) {
		this.dmg = dmg;
	}

	public int getVie() {
		return Vie;
	}

	public void setVie(int Vie) {
		this.Vie = Vie;
	}

	public double getRacX() {
		return RacX;
	}

	public void setRacX(double RacX) {
		this.RacX = RacX;
	}

	public double getRacY() {
		return RacY;
	}

	public void setRacY(double RacY) {
		this.RacY = RacY;
	}

	protected void paint_rac(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int) (getRacX() + ((50 - getVie()) / 2) - 25), (int) (getRacY() + ((50 - getVie()) / 2) - 25), getVie(), getVie());
	}
}