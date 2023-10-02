import java.io.Serializable;

public abstract class Individu implements Serializable {
	private Piece position;
	int xI;
	int yI;
	protected String sprite;

	public Individu(int x, int y) {
		xI = x;
		yI = y;
	}

	public void deplacer() {}

	public Piece getPosition() {
		return position;
	}

	public String getSpriteName() {
		return sprite;
	}
}
