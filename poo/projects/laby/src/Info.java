import java.io.Serializable;

public class Info implements Serializable {
	public double PlayerPos[];
	public double Running[];
	public double Slow[];
	public boolean FaceRight;
	public boolean FaceDown;
	public boolean VerticalView;
	public int SpriteLevel;
	public boolean Run;
	public Piece[][] laby;
	public Joueur player;
	public int level;
	public int numPiece;

	@SuppressWarnings("static-access")
	public Info(Frame f) {
		this.PlayerPos = f.PlayerPos;
		this.Running = f.Running;
		this.Slow = f.Slow;
		this.FaceRight = f.FaceRight;
		this.FaceDown = f.FaceDown;
		this.VerticalView = f.VerticalView;
		this.SpriteLevel = f.SpriteLevel;
		this.Run = f.kl.Run;
		this.laby = f.laby;
		this.player = f.player;
		this.level = f.level;
		this.numPiece = f.player.getPosition().getNum();
	}

	public Piece[][] getLaby() {
		return laby;
	}

	public int getLvl() {
		return level;
	}

}
