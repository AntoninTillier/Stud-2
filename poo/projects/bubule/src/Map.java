import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Map {
	BufferedImage image;
	int num;

	public Map(int num) {
		this.num = num;
		if (num == -2) {
			try {
				image = ImageIO.read(new File("images/menu.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (num == -1) {
			new Animation();
		}
		if (num == 0) {
			try {
				image = ImageIO.read(new File("images/terre.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (num == 1) {
			try {
				image = ImageIO.read(new File("images/eau.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (num == 2) {
			try {
				image = ImageIO.read(new File("images/feu.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void paint_map(Graphics g) {
		if (num == -1) {
			Frame.transition.paint_animation(g);
		} else {
			g.drawImage(image, 0, 0, Frame.panel.getWidth(), Frame.panel.getHeight(), null);
		}
	}
}