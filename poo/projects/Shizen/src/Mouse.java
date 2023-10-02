import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
    protected double x = 0;
    protected double y = 0;
    protected static boolean downer = false;

    public void mousePressed(MouseEvent e) {
        downer = true;
    }

    public void mouseReleased(MouseEvent e) {
        if (downer) {
            x = e.getX() - 3;
            y = e.getY() - 25;
        }
        downer = false;
    }

    public void reset() {
        x = 0;
        y = 0;
    }
}