import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graph extends JPanel {
    Tuile[][] tab = new Tuile[24][12];
    int victoire = 0;
    int score = 0;
    int posY = 0;

    public void paintComponent(Graphics g) {
        g.setColor(new Color(235, 235, 235));
        g.fillRect(0, posY, this.getWidth(), this.getHeight());
        if (victoire != 1) {
            for (int i = 0; i < 24; i++)
                for (int j = 0; j < 12; j++) {
                    Tuile select = tab[i][j];
                    g.setColor(select.getColor());
                    g.fillRect(i * 50, j * 50, 50, 50);
                    if (select.n != -1) {
                        g.setColor(Color.black);
                        g.setFont(new Font("Arial", Font.BOLD, 20));
                        g.drawString("" + select.n, i * 50 + 20, j * 50 + 30);
                    }
                }
            g.setColor(new Color(255, 150, 100));
            g.fillRect(0, 606, this.getWidth(), 65);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Score : " + score, 5, 650);
            if (victoire == -1) {
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                g.setColor(Color.black);
                g.setFont(new Font("Arial", Font.BOLD, 30));
                g.drawString("Il n'y a plus de", 35, 50);
                g.drawString("coups jouable ...", 68, 85);
            }
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Félicitation !", 35, 50);
            g.drawString("Vous avez gagnez", 68, 75);
        }
    }
}