package databaseaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class DatabaseAccess {
	public static String server = "mysql";
	public static String port = "3306";
	public static String user = "root";
	public static String pass = "Ec!66440";
	public static String dbName = "employees";
	private Connection con;
	private Properties conProp;
	private ArrayList<String> tableNames;

	/**
	 * Private constructor for the Database access object, creating a singleton
	 * 
	 * Connection con, allows for access and manipulation of the dataset
	 */
	private DatabaseAccess() {
		try {
			conProp = new Properties();
			conProp.put("user", user);
			conProp.put("password", pass);
			con = DriverManager.getConnection(
					"jdbc:" + server + "://localhost" + ":" + port + "/" + dbName + "?autoReconnect=true&useSSL=false",
					conProp);
			System.out.println("Connected to database");
			tableNames = this.setTableNames();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public String[] setColumns(String tName) {
		String[] result = null;
		try {
			ResultSet r = con.prepareStatement("SELECT * FROM " + tName + ";").executeQuery();
			result = new String[r.getMetaData().getColumnCount()];
			for (int i = 0; i < r.getMetaData().getColumnCount(); i++) {
				result[i] = r.getMetaData().getColumnName(i + 1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		return result;
	}

	public ArrayList<String> setTableNames() {
		ArrayList<String> result = null;
		try {
			ResultSet r = con.prepareStatement("Show tables from " + dbName).executeQuery();
			result = new ArrayList<String>();
			r.first();
			while (!r.isAfterLast()) {
				for (int i = 0; i < r.getMetaData().getColumnCount(); i++)
					result.add(r.getString(i + 1));

				r.next();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public DAO createItem(DAO entry, int numRows) {
		String tName = entry.getTName();
		String[] colNames = setColumns(tName);
		try {
			ResultSet r = null;
			if (numRows == -1) {
				r = con.prepareStatement("SELECT * FROM " + tName + ";").executeQuery();
			} else {
				r = con.prepareStatement("SELECT * FROM " + tName + " LIMIT " + numRows + ";").executeQuery();
			}
			if (r != null) {
				r.first();
				while (!r.isAfterLast()) {
					for (int i = 0; i < r.getMetaData().getColumnCount(); i++) {
						if (r.getMetaData().getColumnType(i + 1) == Types.VARCHAR) {
							if (entry.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
								entry.setString(r.getString(i + 1), r.getMetaData().getColumnName(i + 1));
							}
						}else if(r.getMetaData().getColumnType(i+1)==Types.CHAR) {
							if(entry.getColumnNames()[i].equals(r.getMetaData().getColumnName(i+1))) {
								entry.setChar(r.getString(i+1),r.getMetaData().getColumnName(i+1));
							}
						} else if (r.getMetaData().getColumnType(i + 1) == Types.INTEGER) {
							if (entry.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
								entry.setInt(r.getInt(i + 1), r.getMetaData().getColumnName(i + 1));
							}
						} else if (r.getMetaData().getColumnType(i + 1) == Types.DATE) {
							if (entry.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
								entry.setDate(r.getDate(i + 1), r.getMetaData().getColumnName(i + 1));
							}
						}

					}
					r.next();
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return entry;

	}

	public static void main(String[] args) {
		final DatabaseAccess dba = new DatabaseAccess();
		DAOEmployee testEmp = (DAOEmployee) dba.createItem(new DAOEmployee(), 1);
		testEmp.printAll();
	}

}
