import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.AttributedCharacterIterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Graph extends JPanel {
	public static Font f;
	public static Font menuFont;

	public static void initFont() {
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, new File("digifit.ttf"));
		} catch (FontFormatException e) {
			f = new Font("Arial", Font.PLAIN, 20);
		} catch (IOException e) {
			f = new Font("Arial", Font.PLAIN, 20);
		}
		try {
			menuFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/ARCADECLASSIC.TTF"));
		} catch (FontFormatException e) {
			System.out.println("pas bon1");
			menuFont = new Font("Arial", Font.PLAIN, 20);
		} catch (IOException e) {
			System.out.println("pas bon2");
			menuFont = new Font("Arial", Font.PLAIN, 20);
		}

	}

	public void paintComponent(Graphics g) {
		if (Frame.isInMenu) {
			try {
				Image welcome = ImageIO.read(new File("menu_back.jpg"));
				g.drawImage(welcome, 0, 0, 1000, 800, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.setColor(new Color(206, 19, 47));
			g.fillRect(200, 250, 600, 100);        // Nouveau jeu solo
			g.fillRect(200, 360, 600, 100);        // Nouveau jeu multi
			g.fillRect(200, 500, 600, 100);        // Quitter
			g.fillRect(200, 610, 290, 70);        // Régles
			g.fillRect(510, 610, 290, 70);        // A propos
			g.setFont(menuFont.deriveFont(Font.PLAIN, 70));
			g.drawString("LABYRINTHE", 300, 150);
			g.setFont(menuFont.deriveFont(Font.PLAIN, 55));
			g.setColor(Color.BLACK);
			g.drawString("Solo", 440, 320);
			g.setFont(menuFont.deriveFont(Font.PLAIN, 50));
			g.drawString("Multijoueur", 345, 425);
			g.drawString("Quitter", 403, 570);
			g.setFont(menuFont.deriveFont(Font.PLAIN, 30));
			g.drawString("Regles du jeu", 250, 655);
			g.drawString("A propos", 590, 655);
		} else if (Frame.isInMultiSel) {
			try {
				Image welcome = ImageIO.read(new File("menu_back.jpg"));
				g.drawImage(welcome, 0, 0, 1000, 800, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.setColor(new Color(206, 19, 47));
			g.fillRect(170, 300, 320, 100);        // creer
			g.fillRect(510, 300, 320, 100);        // rejoindre
			g.setFont(menuFont.deriveFont(Font.PLAIN, 50));
			g.drawString("PARTIE  MULTIJOUEUR", 250, 175);
			g.fillRect(380, 225, 240, 10);
			g.setColor(Color.BLACK);
			g.setFont(menuFont.deriveFont(Font.PLAIN, 34));
			g.drawString("Creer  serveur", 210, 360);    // 60+
			g.drawString("Rejoindre  partie", 525, 360);
		} else if (Frame.isInModeSel) {
			try {
				Image welcome = ImageIO.read(new File("menu_back.jpg"));
				g.drawImage(welcome, 0, 0, 1000, 800, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.setColor(new Color(206, 19, 47));
			g.fillRect(170, 275, 200, 100);        // Easy
			g.fillRect(400, 275, 200, 100);        // Normal
			g.fillRect(630, 275, 200, 100);        // Hard
			g.fillRect(170, 500, 320, 100);        // Nouvelle partie
			g.fillRect(510, 500, 320, 100);        // Continuer
			g.setFont(menuFont.deriveFont(Font.PLAIN, 50));
			g.drawString("SELECTION  DU  MODE  DE  JEU", 200, 150);
			g.fillRect(380, 200, 240, 10);
			g.setFont(menuFont.deriveFont(Font.PLAIN, 40));
			g.drawString("Mode Classique", 355, 265);
			g.drawString("Mode Infini", 385, 490);
			g.setFont(menuFont.deriveFont(Font.PLAIN, 30));
			g.setColor(Color.BLACK);
			g.drawString("Easy", 235, 335);
			g.drawString("Normal", 455, 335);
			g.drawString("Hard", 695, 335);
			g.setFont(menuFont.deriveFont(Font.PLAIN, 34));
			g.drawString("Nouvelle partie", 195, 560);
			g.drawString("Continuer", 585, 560);
		} else {        // SI PAS MENU
			if (Frame.player.getPosition().getType()) {
				try {
					Image background = ImageIO.read(new File("boss_ground.gif"));
					g.drawImage(background, 0, 0, 1000, 800, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Image background = ImageIO.read(new File("back2.gif"));
					g.drawImage(background, 0, 0, 1000, 1000, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Piece tempRoom = Frame.player.getPosition();
			int roomSize[] = tempRoom.getRoomSize();
			g.setColor(Color.BLACK);        // MATERIALISATION DES BORDURES
			g.fillRect(roomSize[1], 0, getSize().width - (roomSize[1]), getSize().height);
			g.fillRect(0, 0, roomSize[0], getSize().height);
			g.fillRect(0, roomSize[3], getSize().width, getSize().height - (roomSize[3]));
			g.fillRect(0, 0, getSize().width, roomSize[2]);
			if (tempRoom.ouest != null) {
				g.setColor(new Color(115, 49, 49));
				g.fillRect(tempRoom.ouest.x - 20, tempRoom.ouest.y - 50, 20, 100);
				if (tempRoom.ouest.getFerme()) {
					if (tempRoom.ouest.getNumero() == 0) {            // A METTRE POUR TOUS
						g.setColor(new Color(167, 14, 145));
						g.fillRect(tempRoom.ouest.x - 20, tempRoom.ouest.y - 50, 20, 100);
					}
					g.setColor(Color.WHITE);
					g.setFont(f.deriveFont(Font.PLAIN, 20));
					g.drawString(Integer.toString(tempRoom.ouest.getNumero()), tempRoom.ouest.x - 15, tempRoom.ouest.y + 10);
				}
			}
			if (tempRoom.est != null) {
				g.setColor(new Color(115, 49, 49));
				g.fillRect(tempRoom.est.x, tempRoom.est.y - 50, 20, 100);
				if (tempRoom.est.getFerme()) {
					if (tempRoom.est.getNumero() == 0) {            // A METTRE POUR TOUS
						g.setColor(new Color(167, 14, 145));
						g.fillRect(tempRoom.est.x, tempRoom.est.y - 50, 20, 100);
					}
					g.setColor(Color.WHITE);
					g.setFont(f.deriveFont(Font.PLAIN, 20));
					g.drawString(Integer.toString(tempRoom.est.getNumero()), tempRoom.est.x + 5, tempRoom.est.y + 8);
				}
			}
			if (tempRoom.sud != null) {
				g.setColor(new Color(115, 49, 49));
				g.fillRect(tempRoom.sud.x - 50, tempRoom.sud.y, 100, 20);
				if (tempRoom.sud.getFerme()) {
					if (tempRoom.sud.getNumero() == 0) {            // A METTRE POUR TOUS
						g.setColor(new Color(167, 14, 145));
						g.fillRect(tempRoom.sud.x - 50, tempRoom.sud.y, 100, 20);
					}
					g.setColor(Color.WHITE);
					g.setFont(f.deriveFont(Font.PLAIN, 20));
					g.drawString(Integer.toString(tempRoom.sud.getNumero()), tempRoom.sud.x - 5, tempRoom.sud.y + 17);
				}
			}
			if (tempRoom.nord != null) {
				if (Frame.player.getPosition().getType()) {
					g.setColor(Color.WHITE);
				} else {
					g.setColor(new Color(115, 49, 49));
				}
				g.fillRect(tempRoom.nord.x - 50, tempRoom.nord.y - 20, 100, 20);
				if (tempRoom.nord.getFerme()) {
					if (tempRoom.nord.getNumero() == 0) {            // A METTRE POUR TOUS
						g.setColor(new Color(167, 14, 145));
						g.fillRect(tempRoom.nord.x - 50, tempRoom.nord.y - 20, 100, 20);
					}
					g.setColor(Color.WHITE);
					g.setFont(f.deriveFont(Font.PLAIN, 20));
					g.drawString(Integer.toString(tempRoom.nord.getNumero()), tempRoom.nord.x - 5, tempRoom.nord.y - 2);
				}
			}
			if (tempRoom.nbObjets() > 0) {
				for (int k = 0; k < tempRoom.nbObjets(); k++) {
					Objet ob = tempRoom.getObjet(k);
					if (ob.getClass().getName().equals("Cle")) {
						try {
							Image item = ImageIO.read(new File(ob.getSprName()));
							g.drawImage(item, ob.x, ob.y, 40, 40, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						g.setColor(Color.WHITE);
						g.setFont(f.deriveFont(Font.PLAIN, 20));
						g.drawString(Integer.toString(ob.getNumero()), ob.x + 30, ob.y + 38);
					} else if (ob.getClass().getName().equals("Nourriture")) {
						try {
							Image item = ImageIO.read(new File(ob.getSprName()));
							g.drawImage(item, ob.x, ob.y, 40, 40, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (ob.getClass().getName().equals("Medicament")) {
						try {
							Image item = ImageIO.read(new File(ob.getSprName()));
							g.drawImage(item, ob.x, ob.y, 40, 40, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			if (Frame.player.nbObjets() > 0) {
				int nbCles = 0;
				for (int i = 0; i < Frame.player.nbObjets(); i++) {
					Objet ob = Frame.player.getObjet(i);
					if (ob.getClass().getName().equals("Cle")) {
						try {
							Image item = ImageIO.read(new File(ob.getSprName()));
							g.drawImage(item, this.getWidth() - 50 - (nbCles * 55), this.getHeight() - 120, 40, 40, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						g.setColor(Color.RED);
						g.setFont(f.deriveFont(Font.PLAIN, 20));
						g.drawString(Integer.toString(ob.getNumero()), this.getWidth() - 22 - (nbCles * 55), this.getHeight() - 82);
						nbCles++;
					} else if (ob.getClass().getName().equals("Nourriture")) {
						try {
							Image item = ImageIO.read(new File(ob.getSprName()));
							g.drawImage(item, this.getWidth() - 50 - ((i - nbCles) * 55), this.getHeight() - 50, 40, 40, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						g.setColor(new Color(255, 255, 255, 200));
						g.setFont(f.deriveFont(Font.PLAIN, 13));
						g.drawString(Integer.toString((i - nbCles) + 1), this.getWidth() - 35 - ((i - nbCles) * 55), this.getHeight() - 55);
					} else if (ob.getClass().getName().equals("Medicament")) {
						try {
							Image item = ImageIO.read(new File(ob.getSprName()));
							g.drawImage(item, this.getWidth() - 50 - ((i - nbCles) * 55), this.getHeight() - 50, 40, 40, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						g.setColor(new Color(255, 255, 255, 200));
						g.setFont(f.deriveFont(Font.PLAIN, 13));
						g.drawString(Integer.toString((i - nbCles) + 1), this.getWidth() - 35 - ((i - nbCles) * 55), this.getHeight() - 55);
					}
				}
			}
			if (Frame.player.getPosition().nbPNJ() > 0) {
				for (int i = 0; i < Frame.player.getPosition().nbPNJ(); i++) {
					Individu pnj = Frame.player.getPosition().getPNJ(i);
					if (pnj.getClass().getName().equals("Monstre")) {
						pnj = (Monstre) pnj;
						try {
							Image ind = ImageIO.read(new File(pnj.getSpriteName()));
							g.drawImage(ind, pnj.xI, pnj.yI, 42, 56, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						g.setColor(Color.BLACK);
						g.drawRect(pnj.xI - 13, pnj.yI - 20, 70, 8);
						g.setColor(Color.RED);
						g.fillRect(pnj.xI - 12, pnj.yI - 19, (int) ((((double) ((Monstre) pnj).getLife()) / ((double) ((Monstre) pnj).getOriginLife())) * 70) - 1, 7);
						g.setFont(f.deriveFont(Font.PLAIN, 16));
						g.drawString(((Monstre) pnj).getName(), pnj.xI - ((Monstre) pnj).getName().length() * 2, pnj.yI - 25);
						g.setColor(Color.BLACK);
						g.setFont(f.deriveFont(Font.PLAIN, 8));
						g.drawString(Integer.toString(((Monstre) pnj).getLife()), pnj.xI + 17, pnj.yI - 13);
					}
					if (pnj.getClass().getName().equals("Medecin")) {
						try {
							Image ind = ImageIO.read(new File("sprites_others/doc_1.png"));
							g.drawImage(ind, pnj.xI, pnj.yI, 45, 60, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (pnj.getClass().getName().equals("Cuisinier")) {
						try {
							Image ind = ImageIO.read(new File("sprites_others/cuistot_1.png"));
							g.drawImage(ind, pnj.xI, pnj.yI, 45, 60, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			if (Frame.player.Attacking()) {
				try {
					Image ind = ImageIO.read(new File("sprites_others/atk.png"));
					g.drawImage(ind, Frame.player.xAtk, Frame.player.yAtk, 30, 30, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (Frame.newLevel) {
				g.setColor(new Color(0, 0, 0, 200));
				g.fillRect(0, 0, getSize().width, getSize().height);
				g.setColor(new Color(255, 255, 255, 125));
				g.setFont(menuFont.deriveFont(Font.PLAIN, 150));
				g.drawString("LEVEL " + Integer.toString(Frame.level), 250, 400);
			}
			if (Frame.player.LevelUp) {
				g.setColor(new Color(0, 0, 0, 200));
				g.fillRect(0, 0, getSize().width, getSize().height);
				g.setColor(new Color(255, 255, 255, 125));
				g.setFont(menuFont.deriveFont(Font.PLAIN, 150));
				g.drawString("LEVEL UP !", 160, 300);
				g.drawRect(285, 390, 100, 100);
				g.drawRect(284, 389, 102, 102);
				g.drawRect(435, 390, 100, 100);
				g.drawRect(434, 389, 102, 102);
				g.drawRect(585, 390, 100, 100);
				g.drawRect(584, 389, 102, 102);
				g.drawRect(360, 540, 100, 100);
				g.drawRect(359, 539, 102, 102);
				g.drawRect(510, 540, 100, 100);
				g.drawRect(509, 539, 102, 102);
				g.setFont(menuFont.deriveFont(Font.PLAIN, 50));
				g.drawString("CHOISISSEZ  UNE  STAT  A  AMELIORER", 90, 360);
				g.setFont(menuFont.deriveFont(Font.PLAIN, 28));
				g.drawString("SANTE", 300, 440);
				g.drawString("MAX", 315, 460);
				g.drawString("POIDS", 600, 440);
				g.drawString("MAX", 615, 460);
				g.setFont(menuFont.deriveFont(Font.PLAIN, 18));
				g.drawString("ENDURANCE", 442, 440);
				g.drawString("MAX", 470, 455);
				g.setFont(menuFont.deriveFont(Font.PLAIN, 25));
				g.drawString("DEGATS", 370, 600);
				g.drawString("ARMURE", 520, 600);
			}
			if (Frame.VerticalView) {
				if (Frame.FaceDown) {
					if (Frame.SpriteLevel == 0 | Frame.SpriteLevel == 2) {
						try {
							if (Frame.kl.Run) {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hdown_r.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 45, 60, this);
							} else {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hdown.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (Frame.SpriteLevel == 1) {
						try {
							if (Frame.kl.Run) {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hdown_r1.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 45, 60, this);
							} else {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hdown1.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (Frame.SpriteLevel == 3) {
						try {
							if (Frame.kl.Run) {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hdown_r2.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 45, 60, this);
							} else {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hdown2.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					if (Frame.SpriteLevel == 0 | Frame.SpriteLevel == 2) {
						try {
							if (Frame.kl.Run) {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hup_r.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							} else {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hup.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (Frame.SpriteLevel == 1) {
						try {
							if (Frame.kl.Run) {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hup_r1.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							} else {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hup1.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (Frame.SpriteLevel == 3) {
						try {
							if (Frame.kl.Run) {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hup_r2.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							} else {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hup2.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				if (Frame.FaceRight) {
					if (Frame.SpriteLevel == 0 | Frame.SpriteLevel == 2) {
						try {
							if (Frame.kl.Run) {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hright_r.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							} else {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hright.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (Frame.SpriteLevel == 1) {
						try {
							if (Frame.kl.Run) {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hright_r1.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							} else {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hright1.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (Frame.SpriteLevel == 3) {
						try {
							if (Frame.kl.Run) {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hright_r2.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							} else {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hright2.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					if (Frame.SpriteLevel == 0 | Frame.SpriteLevel == 2) {
						try {
							if (Frame.kl.Run) {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hleft_r.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							} else {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hleft.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (Frame.SpriteLevel == 1) {
						try {
							if (Frame.kl.Run) {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hleft_r1.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							} else {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hleft1.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (Frame.SpriteLevel == 3) {
						try {
							if (Frame.kl.Run) {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hleft_r2.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							} else {
								Image player_skin = ImageIO.read(new File("sprites_joueur/player_hleft2.png"));
								g.drawImage(player_skin, (int) (Frame.PlayerPos[0]), (int) (Frame.PlayerPos[1] - 13), 42, 63, this);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			if (Frame.multi) {
				g.setColor(Color.WHITE);
				g.setFont(menuFont.deriveFont(Font.PLAIN, 20));
				try {
					String ad = InetAddress.getLocalHost().getHostAddress().replace('.', ' ');
					g.drawString(ad, getWidth() - 150, 40);
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				}
				for (int i = 0; i < Frame.otherPlayersInfo.size(); i++) {
					Info inf = Frame.otherPlayersInfo.get(i);
					if (inf.numPiece == Frame.player.getPosition().getNum()) {
						if (inf.VerticalView) {
							if (inf.FaceDown) {
								if (inf.SpriteLevel == 0 | inf.SpriteLevel == 2) {
									try {
										if (inf.Run) {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hdown_r.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 45, 60, this);
										} else {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hdown.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								if (inf.SpriteLevel == 1) {
									try {
										if (inf.Run) {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hdown_r1.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 45, 60, this);
										} else {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hdown1.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								if (inf.SpriteLevel == 3) {
									try {
										if (inf.Run) {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hdown_r2.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 45, 60, this);
										} else {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hdown2.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							} else {
								if (inf.SpriteLevel == 0 | inf.SpriteLevel == 2) {
									try {
										if (inf.Run) {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hup_r.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										} else {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hup.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								if (inf.SpriteLevel == 1) {
									try {
										if (inf.Run) {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hup_r1.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										} else {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hup1.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								if (inf.SpriteLevel == 3) {
									try {
										if (inf.Run) {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hup_r2.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										} else {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hup2.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
						} else {
							if (inf.FaceRight) {
								if (inf.SpriteLevel == 0 | inf.SpriteLevel == 2) {
									try {
										if (inf.Run) {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hright_r.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										} else {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hright.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								if (inf.SpriteLevel == 1) {
									try {
										if (inf.Run) {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hright_r1.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										} else {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hright1.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								if (inf.SpriteLevel == 3) {
									try {
										if (inf.Run) {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hright_r2.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										} else {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hright2.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							} else {
								if (inf.SpriteLevel == 0 | inf.SpriteLevel == 2) {
									try {
										if (inf.Run) {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hleft_r.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										} else {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hleft.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								if (inf.SpriteLevel == 1) {
									try {
										if (inf.Run) {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hleft_r1.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										} else {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hleft1.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								if (inf.SpriteLevel == 3) {
									try {
										if (inf.Run) {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hleft_r2.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										} else {
											Image player_skin = ImageIO.read(new File("sprites_joueur/player_hleft2.png"));
											g.drawImage(player_skin, (int) (inf.PlayerPos[0]), (int) (inf.PlayerPos[1] - 13), 42, 63, this);
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
						}
						g.setColor(Color.BLACK);
						g.drawRect((int) inf.PlayerPos[0] - 12, (int) inf.PlayerPos[1] - 23, 70, 8);
						g.setColor(Color.RED);
						g.fillRect((int) inf.PlayerPos[0] - 11, (int) inf.PlayerPos[1] - 22, (int) ((double) (inf.player.getVie()) / ((double) (inf.player.getVieMax())) * 70) - 1, 7);
						g.setColor(Color.BLACK);
						g.setFont(f.deriveFont(Font.PLAIN, 8));
						g.drawString(Integer.toString(inf.player.getVie()), (int) inf.PlayerPos[0] + 15, (int) inf.PlayerPos[1] - 16);
						if (inf.player.Attacking()) {
							try {
								Image ind = ImageIO.read(new File("sprites_others/atk.png"));
								g.drawImage(ind, inf.player.xAtk, inf.player.yAtk, 30, 30, this);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			if (!Frame.End) {
				int[] stats = Frame.player.getStats();
				g.setFont(f.deriveFont(Font.PLAIN, 14));
				g.setColor(new Color(255, 15, 15));            // BARRE DE VIE
				g.fillRect(20, getSize().height - 40, (int) (((double) stats[0] / (double) stats[1]) * 400), 20);
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(20, getSize().height - 40, 400, 20);
				g.setColor(new Color(0, 0, 0));
				g.drawRect(20, getSize().height - 40, 400, 20);
				g.drawString(Integer.toString(stats[0]) + " / " + Integer.toString(stats[1]), 195, getSize().height - 25);
				g.setColor(Color.YELLOW);            // BARRE DE FORCE
				g.fillRect(20, getSize().height - 70, (int) (((double) stats[2] / (double) stats[3]) * 400), 20);
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(20, getSize().height - 70, 400, 20);
				g.setColor(new Color(0, 0, 0));
				g.drawRect(20, getSize().height - 70, 400, 20);
				g.drawString(Integer.toString(stats[2]) + " / " + Integer.toString(stats[3]), 195, getSize().height - 55);
				g.setColor(Color.CYAN);            // BARRE D'EXP
				g.fillRect(200, 30, (int) (((double) stats[4] / (double) (stats[5] * 100)) * 600), 15);
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(200, 30, 600, 15);
				g.setColor(new Color(0, 0, 0));
				g.drawRect(200, 30, 600, 15);
				g.setFont(menuFont.deriveFont(Font.PLAIN, 15));
				g.drawString("Niveau  " + stats[5], 470, 43);
				if (Frame.kl.Pause) {
					g.setColor(new Color(0, 0, 0, 200));
					g.fillRect(0, 0, getSize().width, getSize().height);
					g.setColor(new Color(255, 255, 255, 125));
					g.setFont(menuFont.deriveFont(Font.PLAIN, 100));
					g.drawString("PAUSE", 350, 150);
					g.setFont(f.deriveFont(Font.PLAIN, 30));
					g.drawRect(80, 170, getWidth() - 160, 80);
					g.drawString("Reprendre", 410, 220);
					g.drawRect(80, 300, getWidth() - 160, 80);
					g.drawString("Rejouer", 430, 350);
					g.drawRect(80, 430, getWidth() - 160, 80);
					g.drawString("Relire les r�gles", 370, 480);
					g.drawRect(80, 560, getWidth() - 160, 80);
					g.drawString("Quitter le jeu", 392, 610);
				} else if (Frame.kl.Inventory) {
					g.setColor(new Color(0, 0, 0, 200));
					g.fillRect(0, 0, getSize().width, getSize().height);
					for (int i = 0; i < Frame.player.nbObjets(); i++) {
						g.setColor(Color.WHITE);
						g.drawRect(100 + (i * 95), 50, 80, 80);
						Objet ob = Frame.player.getObjet(i);
						if (ob.getClass().getName().equals("Cle")) {
							try {
								Image item = ImageIO.read(new File(ob.getSprName()));
								g.drawImage(item, 105 + (i * 95), 55, 70, 70, this);
							} catch (IOException e) {
								e.printStackTrace();
							}
							g.setColor(Color.RED);
							g.setFont(f.deriveFont(Font.PLAIN, 30));
							g.drawString(Integer.toString(ob.getNumero()), 155 + (i * 95), 120);
						} else if (ob.getClass().getName().equals("Nourriture")) {
							try {
								Image item = ImageIO.read(new File(ob.getSprName()));
								g.drawImage(item, 105 + (i * 95), 55, 70, 70, this);
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else if (ob.getClass().getName().equals("Medicament")) {
							try {
								Image item = ImageIO.read(new File(ob.getSprName()));
								g.drawImage(item, 105 + (i * 95), 55, 70, 70, this);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					if (Frame.player.getObjAff() != -1) {
						Objet ob = Frame.player.getObjet(Frame.player.getObjAff());
						g.setColor(Color.WHITE);
						g.setFont(f.deriveFont(Font.PLAIN, 40));
						g.drawString(ob.getNom() + " (Poids : " + Integer.toString(ob.getPoids()) + ")", 20, 200);
						g.setFont(f.deriveFont(Font.PLAIN, 30));
						g.drawString(ob.getDesc(), 20, 250);
						if (ob.getClass().getName().equals("Nourriture")) {
							g.drawString("Manger ceci vous redonnera " + ((Nourriture) ob).getForce() + " PS", 20, 290);
						}
						if (ob.getClass().getName().equals("Medicament")) {
							g.drawString("Utiliser ceci vous redonnera " + ((Medicament) ob).getVie() + " PV", 20, 290);
						}
						g.drawRect(100, getHeight() - 150, 250, 50);
						g.drawRect(99, getHeight() - 151, 252, 52);
						g.setFont(f.deriveFont(Font.PLAIN, 20));
						g.drawString("Lacher l'objet", 165, getHeight() - 117);
					}
					g.setColor(Color.RED);            // BARRE DE DEGATS
					g.fillRect(600, 600, (int) (((double) Frame.player.getDmg() / 50) * 300), 15);
					g.setColor(new Color(255, 255, 255, 100));
					g.fillRect(600, 600, 300, 15);
					g.setColor(new Color(0, 0, 0));
					g.drawRect(600, 600, 300, 15);
					g.setFont(menuFont.deriveFont(Font.PLAIN, 15));
					g.drawString(Integer.toString(Frame.player.getDmg()), 760, 613);
					try {
						Image dmg = ImageIO.read(new File("sprites_others/dmg.png"));
						g.drawImage(dmg, 575, 580, 50, 50, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
					g.setColor(Color.BLUE);            // BARRE D'ARMURE
					g.fillRect(600, 650, (int) (((double) Frame.player.getArmor() / 20) * 300), 15);
					g.setColor(new Color(255, 255, 255, 100));
					g.fillRect(600, 650, 300, 15);
					g.setColor(new Color(0, 0, 0));
					g.drawRect(600, 650, 300, 15);
					g.setFont(menuFont.deriveFont(Font.PLAIN, 15));
					g.drawString(Integer.toString(Frame.player.getArmor()), 760, 663);
					try {
						Image dmg = ImageIO.read(new File("sprites_others/armor.png"));
						g.drawImage(dmg, 575, 630, 50, 50, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
					g.setColor(Color.YELLOW);            // BARRE DE POIDS
					g.fillRect(600, 700, (int) (((double) Frame.player.getPoidsPorte() / Frame.player.getPoidsMax()) * 300), 15);
					g.setColor(new Color(255, 255, 255, 100));
					g.fillRect(600, 700, 300, 15);
					g.setColor(new Color(0, 0, 0));
					g.drawRect(600, 700, 300, 15);
					g.setFont(menuFont.deriveFont(Font.PLAIN, 15));
					g.drawString(Integer.toString(Frame.player.getPoidsPorte()), 760, 713);
					try {
						Image dmg = ImageIO.read(new File("sprites_others/bag.png"));
						g.drawImage(dmg, 575, 683, 50, 50, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			if (Frame.End) {
				g.setColor(new Color(0, 0, 0, 200));
				g.fillRect(0, 0, getSize().width, getSize().height);
				g.setColor(new Color(255, 255, 255, 125));
				g.setFont(f.deriveFont(Font.PLAIN, 100));
				int Minuts = (int) ((double) (Frame.Time - Frame.TimeF) / 60000);
				int Seconds = (int) ((double) (Frame.Time - Frame.TimeF - Minuts * 60000) / 1000);
				int Milli = Frame.Time - Frame.TimeF - Minuts * 60000 - Seconds * 1000;
				if (Seconds < 10) {
					if (Milli < 10) g.drawString(Minuts + ":0" + Seconds + ".00" + Milli + " s", 40, 100);
					else if (Milli < 100) g.drawString(Minuts + ":0" + Seconds + ".0" + Milli + " s", 40, 100);
					else g.drawString(Minuts + ":" + Seconds + "." + Milli + " s", 40, 100);
				} else {
					if (Milli < 10) g.drawString(Minuts + ":" + Seconds + ".00" + Milli + " s", 40, 100);
					else if (Milli < 100) g.drawString(Minuts + ":" + Seconds + ".0" + Milli + " s", 40, 100);
					else g.drawString(Minuts + ":" + Seconds + "." + Milli + " s", 40, 100);
				}
				g.fillRect(0, 140, getSize().width, 20);
				g.setFont(f.deriveFont(Font.PLAIN, 40));
				String line;
				if (Frame.LoseCause == null) {
					String[] fin = {"Bien jou� vous avez trouv� la sortie !", "Vous avez retrouv� la lumi�re du jour !"};
					line = fin[(int) (Math.random() * fin.length)];
				} else if (Frame.LoseCause.equals("Force")) {
					String[] fin = {"Vos poumons sont morts", "Arrete de fumer encul� !", "A bout de forces, vous succombez...", "Vous crachez vos poumons, litt�ralement"};
					line = fin[(int) (Math.random() * fin.length)];
				} else {
					String[] fin = {"WASTED", "Vous �tes mort.", "Le monstre vous capture et abuse de votre cadavre...", "M�me la vie vous friendzone...",
							"Vous voyez une lumi�re, mais ce n'est pas la sortie...", "U DON'T KNO DA WEY"};
					line = fin[(int) (Math.random() * fin.length)];
				}
				g.drawString(line, 20, 210);
				g.setFont(f.deriveFont(Font.PLAIN, 30));
				g.drawRect(80, 300, getWidth() - 160, 80);
				g.drawString("Rejouer", 430, 350);
				g.drawRect(80, 430, getWidth() - 160, 80);
				g.drawString("Relire les r�gles", 370, 480);
				g.drawRect(80, 560, getWidth() - 160, 80);
				g.drawString("Quitter le jeu", 392, 610);
			}
		}
	}
}