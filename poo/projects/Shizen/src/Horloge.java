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
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
    }

    public void run() {
        this.setText(sdf.format(new Date()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            System.out.println("error");
        }
    }
}