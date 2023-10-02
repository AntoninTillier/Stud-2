import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

public class Bouclier {
	protected double angle;
	Color c = Color.gray;

	@SuppressWarnings("unused")
	public void animation() {
		angle =  getAngle( Frame.ml.X, Frame.ml.Y, Frame.bubule.getBubuleX(), Frame.bubule.getBubuleY());
		for (int i = 0; i < Ennemis.ennemisr.size(); i++) {
			Rac rac = Ennemis.ennemisr.get(i);
			double x = rac.getRacX() - Frame.bubule.getBubuleX();
			double y = rac.getRacY() - Frame.bubule.getBubuleY() ;
			double d = Math.sqrt(x*x + y*y);
			double a = x / d;
			double b = y / d;
			double angle2 = Math.acos(a);
			if (b < 0)
				angle2 = - angle2;
			angle2 = 3*Math.PI/2 - angle2;
			if (d <= 115 && (angle -  angle2 <= Math.PI / 2  && angle -  angle2 >= -Math.PI / 2 )) {
				if(rac.getRacX() < Frame.bubule.getBubuleX()) {
					rac.setRacX(rac.getRacX() - 1);
				}
				else {
					rac.setRacX(rac.getRacX() + 1);
				}
				if(rac.getRacY() < Frame.bubule.getBubuleY()) {
					rac.setRacY(rac.getRacY() - 1);
				}
				else {
					rac.setRacY(rac.getRacY() + 1);
				}
			}
			for (int j = 0; j < Frame.b.size(); j++) {
				Balle ba = Frame.b.get(j);
				ArrayList<Balle> bal = Frame.b;
				double x = Frame.b.get(j).posBX - Frame.bubule.getBubuleX();
				double y = Frame.b.get(j).posBY - Frame.bubule.getBubuleY();
				double d = Math.sqrt(x*x + y*y);
				double a = x / d;
				double b = y / d;
				double angle2 = Math.acos(a);
				if (b < 0)
					angle2 = - angle2;
				angle2 = 3*Math.PI/2 - angle2;
				if (d <= 115 && (angle -  angle2 <= Math.PI / 2  && angle -  angle2 >= -Math.PI / 2 )) {
					if(Frame.b.get(j).posBX < Frame.bubule.getBubuleX()) {
						Frame.b.remove(j);
						j--;
					}
					else {
						Frame.b.remove(j);
						j--;
					}
					if(Frame.b.get(j).posBX < Frame.bubule.getBubuleY()) {
						Frame.b.remove(j);
						j--;
					}
					else {
						Frame.b.remove(j);
						j--;
					}
				}
			}
		}
	}

	public double getAngle(double mlx, double mly , double x, double y) {
		double a = mlx-x;
		double b = mly - y;
		double l = Math.sqrt(a*a+b*b);
		double angle = Math.acos(a/l);
		if (b<0)
			angle = -angle;
		return 3*Math.PI/2 - angle;
	}

	public void paint_bouclier(Graphics g) {
		if(Frame.ml.bouclier) {
			g.setColor(c);
			Graphics2D g2d = (Graphics2D) g;
			Stroke s = g2d.getStroke();
			g2d.setStroke(new BasicStroke(5));
			g.drawArc(Frame.bubule.getBubuleX() - 75, Frame.bubule.getBubuleY() - 75, 150, 150, (int) Math.toDegrees(angle), 180);
			g2d.setStroke(s);
		}
	}
}