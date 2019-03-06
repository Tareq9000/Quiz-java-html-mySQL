package quizFragen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao {

	public ArrayList<Frage> selectTable() {

		Connection con = null;

		ArrayList<Frage> list = new ArrayList<>();
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "1234");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from fragen");

			while (rs.next()) {
				Frage frage = new Frage();
				frage.setId(rs.getInt("id"));
				frage.setFrage(rs.getString("frage"));
				frage.setAntwort(rs.getInt("antwort"));;
				frage.setM1(rs.getString("m1"));
				frage.setM2(rs.getString("m2"));
				frage.setM3(rs.getString("m3"));
				list.add(frage);

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
		return list;

	}

}