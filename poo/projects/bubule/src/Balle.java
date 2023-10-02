public class Balle {
	public double posBX;
	public double posBY;
	public double dirX;
	public double dirY;
	public double vitesse;

	public Balle(double posX, double posY, double posJX, double posJY) {
		double a = posJX - posX;
		double b = posJY - posY;
		double l = Math.sqrt(a * a + b * b);
		a = a / l;
		b = b / l;
		posBX = posX + a * 25;
		posBY = posY + b * 25;
		dirX = a;
		dirY = b;
		vitesse = 4;
	}

	public void deplacement() {
		posBX += dirX * vitesse;
		posBY += dirY * vitesse;
	}
}
