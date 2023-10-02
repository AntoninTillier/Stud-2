import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keylistener implements KeyListener {
    protected boolean up = false;
    protected boolean down = false;
    protected boolean left = false;
    protected boolean right = false;
    protected boolean recherche = false;
    protected boolean pause = false;

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 90) up = true;        // Z
        if (e.getKeyCode() == 81) left = true;        // Q
        if (e.getKeyCode() == 83) down = true;        // S
        if (e.getKeyCode() == 68) right = true;        // D
        if (e.getKeyCode() == 82) recherche = true;    //R
        if (e.getKeyCode() == 80 || e.getKeyCode() == 27) pause = true;        // P ou ESC
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 90) up = false;        // Z
        if (e.getKeyCode() == 81) left = false;        // Q
        if (e.getKeyCode() == 83) down = false;        // S
        if (e.getKeyCode() == 68) right = false;        // D
        if (e.getKeyCode() == 82) recherche = false;    //R
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}