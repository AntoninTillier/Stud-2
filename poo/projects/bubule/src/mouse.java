import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;

public class mouse implements MouseListener, MouseMotionListener {
    protected boolean clic = false;
    protected boolean tire = false;
    protected boolean bouclier = false;
    protected int X = 500;
    protected int Y = 500;
    protected int x = 0;
    protected int y = 0;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();
        if (source == Frame.menu.b) {
            Frame.menu.b.setBackground(new Color(70, 70, 70, 235));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object source = e.getSource();
        if (source == Frame.menu.b) {
            Frame.menu.b.setBackground(new Color(0, 0, 0, 235));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            clic = true;
            if (!bouclier) {
                if (!Frame.kl.pause) {
                    tire = true;
                }
            }
            x = e.getX();
            y = e.getY();
        } else if (SwingUtilities.isRightMouseButton(e)) {
            if (!Frame.kl.pause) {
                bouclier = true;
            }
            clic = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            clic = false;
            if (!Frame.kl.pause) {
                tire = false;
            }
        } else if (SwingUtilities.isRightMouseButton(e)) {
            if (!Frame.kl.pause) {
                bouclier = false;
            }
            clic = false;

        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        X = e.getX();
        Y = e.getY();
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        X = e.getX();
        Y = e.getY();
    }
}