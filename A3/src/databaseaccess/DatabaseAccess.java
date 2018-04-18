package databaseaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseAccess {
	public static String server = "mysql";
	public static String port = "3306";
	public static String user = "root";
	public static String pass = "Ec!66440";
	public static String dbName = "employees";
	public static DatabaseAccess access = new DatabaseAccess();
	private Connection con;
	private Properties conProp;

	/**
	 * Private constructor for the Database access object, creating a singleton
	 * 
	 * Connection con, allows for access and manipulation of the dataset
	 */
	private DatabaseAccess() {
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
			if (!con.isClosed()) {
				return con;
			} else {
				con = DriverManager.getConnection("jdbc:" + server + "://localhost" + ":" + port + "/" + dbName
						+ "?autoReconnect=true&useSSL=false", conProp);
				return con;
			}
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

}
