import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class BoutonListener implements ActionListener {
	ImageIcon image;
	boolean mail = false;
	boolean pass = false;

	public void actionPerformed(ActionEvent e) {
		try {
			new Sound(new File("music/CellSelect.wav")).start();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Frame.menu.mail = Frame.menu.email.getText();
		if (Menu.verif_mail(Frame.menu.mail)) {
			mail = true;
			System.out.println("mail = " + Frame.menu.mail);
		} else {
			erreur(mail, pass);
		}
		char[] Password = Frame.menu.password.getPassword();
		String mdp = new String(Password);
		if (mdp.length() >= 1) {
			pass = true;
			if (mail)
				System.out.println("password = " + mdp);
		} else {
			if (mail)
				erreur(mail, pass);
		}
	}

	public boolean changement() {
		if (mail == true && pass == true)
			return true;
		else
			return false;
	}

	public void erreur(boolean mail, boolean pass) {
		if (!mail || !pass) {
			try {
				new Sound(new File("music/Fail.wav")).start();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			image = new ImageIcon("images/nicolas.jpg");
			JOptionPane.showMessageDialog(null, "Connexion impossible", "Erreur", JOptionPane.ERROR_MESSAGE, image);
			try {
				new Sound(new File("music/CellSelect.wav")).start();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}