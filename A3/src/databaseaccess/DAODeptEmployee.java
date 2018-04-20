package databaseaccess;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factories.DeptEmployeeFactory;
import factories.EmployeeFactory;
import factories.DepartmentsFactory;
import factories.TransferFactoryCreator;
import transferobj.Departments;
import transferobj.DeptEmployee;
import transferobj.Employee;

public class DAODeptEmployee implements DAO<DeptEmployee> {
	public static String tName = "dept_emp";
	public static String[] COLUMNS = new String[] { "emp_no", "dept_no", "from_date", "to_date" };

	@Override
	public boolean addItem(DeptEmployee item) {
		DAOEmployee emp = new DAOEmployee();
		DAODepartments dep = new DAODepartments();
		ArrayList<Employee> eList = emp.getItemsByID(item.getEmpID());
		ArrayList<Departments> dList = dep.getItemsByID(item.getDeptID());
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
			} else {
				s.setString(2, item.getDeptID());
			}
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
					.prepareStatement("UPDATE " + DAODeptEmployee.tName + " SET " + DAODeptEmployee.COLUMNS[2]
							+ " = ?, " + DAODeptEmployee.COLUMNS[3] + " = ?  WHERE " + DAODeptEmployee.COLUMNS[0]
							+ " = ? AND " + DAODeptEmployee.COLUMNS[1] + " = ?;");
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
			//System.out.println(e.getMessage());
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
}