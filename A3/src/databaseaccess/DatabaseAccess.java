package databaseaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	public static DatabaseAccess access = new DatabaseAccess();
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

	public boolean addItem(DAO itemToAdd) {
		boolean result = false;
		PreparedStatement s = null;
		try {

			if (itemToAdd instanceof DAOEmployee) {
				s = con.prepareStatement("INSERT INTO " + DAOEmployee.tName + " VALUES(?,?,?,?,?,?);");
				PreparedStatement maxKey = con.prepareStatement("SELECT MAX("+itemToAdd.getColumnNames()[0] +") FROM "+ DAOEmployee.tName+";");
				ResultSet res = maxKey.executeQuery();
				res.first();
				s.setInt(1, res.getInt(1)+1);
				for (int i = 1; i < itemToAdd.getColumnNames().length; i++) {
					s.setString(i + 1, itemToAdd.getColumnValues()[i]);
				}
			}
			System.out.println(s.toString());
			if (s != null) {
				return s.executeUpdate() == 1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;

	}

	public boolean removeItem(DAO itemToRemove) {
		boolean result = false;
		PreparedStatement s = null;
		try {

			if (itemToRemove instanceof DAOEmployee) {
				s = con.prepareStatement("DELETE FROM " + DAOEmployee.tName + " WHERE "
						+ itemToRemove.getColumnNames()[0] + " = " + ((DAOEmployee) itemToRemove).getEmpNumber());
			}
			if (s != null) {
				return s.executeUpdate() == 1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public void createItems(ArrayList<DAO> entryList, String type, int numRows) {
		String tName = type;
		String[] colNames = setColumns(tName);
		DAO entry;

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
					if (type.equals(DAOEmployee.tName)) {
						entry = new DAOEmployee();
					} else {
						return;
					}
					for (int i = 0; i < r.getMetaData().getColumnCount(); i++) {
						if (r.getMetaData().getColumnType(i + 1) == Types.VARCHAR) {
							if (entry.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
								entry.setString(r.getString(i + 1), r.getMetaData().getColumnName(i + 1));
							}
						} else if (r.getMetaData().getColumnType(i + 1) == Types.CHAR) {
							if (entry.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
								entry.setChar(r.getString(i + 1), r.getMetaData().getColumnName(i + 1));
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
					entryList.add(entry);
					r.next();
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}



}
