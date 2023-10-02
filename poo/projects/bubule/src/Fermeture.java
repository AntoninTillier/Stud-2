public class Fermeture {

	public void update() {
		if(Frame.ml.X < 20) {
			if(Math.pow(Frame.ml.X - 8, 2) + Math.pow(- Frame.ml.Y + 8, 2) <= 225) {
				if(Frame.ml.clic) {
					System.exit(1);
				}
			}
		}
	}
}