import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graph extends JPanel {

	public void paintComponent(Graphics g) {
		Frame.notification.paint_notification(g, 0);
		if (Frame.map.num == -1) {
			Frame.map.paint_map(g);
		} else {
			if (Frame.map.num == -2) {
				Frame.map.paint_map(g);
				Frame.menu.paint_menu(g);
			} else {
				Frame.map.paint_map(g);
				Frame.ennemis.paint_ennemis(g);
				for (Balle b : Frame.b) {
					g.setColor(new Color(100, 255, 50));
					g.fillOval((int) (b.posBX - 10), (int) (b.posBY - 10), 20, 20);
				}
				Frame.bubule.paint_bububle(g);
			}
			if (Frame.kl.pause) {
				Frame.map.paint_map(g);
				Frame.ennemis.paint_ennemis(g);
				Frame.bubule.paint_bububle(g);
				Frame.pause.paint_pause(g);
			}
		}
	}
}