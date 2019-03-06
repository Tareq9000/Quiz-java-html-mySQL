package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao {

	public String[] selectTable(int nr) {

		Connection con = null;

		String[] daten = new String[5];
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "1234");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select nr,frage, antwort, m1, m2 , m3 from fragen where nr = " + nr);

			while (rs.next()) {
				daten[0] = rs.getString("frage");
				daten[1] = Integer.toString(rs.getInt("antwort"));
				daten[2] = rs.getString("m1");
				daten[3] = rs.getString("m2");
				daten[4] = rs.getString("m3");

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		if (con != null) {

			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return daten;

	}

	public ArrayList<Highscore> selectHighscore() {

		Connection con = null;
		ArrayList<Highscore> hsList = new ArrayList<Highscore>();
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "1234");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from quiz.highscore");

			while (rs.next()) {
				Highscore hs = new Highscore();
				hs.setId(rs.getInt("idhighscore"));
				hs.setAnzahl(rs.getInt("anzahl"));
				hs.setName(rs.getString("name"));
				hsList.add(hs);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		if (con != null) {

			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return hsList;

	}

	public void updateHighscore(String name, int anzahl) {

		Connection con = null;
		ArrayList<Highscore> hsList = new ArrayList<Highscore>();
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "1234");

			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"update highscore set name =\"" + name + "\", anzahl=" + anzahl + " where idhighscore = 1;");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		if (con != null) {

			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

	}
	
	public String selectBild(int nr) {

		Connection con = null;
		String s = "";
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "1234");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from quiz.bilder where nr="+nr);

			while (rs.next()) {
				s = rs.getString("url");

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		if (con != null) {

			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return s;

	}
}