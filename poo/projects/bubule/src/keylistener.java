import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class keylistener implements KeyListener {
	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	public boolean pause = false;
	public boolean hide = false;
	private boolean bouton = true;
	boolean mail = false;
	boolean pass = false;

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 10 && bouton)  {
			try {
				new Sound(new File("music/CellSelect.wav")).start();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			Frame.menu.mail = Frame.menu.email.getText();
			if(Menu.verif_mail(Frame.menu.mail)) {
				mail = true;
				System.out.println("mail = " + Frame.menu.mail);
			}
			else {
				Frame.bl.erreur(mail, pass);
			}
			char[] Password = Frame.menu.password.getPassword();
			String mdp = new String(Password);
			if(mdp.length() >= 1) {
				pass = true;
				if(mail) {
					System.out.println("password = "+ mdp);
					if(changement())
						bouton = false;
				}
			}
			else {
				if(mail)
					Frame.bl.erreur(mail, pass);
			}
		}
		if(e.getKeyCode() == 72) hide = true;		//H
		if(e.getKeyCode() == 80 || e.getKeyCode() == 27) pause = true;		// P ou ESC
		if(e.getKeyCode() == 90) up = true;		// Z
		if(e.getKeyCode() == 81) left = true;		// Q
		if(e.getKeyCode() == 83) down = true;		// S
		if(e.getKeyCode() == 68) right = true;		// D
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 90) up = false;		// Z
		if(e.getKeyCode() == 81) left = false;		// Q
		if(e.getKeyCode() == 83) down = false;		// S
		if(e.getKeyCode() == 68) right = false;		// D
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public boolean changement() {
		if(mail == true && pass == true)
			return true;
		else
			return false;
	}
}