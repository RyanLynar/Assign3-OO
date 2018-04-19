package databaseaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factories.EmployeeFactory;
import factories.TransferFactoryCreator;
import transferobj.Employee;

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
	public boolean addItem(Employee itemToAdd) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("INSERT INTO " + DAOEmployee.tName + " VALUES(?,?,?,?,?,?);");
			PreparedStatement maxKey = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("SELECT MAX(" + DAOEmployee.COLUMNS[0] + ") FROM " + DAOEmployee.tName + ";");
			ResultSet res = maxKey.executeQuery();
			res.first();
			s.setInt(1, res.getInt(1) + 1);
			s.setDate(2, itemToAdd.getEmpBDate());
			s.setString(3, itemToAdd.getEmpFName());
			s.setString(4, itemToAdd.getEmpLName());
			s.setString(5, itemToAdd.getEmpGender());
			s.setDate(6, itemToAdd.getEmpHDate());
			res.close();

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

	public boolean removeItem(Employee itemToRemove) {
		//TODO Make Transactional so that it removes all items with the same key from other tables
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection().prepareStatement(
					"DELETE FROM " + DAOEmployee.tName + " WHERE " + DAOEmployee.COLUMNS[0] + " = ?;");
			s.setInt(1, itemToRemove.getEmpNumber());

			if (s != null) {
				result = s.executeUpdate() >0;
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

	public boolean modifyItem(Employee itemToModify) {
		PreparedStatement s = null;
		boolean result=false;
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("UPDATE " + DAOEmployee.tName + " SET " + DAOEmployee.COLUMNS[1] + "=?,"
							+ DAOEmployee.COLUMNS[2] + "=?," + DAOEmployee.COLUMNS[3] + "=?," + DAOEmployee.COLUMNS[4]
							+ "=?," + DAOEmployee.COLUMNS[5] + "=? WHERE " + DAOEmployee.COLUMNS[0] + " = ?;");
			s.setDate(1, itemToModify.getEmpBDate());
			s.setString(2, itemToModify.getEmpFName());
			s.setString(3, itemToModify.getEmpLName());
			s.setString(4, itemToModify.getEmpGender());
			s.setDate(5, itemToModify.getEmpHDate());
			s.setString(6, itemToModify.getValues()[0]);

			if (s != null) {
				result = s.executeUpdate() >0;
				DatabaseAccess.getInstance().closeConnection();
				return result;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.getInstance().closeConnection();
		return result;
	}

	public ArrayList<Employee> createList(int numRows) {
		ArrayList<Employee> entryList = new ArrayList<>();
		try {
			ResultSet r = null;
			if (numRows == -1) {
				r = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT * FROM " + DAO.tName + ";")
						.executeQuery();
			} else {
				r = DatabaseAccess.getInstance().getConnection()
						.prepareStatement("SELECT * FROM " + DAOEmployee.tName +";").executeQuery();
			}
			if (r != null) {
				EmployeeFactory fact = (EmployeeFactory) TransferFactoryCreator.createBuilder(Employee.class);
				entryList = fact.createListFromResults(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.getInstance().closeConnection();
		return entryList;

	}

	public ArrayList<Employee> getItemsByID(int id) {
		ArrayList<Employee> result = new ArrayList<>();
		try {
			ResultSet r = null;
			PreparedStatement s = DatabaseAccess.getInstance().getConnection().prepareStatement(
					"SELECT * FROM " + DAOEmployee.tName + " WHERE " + DAOEmployee.COLUMNS[0] + " = ?;");
			r = s.executeQuery();
			if (r != null) {
				EmployeeFactory fact = (EmployeeFactory) TransferFactoryCreator.createBuilder(Employee.class);
				result = fact.createListFromResults(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.getInstance().closeConnection();
		return result;

	}
}