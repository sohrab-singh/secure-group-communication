package communication.group.secure.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDao {
	Connection con, c1;
	Statement st, st1;
	ResultSet rs;

	public LoginDao() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:signature", "sa", "");
			st = con.createStatement();
		} catch (Exception e) {
			System.out.println("Database Connectivity Error " + e);
		}

	}

	public boolean getResult(String id, String pass, String gid)
			throws Exception {

		ResultSet rs = st
				.executeQuery("select * from UsersInfo where UserId = '" + id
						+ "' and Password = '" + pass + "' and GrpId = '" + gid
						+ "'");
		if (rs.next()) {
			System.out.println("The Values are Found");
			return true;
		} else
			return false;
	}


	// end------------------------------



	public int addMember(String id, String pass, String gid) throws Exception {
		st.executeUpdate("Insert into UsersInfo (UserId,Password,GrpId) values ('"
				+ id + "','" + pass + "','" + gid + "')");
		return 1;
	}

	public boolean checkPermit(String id) throws Exception {

		rs = st.executeQuery("Select * from UsersInfo where MailId = '" + id
				+ "'");
		boolean flag = false;
		if (rs.next()) {
			flag = true;
		}
		return flag;
	}

	public boolean assignKeys(String mailid, String pass, String usradrs,
			String gid) {
		boolean flag = false;
		try {
			String query = "Update UsersInfo set UserIpAdrs = '" + usradrs
					+ "' where UserId='" + mailid + "' and GrpId='" + gid + "'";
			st.executeUpdate(query);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean removeUser(String mailid, String gid) {
		boolean flag = false;
		try {
			String query = "Delete from UsersInfo where UserId='" + mailid
					+ "' and GrpId='" + gid + "'";
			st.executeUpdate(query);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
