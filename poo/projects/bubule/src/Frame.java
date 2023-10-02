import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.metal.MetalButtonUI;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	protected static Internet internet = new Internet();
	protected static Horloge time = new Horloge();
	protected static Fermeture stop = new Fermeture();
	protected static Graph panel = new Graph();
	protected static keylistener kl = new keylistener();
	protected static mouse ml = new mouse();
	protected static Menu menu = new Menu();
	protected static Animation transition = new Animation();
	protected static Pause pause = new Pause();
	protected static BoutonListener bl;
	protected static Bubule bubule;
	protected static Ennemis ennemis;
	protected static PiouPiou pioupiou;
	protected static Bouclier bouclier = new Bouclier();
	protected static Obstacle obstacle = new Obstacle();
	protected static Notifieur notification = new Notifieur();
	protected static Map map = new Map(-2);
	public static ArrayList<Balle> b = new ArrayList<Balle>();
	public static String id;


	public Frame() {
		this.setContentPane(panel);
		int h = 700;
		int l = 1270;
		this.setUndecorated(true);
		Shape sh = new RoundRectangle2D.Double(0, 0, l, h, 20, 20);
		this.setShape(sh);
		this.setSize(l, h);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addMouseListener(ml);
		this.addMouseMotionListener(ml);
		menu.email.setPreferredSize(new Dimension(180, 30));
		menu.password.setPreferredSize(new Dimension(180, 30));
		menu.b.setPreferredSize(new Dimension(180, 20));
		panel.setBorder(BorderFactory.createEmptyBorder(l / 4, 0, 0, 0));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, l, 0));
		bl = new BoutonListener();
		menu.b.addActionListener(bl);
		menu.b.setCursor(new Cursor(Cursor.HAND_CURSOR));
		menu.b.addMouseListener(ml);
		menu.b.setBackground(new Color(0, 0, 0, 235));
		menu.b.setForeground(Color.white);
		menu.b.setUI((ButtonUI) MetalButtonUI.createUI(menu.b));
		menu.email.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		menu.password.addKeyListener(kl);
		menu.password.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		panel.add(menu.email);
		panel.add(menu.password);
		panel.add(menu.b);
		this.setVisible(true);
		menu();
	}

	public Frame(int a) {
		if (a >= -1) {
			this.setContentPane(panel);
			this.setLayout(null);
			this.setUndecorated(true);
			int h = 700;
			int l = 1270;
			this.setSize(l, h);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
			this.addKeyListener(kl);
			this.addMouseListener(ml);
			this.addMouseMotionListener(ml);
			this.setVisible(true);
			map.num = -1;
			jeu();
		}
	}

	public void menu() {
		while (true) {
			panel.repaint();
			long start = System.currentTimeMillis();
			stop.update();
			if (!internet.netIsAvailable()) {
				if (bl.changement() || kl.changement()) {
					JDBC b = new JDBC();
					if(b.f) {
						id = b.ID;
						System.out.println(id);
						panel.removeAll();
						this.dispose();
						new Frame(-1);
					}
					else {
						bl.mail = false;
						bl.pass = false;
						kl.mail = false;
						kl.pass = false;
					}
				}
			}
			long fin = System.currentTimeMillis() + 15;
			try {
				Thread.sleep(fin - start);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unused")
	public void jeu() {
		bubule = new Bubule();
		ennemis = new Ennemis();
		pioupiou = new PiouPiou();
		panel.removeAll();
		while (true) {
			if (kl.hide) {
				kl.pause = true;
				GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
				this.setExtendedState(Frame.ICONIFIED);
				kl.hide = false;
			}
			if (kl.pause) {
				panel.repaint();
				if (this.getExtendedState() == 0)
					GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
							.setFullScreenWindow(this);
				pause.pause();
			} else {
				if (map.num == -1) {
					panel.repaint();
					while (transition.count < 670) {
						long start = System.currentTimeMillis();
						transition.anime();
						long fin = System.currentTimeMillis() + 15;
						try {
							Thread.sleep(fin - start);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					panel.removeAll();
					map.num = 0;
				} else {
					panel.repaint();
					long start = System.currentTimeMillis();
					bubule.deplace_Bubule();
					ennemis.ennemis();
					for (int i = 0; i < b.size(); i++) {
						Balle ba = b.get(i);
						ArrayList<Balle> bal = b;
						if (b.get(i).posBX < 0 || b.get(i).posBX > Frame.panel.getWidth() || b.get(i).posBY < 0
								|| b.get(i).posBY > Frame.panel.getHeight()) {
							b.remove(i);
							i--;
						} else if (b.get(i).posBX < bubule.BubuleX + 25 && b.get(i).posBX > bubule.BubuleX - 25
								&& b.get(i).posBY < bubule.BubuleY + 25 && b.get(i).posBY > bubule.BubuleY - 25) {
							b.remove(i);
							i--;
							bubule.Vie--;
						} else
							b.get(i).deplacement();
					}
					if(bubule.getVie() < 20) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						bubule.setVie(50);
						bubule.dead ++;
					}
					long fin = System.currentTimeMillis() + 15;
					try {
						Thread.sleep(fin-start);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}