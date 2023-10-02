import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Menu {
	protected JTextField email = new JTextField();
	protected JPasswordField password = new JPasswordField();
	protected JButton b = new JButton("Connexion");
	protected String mail = null;
	private Font titre = new Font("Arial ", Font.PLAIN, 13);

	public static boolean verif_mail(String mail) {
		if (mail == null)
			return false;
		return Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", mail);
	}

	public void paint_menu(Graphics g) {
		if (Frame.ml.X < 20) {
			g.setColor(new Color(235, 235, 235, 50));
			g.fillRoundRect(0, 0, 30, Frame.panel.getHeight(), 20, 20);
			if (Math.pow(Frame.ml.X - 8, 2) + Math.pow(-Frame.ml.Y + 8, 2) <= 225) {
				g.setColor(Color.red);
				g.fillOval(15 - 7, 15 - 7, 15, 15);
			} else {
				g.setColor(new Color(235, 235, 235));
				g.fillOval(15 - 7, 15 - 7, 15, 15);
			}
		}
		if (!Frame.internet.netIsAvailable()) {
			g.setColor(new Color(65, 66, 68, 150));
			g.fillRoundRect(Frame.panel.getWidth() - 250, 25, 230, 45, 20, 20);
			g.setColor(new Color(235, 235, 235));
			g.setFont(titre);
			g.drawString("Vous n'êtes pas connecté à internet", Frame.panel.getWidth() - 246, 50);
		}
		email.setBackground(new Color(255, 255, 255, 100));
		password.setBackground(new Color(255, 255, 255, 100));
		email.setForeground(Color.white);
		password.setForeground(Color.white);
	}
}