package cesi.ril;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

public class DemoJdbc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sauverEnBase("Jean DRANT");
		System.out.println("Sauver en base fait...");
		
		lireEnbase();
		System.out.println("Lire en base fait...");
		
		modifierEnbase("Jean DUPONT", "Jean DRANT");
		System.out.println("Modifier en base fait...");

		supprimerEnbase("Jean DUPONT");
		System.out.println("Supprimer en base fait...");
	}
	public static void sauverEnBase(String personne) {
		String url = "jdbc:mysql://localhost/javadb";
		String login = "mla";
		String passwd = "mla";
		Connection cn = null;
		Statement st = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, login, passwd);
			st = cn.createStatement();
			
			String sql = "INSERT INTO `javadb` (`personne`) VALUES ('"+personne+"')";
			st.executeUpdate(sql);
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void lireEnbase() {
		String url = "jdbc:mysql://localhost/javadb";
		String login = "mla";
		String passwd = "mla";
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, login, passwd);
			st = cn.createStatement();
			
			String sql = "SELECT * FROM `javadb`";
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				System.out.println(rs.getString("personne"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void modifierEnbase(String personne, String where) {
		String url = "jdbc:mysql://localhost/javadb";
		String login = "mla";
		String passwd = "mla";
		Connection cn = null;
		PreparedStatement st = null;
		int nbChamp = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, login, passwd);
			
			String sql = "Update `javadb` SET `personne` = ? WHERE `personne` = ?";
			st = cn.prepareStatement(sql);
			
			st.setString(1, personne);
			st.setString(2, where);
			
			nbChamp = st.executeUpdate();
			System.out.println(nbChamp + " champs supprimés");
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	
	
	public static void supprimerEnbase(String where) {
		String url = "jdbc:mysql://localhost/javadb";
		String login = "mla";
		String passwd = "mla";
		Connection cn = null;
		PreparedStatement st = null;
		int nbChamp = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, login, passwd);
			
			String sql = "DELETE FROM `javadb` WHERE `personne` = ?";
			st = cn.prepareStatement(sql);
			
			st.setString(1, where);
			
			nbChamp = st.executeUpdate();
			System.out.println(nbChamp + " champs supprimés");
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
