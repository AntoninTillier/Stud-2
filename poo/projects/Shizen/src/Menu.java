import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Menu {
    String s = Frame.load();
    protected JTextField pseudo = new JTextField();
    protected JLabel save = new JLabel(s);
    protected JLabel ancien = new JLabel("Ancien Score : ");
    protected JLabel titre = new JLabel("Bienvenu dans Shizen");
    protected JButton b = new JButton("Jouer");
    protected JButton q = new JButton("Quitter");

    public Menu() {
        pseudo.setBackground(new Color(255, 255, 255, 100));
        pseudo.setForeground(Color.white);
    }
}