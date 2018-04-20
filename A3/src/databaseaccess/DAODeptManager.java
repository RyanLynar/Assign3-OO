package databaseaccess;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factories.DepartmentsFactory;
import factories.DeptEmployeeFactory;
import factories.DeptManagerFactory;
import factories.EmployeeFactory;
import factories.TransferFactoryCreator;
import transferobj.Departments;
import transferobj.DeptEmployee;
import transferobj.DeptManager;
import transferobj.Employee;

public class DAODeptManager implements DAO<DeptManager> {
	public static String tName = "dept_manager";
	public static String[] COLUMNS = new String[] { "emp_no", "dept_no", "from_date", "to_date" };

	@Override
	public boolean addItem(DeptManager item) {
		DAOEmployee emp = new DAOEmployee();
		DAODepartments dep = new DAODepartments();
		ArrayList<Employee> eList = emp.getItemsByID(item.getEmployeeNumber());
		ArrayList<Departments> dList = dep.getItemsByID(item.getDeptNumber());
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("INSERT INTO " + DAODeptEmployee.tName + " VALUES(?,?,?,?);");
			if (eList.isEmpty()) {
				PreparedStatement eKey = DatabaseAccess.getInstance().getConnection()
						.prepareStatement("SELECT MAX(" + DAOEmployee.COLUMNS[0] + ") FROM " + DAOEmployee.tName + ";");
				ResultSet r = eKey.executeQuery();
				r.first();

				EmployeeFactory eFact = (EmployeeFactory) TransferFactoryCreator.createBuilder(Employee.class);
				emp.addItem(eFact.createFromInput(new String[] { "" + (r.getInt(1) + 1), "1992-03-24", "PLACEHOLDER",
						"PLACEHOLDER", "M", "1992-03-24" }));
				s.setInt(1, r.getInt(1) + 1);
				r.close();
			} else {
				s.setInt(1, eList.get(0).getEmpNumber());
			}
			if (dList.isEmpty()) {
				PreparedStatement dKey = DatabaseAccess.getInstance().getConnection().prepareStatement(
						"SELECT MAX(" + DAODepartments.COLUMNS[0] + ") FROM " + DAODepartments.tName + ";");
				ResultSet r = dKey.executeQuery();
				r.first();

				DepartmentsFactory dFact = (DepartmentsFactory) TransferFactoryCreator.createBuilder(Departments.class);
				String sPlaceHolder = r.getString(1);
				String[] temp = sPlaceHolder.split("d");
				int placeHolder = Integer.parseInt(temp[1]);
				placeHolder++;
				s.setString(2, String.format("d%03d", placeHolder));
				r.close();
			} else {
				s.setString(2, item.getDeptNumber());
			}
			s.setDate(3, item.getToDate());
			s.setDate(4, item.getFromDate());
			

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
					.prepareStatement("UPDATE " + DAODeptManager.tName + " SET " + DAODeptManager.COLUMNS[1] + "=?,"
							+ DAODeptManager.COLUMNS[2] + "=?," + DAODeptManager.COLUMNS[3] + "= ? WHERE "
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
				r = DatabaseAccess.getInstance().getConnection()
						.prepareStatement("SELECT * FROM " + DAODeptManager.tName + ";").executeQuery();
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
	public <U> ArrayList<DeptManager> getItemsByID(U id) {
		ArrayList<DeptManager> result = new ArrayList<>();
		try {
			ResultSet r = null;
			PreparedStatement s = null;
			if (id instanceof Integer) {
				s = DatabaseAccess.getInstance().getConnection().prepareStatement(
						"SELECT * FROM " + DAODeptManager.tName + " WHERE " + DAODeptManager.COLUMNS[0] + " = ?");
				s.setInt(1, (int) id);
			} else if (id instanceof String) {
				s = DatabaseAccess.getInstance().getConnection().prepareStatement(
						"SELECT * FROM " + DAODeptManager.tName + " WHERE " + DAODeptManager.COLUMNS[1] + " = ?");

				s.setString(1, (String) id);
			} else {
				throw new IllegalArgumentException("Invalid Key Type");
			}
			if (s != null) {
				r = s.executeQuery();
				if (r != null) {
					DeptManagerFactory fact = (DeptManagerFactory) TransferFactoryCreator
							.createBuilder(DeptManager.class);
					result = fact.createListFromResults(r);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.getInstance().closeConnection();
		return result;
	}

	public static void main(String[] args) {
		DAODeptManager emp = new DAODeptManager();
		DeptManagerFactory dFact = (DeptManagerFactory) TransferFactoryCreator.createBuilder(DeptManager.class);
		System.out.println(emp.addItem( dFact.createFromInput(new String[]{"25","d002","1992-03-24","1992-03-24"})));


	}

}
