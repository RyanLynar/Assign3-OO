package databaseaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factories.EmployeeFactory;
import factories.TransferFactoryCreator;
import transferobj.Employee;
import transferobj.TransferObject;

public class DAOEmployee implements DAO<Employee> {
	public static String tName = "employees";
	public static String[] COLUMNS = new String[] { "emp_no", "birth_date", "first_name", "last_name", "gender",
			"hire_date" };

	/*
	 * public void setString(Employee newEmp, String data, String cName) { if
	 * (cName.equals("first_name")) { newEmp.setEmpFName(data); } else if
	 * (cName.equals("last_name")) { newEmp.setEmpLName(data); } }
	 * 
	 * public void setChar(Employee newEmp, String data, String cName) { if
	 * (cName.equals("gender")) { newEmp.setEmpGender(data); } }
	 * 
	 * public void setInt(Employee newEmp, int data, String cName) { if
	 * (cName.equals("emp_no")) { newEmp.setEmpNumber(data); }
	 * 
	 * }
	 * 
	 * public void setDate(Employee newEmp, Date data, String cName) { if
	 * (cName.equals("birth_date")) { newEmp.setEmpBDate(data); } else if
	 * (cName.equals("hire_date")) { newEmp.setEmpHDate(data); }
	 * 
	 * }
	 */

	@Override
	public boolean addItem(TransferObject itemToAdd) {
		boolean result = false;
		PreparedStatement s = null;
		try {

			if (itemToAdd instanceof Employee) {
				s = DatabaseAccess.access.getConnection()
						.prepareStatement("INSERT INTO " + DAOEmployee.tName + " VALUES(?,?,?,?,?,?);");
				PreparedStatement maxKey = DatabaseAccess.access.getConnection()
						.prepareStatement("SELECT MAX(" + DAOEmployee.COLUMNS[0] + ") FROM " + DAOEmployee.tName + ";");
				ResultSet res = maxKey.executeQuery();
				res.first();
				s.setInt(1, res.getInt(1) + 1);
				for (int i = 1; i < DAOEmployee.COLUMNS.length; i++) {
					s.setString(i + 1, itemToAdd.getValues()[i]);
				}
				res.close();
			}
			System.out.println(s.toString());
			if (s != null) {
				DatabaseAccess.access.closeConnection();
				return s.executeUpdate() == 1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.access.closeConnection();
		return result;

	}

	public boolean removeItem(TransferObject itemToRemove) {
		boolean result = false;
		PreparedStatement s = null;
		try {

			if (itemToRemove instanceof Employee) {
				s = DatabaseAccess.access.getConnection().prepareStatement("DELETE FROM " + DAOEmployee.tName
						+ " WHERE " + DAOEmployee.COLUMNS[0] + " = " + ((Employee) itemToRemove).getEmpNumber());
			}
			if (s != null) {
				DatabaseAccess.access.closeConnection();
				return s.executeUpdate() > 0;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		DatabaseAccess.access.closeConnection();
		return result;
	}

	@Override

	public boolean modifyItem(TransferObject itemToModify) {
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.access.getConnection()
					.prepareStatement("UPDATE " + DAOEmployee.tName + " SET " + DAOEmployee.COLUMNS[1] + "=?,"
							+ DAOEmployee.COLUMNS[2] + "=?," + DAOEmployee.COLUMNS[3] + "=?," + DAOEmployee.COLUMNS[4]
							+ "=?," + DAOEmployee.COLUMNS[5] + "=? WHERE " + DAOEmployee.COLUMNS[0] + " = ?;");

			for (int i = 1; i < DAOEmployee.COLUMNS.length; i++) {
				s.setString(i, itemToModify.getValues()[i]);
			}
			s.setString(6, itemToModify.getValues()[0]);

			if (s != null) {
				DatabaseAccess.access.closeConnection();
				return 0 < s.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.access.closeConnection();
		return false;
	}

	public ArrayList<Employee> createList(int numRows) {
		ArrayList<Employee> entryList = new ArrayList<>();
		try {
			ResultSet r = null;
			if (numRows == -1) {
				r = DatabaseAccess.access.getConnection().prepareStatement("SELECT * FROM " + tName + ";")
						.executeQuery();
			} else {
				r = DatabaseAccess.access.getConnection()
						.prepareStatement("SELECT * FROM " + tName + " LIMIT " + numRows + ";").executeQuery();
			}
			if (r != null) {
				EmployeeFactory fact = (EmployeeFactory) TransferFactoryCreator.createBuilder(Employee.class);
				entryList = fact.createListFromResults(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.access.closeConnection();
		return entryList;

	}

	public ArrayList<Employee> getItemsByID(int id) {
		ArrayList<Employee> result = new ArrayList<>();
		try {
			ResultSet r = null;
			PreparedStatement s = DatabaseAccess.access.getConnection().prepareStatement(
					"SELECT * FROM " + DAOEmployee.tName + " WHERE " + DAOEmployee.COLUMNS[0] + " = ?");
			r = s.executeQuery();
			if (r != null) {
				EmployeeFactory fact = (EmployeeFactory) TransferFactoryCreator.createBuilder(Employee.class);
				result = fact.createListFromResults(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.access.closeConnection();
		return result;

	}
}
