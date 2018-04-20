package databaseaccess;

import java.sql.Date;
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
		DAOEmployee emp = new DAOEmployee();
		DAODepartments dep = new DAODepartments();

		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("INSERT INTO " + DAODeptEmployee.tName + " VALUES(?,?,?,?);");
			PreparedStatement eKey = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("SELECT MAX(" + DAOEmployee.COLUMNS[0] + ") FROM " + DAOEmployee.tName + ";");
			PreparedStatement dKey = DatabaseAccess.getInstance().getConnection().prepareStatement(
					"SELECT MAX(" + DAODepartments.COLUMNS[0] + ") FROM " + DAODepartments.tName + ";");
			s.setInt(1, item.getEmpID());
			s.setString(2, item.getDeptID());
			s.setDate(3, item.getfDate());
			s.setDate(4, item.gettDate());
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

			int temp = s.executeUpdate();
			System.out.println(temp);
			result = temp > 0;
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
					.prepareStatement("UPDATE " + DAODeptEmployee.tName + " SET " + DAODeptEmployee.COLUMNS[2] + " = ?, "
							+ DAODeptEmployee.COLUMNS[3] + " = ?  WHERE " + DAODeptEmployee.COLUMNS[0] + " = ? AND "
							+ DAODeptEmployee.COLUMNS[1] + " = ?;");
			s.setDate(1, item.getfDate());
			s.setDate(2, item.gettDate());
			s.setInt(3, item.getEmpID());
			s.setString(4, item.getDeptID());
			System.out.println(s.toString());
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
	public <U> ArrayList<DeptEmployee> getItemsByID(U id) {
		ArrayList<DeptEmployee> result = new ArrayList<>();
		PreparedStatement s = null;
		try {
			if (id instanceof Integer) {
				s = DatabaseAccess.getInstance().getConnection().prepareStatement(
						"SELECT * FROM " + DAODeptEmployee.tName + " WHERE " + DAODeptEmployee.COLUMNS[0] + " = ?;");
				s.setInt(1, (int) id);
			} else if (id instanceof String) {
				s = DatabaseAccess.getInstance().getConnection().prepareStatement(
						"SELECT * FROM " + DAODeptEmployee.tName + " WHERE " + DAODeptEmployee.COLUMNS[1] + " = ?;");

				s.setString(1, (String) id);
			} else {
				throw new IllegalArgumentException("Invallid Key Type");
			}

			if (s != null) {
				ResultSet r = s.executeQuery();
				DeptEmployeeFactory fact = (DeptEmployeeFactory) TransferFactoryCreator
						.createBuilder(DeptEmployee.class);
				result = fact.createListFromResults(r);
				DatabaseAccess.getInstance().closeConnection();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public static void main(String[] args) {
		DAODeptEmployee emp = new DAODeptEmployee();
		ArrayList<DeptEmployee> eList = emp.getItemsByID(10001);
		eList.get(0).setfDate(Date.valueOf("1992-03-24"));
		System.out.println(emp.modifyItem(eList.get(0)));
		eList = emp.getItemsByID("d005");
		eList.get(0).settDate(Date.valueOf("1997-04-27"));
		System.out.println(emp.modifyItem(eList.get(0)));
	}

}
