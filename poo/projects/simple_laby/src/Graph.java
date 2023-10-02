import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graph extends JPanel {

    public void paintComponent(Graphics g) {
        if (Frame.l.lab.size() > 0) {
            Frame.l.lab.get(Frame.i).paint_piece(g);
        }
        Frame.j.paint_joueur(g);
        if (Frame.kl.pause)
            Frame.pause.paint_pause(g);
    }
}