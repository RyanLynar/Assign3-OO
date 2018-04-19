package databaseaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factories.DeptManagerFactory;
import factories.EmployeeFactory;
import factories.TransferFactoryCreator;
import transferobj.DeptManager;
import transferobj.Employee;

public class DAODeptManager implements DAO<DeptManager> {
	public static String tName = "dept_manager";
	public static String[] COLUMNS = new String[] { "emp_no", "dept_no", "from_date", "to_date" };

	@Override
	public boolean addItem(DeptManager item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("INSERT INTO " + DAODeptManager.tName + " VALUES(?,?,?,?);");
			PreparedStatement maxKey = DatabaseAccess.getInstance().getConnection().prepareStatement(
					"SELECT MAX(" + DAODeptManager.COLUMNS[0] + ") FROM " + DAODeptManager.tName + ";");
			ResultSet res = maxKey.executeQuery();

			res.first();

			s.setInt(1, res.getInt(1) + 1);
			s.setString(2, item.getDeptNumber());
			s.setDate(3, item.getToDate());
			s.setDate(4, item.getFromDate());

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
	public boolean removeItem(DeptManager item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection().prepareStatement(
					"DELETE FROM " + DAODeptManager.tName + " WHERE " + DAODeptManager.COLUMNS[0] + " = ?;");
			s.setInt(1, item.getEmployeeNumber());

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
	public boolean modifyItem(DeptManager item) {
		PreparedStatement s = null;
		boolean result = false;
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("UPDATE " + DAODeptManager.tName + " SET " + DAODeptManager.COLUMNS[1]
							+ "=?," + DAODeptManager.COLUMNS[2] + "=?," + DAODeptManager.COLUMNS[3] + "=? WHERE "
							+ DAODeptManager.COLUMNS[0] + " = ?;");

			s.setString(1, item.getDeptNumber());
			s.setDate(2, item.getFromDate());
			s.setDate(3, item.getToDate());
			s.setInt(4, item.getEmployeeNumber());

			if (s != null) {
				result = s.executeUpdate() > 0;
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
	public ArrayList<DeptManager> createList(int numRows) {
		ArrayList<DeptManager> entryList = new ArrayList<>();
		try {
			ResultSet r = null;
			if (numRows == -1) {
				r = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT * FROM " + DAODeptManager.tName + ";")
						.executeQuery();
			} else {
				r = DatabaseAccess.getInstance().getConnection()
						.prepareStatement("SELECT * FROM " + DAODeptManager.tName + ";").executeQuery();
			}
			if (r != null) {
				DeptManagerFactory fact = (DeptManagerFactory) TransferFactoryCreator.createBuilder(DeptManager.class);
				entryList = fact.createListFromResults(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.getInstance().closeConnection();
		return entryList;
	}

	@Override
	public ArrayList<DeptManager> getItemsByID(int id) {
		ArrayList<DeptManager> result = new ArrayList<>();
		try {
			ResultSet r = null;
			PreparedStatement s = DatabaseAccess.getInstance().getConnection().prepareStatement(
					"SELECT * FROM " + DAODeptManager.tName + " WHERE " + DAODeptManager.COLUMNS[0] + " = ?");
			s.setInt(1, id);
			System.out.println("DEPTM Statement " + s.toString());
			r = s.executeQuery();
			if (r != null) {
				DeptManagerFactory fact = (DeptManagerFactory) TransferFactoryCreator.createBuilder(DeptManager.class);
				result = fact.createListFromResults(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.getInstance().closeConnection();
		return result;
	}

}
