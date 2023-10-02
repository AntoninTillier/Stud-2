import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouseListener implements MouseListener {
    public static boolean clic = false;
    public static int clicX = 0;
    public static int clicY = 0;

    @Override
    public void mouseClicked(MouseEvent e) {
        clic = true;
        clicX = e.getX();
        clicY = e.getY();
        System.out.println("clic !");
        System.out.println(clicX + "\n" + clicY);

    }

    public void reset() {
        clicX = 0;
        clicY = 0;
    }

    public void valide() {
        clicX = -1;
        clicY = -1;
    }
}