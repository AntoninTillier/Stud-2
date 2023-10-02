import java.awt.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Horloge extends JLabel {
	private SimpleDateFormat sdf;

	public Horloge() {
		this.sdf = new SimpleDateFormat("HH:mm:ss");
		this.setText(sdf.format(new Date()));
		this.setFont(new Font("Arial", Font.PLAIN, 18));
	}

	public void run() {
		this.setText(sdf.format(new Date()));
		if(Frame.ml.x >= 20 && Frame.ml.x <= 70 && Frame.ml.Y >= 20 && Frame.ml.y <= 70) {
			if(Frame.ml.clic) {
				Frame.kl.pause = false;
				Pause.x = 0;
				Frame.panel.remove(Frame.time);
			}
		}
		try {
			Thread.sleep(1000);
		} catch(InterruptedException ie) {
			System.out.println("error");
		}
	}
}