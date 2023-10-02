
public class Joueur {
	public double posJX = 100;
	public double posJY = 100;
	public double dirX=0;
	public double dirY=0;
	public int vie = 10;

	public Joueur() {}

	public void deplacement(boolean gauche,boolean haut, boolean droite, boolean bas) {
		dirX = 0;
		dirY = 0;
		if (bas)
			dirY = 1;
		if (gauche)
			dirX=-1;
		if (haut)
			dirY = -1;
		if (droite)
			dirX=1;
		if (dirX * dirY != 0) {
			dirX /= Math.sqrt(2);
			dirY /= Math.sqrt(2);
		}
		posJX += dirX*2;
		posJY += dirY*2;

	}
}