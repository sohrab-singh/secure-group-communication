package communication.group.secure.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public  class DataBaseConnection

{
	public static Statement st1;
	public DataBaseConnection()

	{

		try
		{

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	
			Connection con1=DriverManager.getConnection("jdbc:odbc:signature","sa", "");
			st1=con1.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("exception" + e );
		}
	}

}	
	
	



