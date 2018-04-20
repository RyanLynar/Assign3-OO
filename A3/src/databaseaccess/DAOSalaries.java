package databaseaccess;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import factories.EmployeeFactory;
import factories.SalariesFactory;
import factories.TransferFactoryCreator;
import transferobj.Employee;
import transferobj.Salaries;

public class DAOSalaries implements DAO<Salaries> {
	public static String tName = "salaries";
	public static String[] COLUMNS = new String[] { "emp_no", "salary", "from_date", "to_date" };

	@Override
	public boolean addItem(Salaries item) {
		DAOEmployee emp = new DAOEmployee();
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("INSERT INTO " + DAOSalaries.tName + " VALUES(?,?,?,?);");
			PreparedStatement maxKey = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("SELECT MAX(" + DAOEmployee.COLUMNS[0] + ") FROM " + DAOEmployee.tName + ";");
			s.setInt(2, item.getSalary());
			s.setDate(3, item.getfDate());
			s.setDate(4, item.gettDate());
			if (emp.getItemsByID(new Integer(item.getEmpNo())).size() == 0) {
				EmployeeFactory empF = (EmployeeFactory) TransferFactoryCreator.createBuilder(Employee.class);
				ResultSet res = maxKey.executeQuery();
				System.out.println("At first " + res.first());
				emp.addItem(empF.createFromInput(new String[] { "" + (res.getInt(1) + 1),
						Date.valueOf("1992-03-24").toString(), "Placeholder", "PlaceHolder", "M", "1992-03-24" }));
				s.setInt(1, res.getInt(1) + 1);
				res.close();
			} else {
				s.setInt(1, item.getEmpNo());
			}
			if (s != null)
				result = s.executeUpdate() == 1;
			DatabaseAccess.getInstance().closeConnection();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DatabaseAccess.getInstance().closeConnection();
		return result;
	}

	@Override
	public boolean removeItem(Salaries item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection().prepareStatement(
					"DELETE FROM " + DAOSalaries.tName + " WHERE " + DAOSalaries.COLUMNS[0] + " = ?;");
			s.setInt(1, item.getEmpNo());
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
	public boolean modifyItem(Salaries item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("UPDATE " + DAOSalaries.tName + " SET " + DAOSalaries.COLUMNS[1] + " = ?, "
							+ DAOSalaries.COLUMNS[3] + " = ? WHERE " + DAOSalaries.COLUMNS[0] + " = ? AND "
							+ DAOSalaries.COLUMNS[2] + " = ? ;");
			System.out.println(s.toString());
			s.setInt(1, item.getSalary());
			s.setDate(4, item.gettDate());
			s.setDate(2, item.getfDate());
			s.setInt(3, item.getEmpNo());
			System.out.println(s.toString());
			if (s != null) {
				int test = s.executeUpdate();
				System.out.println(test);
				result = test > 0;
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
	public ArrayList<Salaries> createList(int numRows) {
		ArrayList<Salaries> entryList = new ArrayList<>();
		try {
			ResultSet r = null;
			if (numRows == -1) {
				r = DatabaseAccess.getInstance().getConnection()
						.prepareStatement("SELECT * FROM " + DAOSalaries.tName + ";").executeQuery();
			} else {
				r = DatabaseAccess.getInstance().getConnection()
						.prepareStatement("SELECT * FROM " + DAOSalaries.tName + ";").executeQuery();
			}
			if (r != null) {
				SalariesFactory fact = (SalariesFactory) TransferFactoryCreator.createBuilder(Salaries.class);
				entryList = fact.createListFromResults(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.getInstance().closeConnection();
		return entryList;

	}

	@Override
	public <U> ArrayList<Salaries> getItemsByID(U id) {
		ArrayList<Salaries> result = new ArrayList<>();
		try {
			PreparedStatement s = DatabaseAccess.getInstance().getConnection().prepareStatement(
					"SELECT * FROM " + DAOSalaries.tName + " WHERE " + DAOSalaries.COLUMNS[0] + " = ?;");
			if (id instanceof Integer) {
				s.setInt(1, (int) id);
			} else {
				throw new IllegalArgumentException("Invalid Key Type");
			}
			System.out.println("Salary Statement " + s.toString());
			ResultSet r = s.executeQuery();
			if (r != null) {
				SalariesFactory fact = (SalariesFactory) TransferFactoryCreator.createBuilder(Salaries.class);
				result = fact.createListFromResults(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public static void main(String args[]) {
		DAOSalaries s = new DAOSalaries();
		SalariesFactory f = (SalariesFactory) TransferFactoryCreator.createBuilder(Salaries.class);
		System.out.println(s.addItem(f.createFromInput(new String[] {"1000","1999","1992-03-24","1992-03-24"})));
		Salaries temp = f.createFromInput(new String[] {"10001","1999","1992-03-24","1987-06-26"});
		temp.setSalary(24);
		System.out.println(s.modifyItem(temp));
		System.out.println(s.removeItem(temp));
	}
}
