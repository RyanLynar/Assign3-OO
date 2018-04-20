package databaseaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class DatabaseAccess {
	private static String server = "mysql";
	private static String port = "3306";
	private static String user = "root";
	private static String pass = "Ec!66440";//"admin";
	private static String dbName = "employees";
	private static DatabaseAccess access;

	public static DatabaseAccess getInstance() {
		if (access == null)
			access = new DatabaseAccess();
		return access;
	}

	private Connection con;
	private Properties conProp;

	/**
	 * Private constructor for the Database access object, creating a singleton
	 * 
	 * Connection con, allows for access and manipulation of the dataset
	 */
	private DatabaseAccess() {
		con = null;
		conProp = new Properties();
		conProp.put("user", user);
		conProp.put("password", pass);
	}

	public void closeConnection() {
		try {
			if (!con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Connection getConnection() {
		try {
			new Driver();
			con = DriverManager.getConnection(
					"jdbc:" + server + "://localhost" + ":" + port + "/" + dbName + "?autoReconnect=true&useSSL=false",
					conProp);
			return con;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void shutDown() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args) {
		DatabaseAccess.access.getConnection();
	}
}
