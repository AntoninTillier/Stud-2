import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoutonListener implements ActionListener {
    protected static boolean shizen = false;
    protected static boolean rejouer = false;
    protected static String name = "";

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Jouer") {
            name = Frame.menu.pseudo.getText();
            shizen = true;
        }
        if (e.getActionCommand() == "Quitter") {
            System.exit(0);
        }
    }
}