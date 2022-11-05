package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost/mygreensky7";
			String dbID = "mygreensky7";
			String dbPassword = "hoon@2443";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM member WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(userPassword)) {
					return 1;
				} else
					return 0;

			}
			return -1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -2;
	}
	public int join(Member member)
	{
		String SQL = "INSERT INTO member VALUES (?,?,?,?,?)";
		try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1,member.getUserID());
				pstmt.setString(2,member.getUserPassword());
				pstmt.setString(3,member.getUserName());
				pstmt.setString(4,member.getUserGender());
				pstmt.setString(5,member.getUserEmail());
				return pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}
 }
