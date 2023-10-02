import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	/*
	-- Création de la table 'connexion'
CREATE TABLE connexion (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    mdp VARCHAR(255) NOT NULL
);

-- Création de la table 'jeu'
CREATE TABLE jeu (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ID_Connexion INT NOT NULL,
    ivl INT,
    palier INT,
    pointDeVie INT,
    FOREIGN KEY (ID_Connexion) REFERENCES connexion(ID)
);

	*/
	boolean f = false;
	String ID;
	String email;
	String mdp;
	String lvl;
	String palier;
	String vie;

	public JDBC() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/project_gl?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "root", "root");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM connexion");
			boolean a = false;
			while(!a) {
				rs.next();
				if(Frame.menu.email.getText().equals(rs.getString("email")) && String.valueOf(Frame.menu.password.getPassword()).equals(rs.getString("mdp"))) {
					ID =rs.getString("ID");
					a = true;
					f = true;
				}else {
					f = false;
				}
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); System.out.println("SQLState: " + ex.getSQLState()); System.out.println("VendorError: " + ex.getErrorCode());
		} catch (Exception e) {
			System.out.println("erreur"); e.printStackTrace();
		}
	}

	public JDBC(String id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/project_gl?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "root", "root");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM jeu");
			boolean a = false;
			while(!a) {
				rs.next();
				if(id.equals(rs.getString("ID_Connexion"))) {
					lvl = rs.getString("ivl");
					palier = rs.getString("palier");
					vie = rs.getString("pointDeVie");
					a = true;
				}
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); System.out.println("SQLState: " + ex.getSQLState()); System.out.println("VendorError: " + ex.getErrorCode());
		} catch (Exception e) {
			System.out.println("erreur"); e.printStackTrace();
		}
	}

	public void update(String id, int lvl, int palier, int vie) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/project_gl?useLegacyDatetimeCode=false&serverTimezone=America/New_York", "root", "root");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE jeu SET ivl = "+lvl+", palier = "+palier+", pointDeVie = "+vie+" WHERE ID_Connexion = "+Integer.valueOf(id));
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage()); System.out.println("SQLState: " + ex.getSQLState()); System.out.println("VendorError: " + ex.getErrorCode());
		} catch (Exception e) {
			System.out.println("erreur"); e.printStackTrace();
		}
	}
}