package communication.group.secure.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MessageDao {

	Connection con, c1;
	Statement st, st1;
	ResultSet rs;

	public boolean isUserExists(String id, String gid) throws Exception {
		boolean isUserExists  = false;
		rs = st.executeQuery("select * from UsersInfo where UserId = '" + id
				+ "' AND GrpId = '" + gid + "'");
		if (rs.next()) {
			System.out.println("The Values are Found");
			isUserExists = true;
		}
		
		return isUserExists;
	}

	public boolean checkMailId(String mailid, String gid) throws Exception {
		boolean isMailIdExists = false;
		rs = st.executeQuery("select * from UsersInfo where UserId = '"
				+ mailid + "' AND GrpId = '" + gid + "'");

		if (rs.next()) {
			System.out.println("value 1:::" + rs.getString(1));
			System.out.println("value 2:::" + rs.getString(2));
			System.out.println("value3 :::" + rs.getString(3));
			isMailIdExists = true;
		}
		return isMailIdExists;
	}

	public String getKey(String user, String grpid) {
		System.out.println("Inside the getKey Method");
		String gid = "";
		if (grpid.equalsIgnoreCase("Group 1"))
			gid = "tree1";
		else if (grpid.equalsIgnoreCase("Group 2"))
			gid = "tree2";
		String key = "";
		try {
			rs = st.executeQuery("Select prikey from " + gid
					+ " where memname = '" + user + "'");
			if (rs.next()) {
				key = rs.getString(1);
				System.out.println("The Key Value = " + key);
			}
			if (key.length() > 8)
				key = key.substring(0, 8);
			else {
				int i = key.length();
				System.out.println("The Key Length = " + i);
				while (i < 8) {
					key = key + i;
					System.out.println("The Key = " + key);
					i++;
				}
			}

		} catch (Exception e) {
			System.out.println("Exception :" + e);
		}
		return key;
	}

	public String getHostAdrs(String gid) throws Exception {
		String adrs = "";
		rs = st.executeQuery("select UserIpAdrs from UsersInfo where UserId = '"
				+ gid + "'");
		if (rs.next()) {
			adrs = rs.getString(1);
		}
		return adrs;
	}

	public String getHostAddress(String userId, String groupId)
			throws Exception {
		String adrs = "";
		rs = st.executeQuery("Select UserIpAdrs from UsersInfo where UserId = '"
				+ userId + "' AND GrpId = '" + groupId + "'");
		if (rs.next()) {
			adrs = rs.getString(1);
		}
		return adrs;
	}
	
	// new edit

	public int getUsersCount(String gid) throws Exception {
		int c = 0;
		if (gid.equals("Group 1")) {
			rs = st.executeQuery("select UserId from usersinfo where Grpid like '"
					+ gid + "%'");
			while (rs.next()) {
				c++;
			}
		} else {
			rs = st.executeQuery("select UserId from usersinfo where Grpid like '"
					+ gid + "%'");
			while (rs.next()) {
				c++;
			}

		}
		return c;

	}

	public String[] getUsers(String gid, int n) throws SQLException {
		int i = 0;
		String users[] = new String[n];
		rs = st.executeQuery("select UserId from Usersinfo where Grpid like '"
				+ gid + "%'");
		while (rs.next()) {
			users[i] = rs.getString("UserId");
			i++;
		}
		return users;
	}
	
	public boolean getResultInfo(String id, String pass, String gid)
			throws Exception {
		String tableName = "";
		if (gid.equalsIgnoreCase("Group 1")) {
			tableName = "tree1";
		} else {
			tableName = "tree2";
		}
		System.out.println("The Selected Group Table = " + tableName);
		rs = st.executeQuery("select * from " + tableName
				+ " where memname = '" + id + "' and pass = '" + pass + "'");
		if (rs.next()) {
			System.out.println("The Values are Found");
			return true;
		} else
			return false;
	}



}
