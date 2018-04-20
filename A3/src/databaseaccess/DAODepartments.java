package databaseaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.media.sound.DirectAudioDeviceProvider;

import factories.DepartmentsFactory;
import factories.EmployeeFactory;
import factories.TitlesFactory;
import factories.TransferFactoryCreator;
import transferobj.Departments;
import transferobj.Employee;
import transferobj.Titles;

public class DAODepartments implements DAO<Departments> {
	public static String TABLE_NAME = "departments";
	public static String[] COLUMNS = new String[] { "dept_no", "dept_name" };

	@Override
	public boolean addItem(Departments item) {
		boolean result = false;
		PreparedStatement s = null;

		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("INSERT INTO " + DAODepartments.TABLE_NAME + " VALUES(?,?);");
			PreparedStatement maxKey = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("SELECT " + DAODepartments.COLUMNS[0] + " FROM " + DAODepartments.TABLE_NAME
							+ " ORDER BY " + DAODepartments.COLUMNS[0] + " DESC;");
			ResultSet res = maxKey.executeQuery();
			res.first();
			String sPlaceHolder = res.getString(1);
			String[] temp = sPlaceHolder.split("d");

			int placeHolder = Integer.parseInt(temp[1]);
			placeHolder++;
			s.setString(1, String.format("d%03d", placeHolder));
			s.setString(2, item.getDeptName());
			res.close();
			System.out.println(s.toString());
			if (s != null) {
				result = s.executeUpdate() == 1;
				DatabaseAccess.getInstance().closeConnection();
				return result;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.getInstance().closeConnection();
		return result;
	}

	@Override
	public boolean removeItem(Departments item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection().prepareStatement(
					"DELETE FROM " + DAODepartments.TABLE_NAME + " WHERE " + DAODepartments.COLUMNS[0] + " = ?;");
			s.setString(1, item.getDeptNumber());
			if (s != null) {
				DatabaseAccess.getInstance().closeConnection();
				return s.executeUpdate() > 0;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		DatabaseAccess.getInstance().closeConnection();
		return result;
	}

	@Override
	public boolean modifyItem(Departments item) {
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection().prepareStatement("UPDATE " + DAODepartments.TABLE_NAME
					+ " SET " + DAODepartments.COLUMNS[1] + "=?" + " WHERE " + DAODepartments.COLUMNS[0] + " = ?;");

			for (int i = 1; i < DAOEmployee.COLUMNS.length; i++) {
				s.setString(i, item.getValues()[i]);
			}
			s.setString(2, item.getDeptNumber());

			if (s != null) {
				DatabaseAccess.getInstance().closeConnection();
				return 0 < s.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.getInstance().closeConnection();
		return false;
	}

	@Override
	public ArrayList<Departments> createList(int numRows) {
		ArrayList<Departments> entryList = new ArrayList<>();
		try {
			ResultSet r = null;
			if (numRows == -1) {
				r = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT * FROM " + tName + ";")
						.executeQuery();
			} else {
				r = DatabaseAccess.getInstance().getConnection()
						.prepareStatement("SELECT * FROM " + DAODepartments.TABLE_NAME + ";").executeQuery();
			}

			if (r != null) {
				DepartmentsFactory fact = (DepartmentsFactory) TransferFactoryCreator.createBuilder(Departments.class);
				entryList = fact.createListFromResults(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.getInstance().closeConnection();
		return entryList;
	}

	@Override
	public <U> ArrayList<Departments> getItemsByID(U id) {
		ArrayList<Departments> entryList = new ArrayList<>();
		try {
			PreparedStatement s = DatabaseAccess.getInstance().getConnection().prepareStatement(
					"SELECT * FROM " + DAODepartments.tName + " WHERE " + DAODepartments.COLUMNS[0] + " = ? ;");
			if (id instanceof String) {
				s.setString(1, (String) id);
			} else {
				throw new IllegalArgumentException("Invalid Key Type");
			}
			System.out.println(s.toString());
			ResultSet r = s.executeQuery();
			DepartmentsFactory fact = (DepartmentsFactory) TransferFactoryCreator.createBuilder(Departments.class);
			entryList = fact.createListFromResults(r);
			DatabaseAccess.getInstance().closeConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return entryList;
	}

}