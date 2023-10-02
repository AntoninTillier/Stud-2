import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

public class Notifieur {

    private Font titre = new Font("Arial ", Font.PLAIN, 10);
    private Font texte = new Font("Arial ", Font.PLAIN, 30);
    private Font bouclier = new Font("Arial ", Font.BOLD, 20);
    public void paint_notification(Graphics g, int numero) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize().getSize();
        g.setColor(new Color(65, 66, 68, 30));
        g.fillRoundRect((int) d.getWidth() - 210, 50, 200, 75, 20, 20);
        if (numero == 0) {
            g.setColor(new Color(65, 66, 68));
            g.setFont(titre);
            g.drawString("maintenant", (int) d.getWidth() - 200, 65);
            g.setColor(Color.black);
            g.setFont(texte);
            g.drawString("Gain de lvl", (int) d.getWidth() - 190, 100);
        }
        if (numero == 1) {
            g.setColor(new Color(65, 66, 68));
            g.setFont(titre);
            g.drawString("maintenant", (int) d.getWidth() - 200, 65);
            g.setColor(Color.black);
            g.setFont(texte);
            g.drawString("Gain de dmg", (int) d.getWidth() - 200, 100);
        }
        if (numero == 2) {
            g.setColor(new Color(65, 66, 68));
            g.setFont(titre);
            g.drawString("maintenant", (int) d.getWidth() - 200, 65);
            g.setColor(Color.black);
            g.setFont(bouclier);
            g.drawString("Gain du bouclier", (int) d.getWidth() - 190, 100);
        }
        if (numero == 3) {
            g.setColor(new Color(65, 66, 68));
            g.setFont(titre);
            g.drawString("maintenant", (int) d.getWidth() - 200, 65);
            g.setColor(Color.black);
            g.setFont(texte);
            g.drawString("Sauvegarde", (int) d.getWidth() - 190, 100);
        }
    }
}