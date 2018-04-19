package databaseaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factories.DeptEmployeeFactory;
import factories.TransferFactoryCreator;
import transferobj.DeptEmployee;

public class DAODeptEmployee implements DAO<DeptEmployee> {
	public static String tName = "dept_emp";
	public static String[] COLUMNS = new String[] { "emp_no", "dept_no", "from_date", "to_date" };

	@Override
	public boolean addItem(DeptEmployee item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("INSERT INTO " + DAODeptEmployee.tName + " VALUES(?,?,?,?);");
			for (int i = 0; i < DAODeptEmployee.COLUMNS.length; i++) {
				s.setString(i, item.getValues()[i]);
			}
			result = s.executeUpdate() == 1;
			DatabaseAccess.getInstance().closeConnection();
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean removeItem(DeptEmployee item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection().prepareStatement("DELETE FROM " + DAODeptEmployee.tName
					+ " WHERE " + DAODeptEmployee.COLUMNS[0] + " = ? AND " + DAODeptEmployee.COLUMNS[1] + " = ?;");
			s.setInt(1, item.getEmpID());
			s.setString(2, item.getDeptID());

			result = s.executeUpdate() > 0;
			DatabaseAccess.getInstance().closeConnection();
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean modifyItem(DeptEmployee item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("UPDATE " + DAODeptEmployee.tName + " SET " + DAODeptEmployee.COLUMNS[2] + "=?"
							+ DAODeptEmployee.COLUMNS[3] + "=? + WHERE " + DAODeptEmployee.COLUMNS[0] + " =? AND "
							+ DAODeptEmployee.COLUMNS[1] + "=?;");
			s.setDate(1, item.getfDate());
			s.setDate(2, item.gettDate());
			s.setInt(3, item.getEmpID());
			s.setString(4, item.getDeptID());
			result = s.executeUpdate() > 0;
			DatabaseAccess.getInstance().closeConnection();
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public ArrayList<DeptEmployee> createList(int numRows) {
		ArrayList<DeptEmployee> result = new ArrayList<>();
		try {
			ResultSet r = null;
			r = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("SELECT * FROM " + DAODeptEmployee.tName + ";").executeQuery();
			DeptEmployeeFactory fact = (DeptEmployeeFactory) TransferFactoryCreator.createBuilder(DeptEmployee.class);
			result = fact.createListFromResults(r);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public ArrayList<DeptEmployee> getItemsByID(int id) {
		ArrayList<DeptEmployee> result = new ArrayList<>();
		try {
			PreparedStatement s = DatabaseAccess.getInstance().getConnection().prepareStatement(
					"SELECT * FROM " + DAODeptEmployee.tName + " WHERE " + DAODeptEmployee.COLUMNS[0] + " = ?;");
			s.setInt(1, id);

			System.out.println("DEPTE Statement " + s.toString());
			ResultSet r = s.executeQuery();
			DeptEmployeeFactory fact = (DeptEmployeeFactory) TransferFactoryCreator.createBuilder(DeptEmployee.class);
			result = fact.createListFromResults(r);
			DatabaseAccess.getInstance().closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// TODO Auto-generated method stub
		return result;
	}

}