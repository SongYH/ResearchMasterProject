package Common;
import java.sql.*;

public class DBconnection
{
	public static Connection getConnection()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			String DB_URL = "jdbc:mysql://202.31.202.199:3306/ResearchMasterDatabase"
						  + "?useUnicode=true&characterEncoding=UTF-8";
			String DB_USER = "root";
			String DB_PASSWORD = "korean1";

			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
