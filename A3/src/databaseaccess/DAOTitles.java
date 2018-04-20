package databaseaccess;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factories.TitlesFactory;
import factories.EmployeeFactory;
import factories.TransferFactoryCreator;
import transferobj.Titles;
import transferobj.Employee;

public class DAOTitles implements DAO<Titles> {
	public static String tName = "titles";
	public static String[] COLUMNS = new String[] { "emp_no", "title", "from_date", "to_date" };

	@Override
	public boolean addItem(Titles item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("INSERT INTO " + DAOTitles.tName + " VALUE(?,?,?,?);");
			ResultSet maxKey = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("SELECT MAX(" + DAOEmployee.COLUMNS[0] + ") FROM " + DAOEmployee.tName)
					.executeQuery();
			maxKey.first();
			s.setString(2, item.getTitle());
			s.setDate(3, item.getfDate());
			s.setDate(4, item.gettDate());
			DAOEmployee emp = new DAOEmployee();
			EmployeeFactory empF = (EmployeeFactory) TransferFactoryCreator.createBuilder(Employee.class);
			if (emp.getItemsByID(new Integer(item.getEmpNo())).size() == 0) {
				emp.addItem(empF.createFromInput(new String[] { "" + (maxKey.getInt(1) + 1),
						Date.valueOf("1992-03-24").toString(), "PlaceHolder", "PlaceHolder", "M", "1992-03-24" }));
					s.setInt(1, maxKey.getInt(1)+1);
				maxKey.close();
			} else {
				s.setInt(1, item.getEmpNo());
			}
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
	public boolean removeItem(Titles item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("DELETE FROM " + DAOTitles.tName + " WHERE " + DAOTitles.COLUMNS[0] + " =?;");
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
	public boolean modifyItem(Titles item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection().prepareStatement(
					"UPDATE " + DAOTitles.tName + " SET " + DAOTitles.COLUMNS[3] + " = ? WHERE " + DAOTitles.COLUMNS[2] + " = ? AND "
							+ DAOTitles.COLUMNS[1] + " = ? AND " + DAOTitles.COLUMNS[0] + "=?;");
			System.out.println(s.toString());
			s.setString(3, item.getTitle());
			s.setDate(2, item.getfDate());
			s.setDate(1, item.gettDate());
			s.setInt(4, item.getEmpNo());
			result = s.executeUpdate() > 0;
			DatabaseAccess.getInstance().closeConnection();
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	@Override
	public ArrayList<Titles> createList(int numRows) {
		ArrayList<Titles> entryList = new ArrayList<>();
		try {
			ResultSet r = null;
			r = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT * FROM " + DAOTitles.tName + ";")
					.executeQuery();
			TitlesFactory fact = (TitlesFactory) TransferFactoryCreator.createBuilder(Titles.class);
			entryList = fact.createListFromResults(r);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return entryList;
	}

	@Override
	public <U> ArrayList<Titles> getItemsByID(U id) {
		ArrayList<Titles> entryList = new ArrayList<>();
		try {
			PreparedStatement s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("SELECT * FROM " + DAOTitles.tName + " WHERE " + DAOTitles.COLUMNS[0] + " = ?;");
			if (id instanceof Integer) {
				s.setInt(1, (int) id);
			} else {
				throw new IllegalArgumentException("Invalid Key Type");
			}
			System.out.println("Titles " + s.toString());
			ResultSet r = s.executeQuery();
			TitlesFactory fact = (TitlesFactory) TransferFactoryCreator.createBuilder(Titles.class);
			entryList = fact.createListFromResults(r);
			DatabaseAccess.getInstance().closeConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return entryList;
	}
	public static void main(String[] args) {
		DAOTitles t = new DAOTitles();
		ArrayList<Titles> tList = t.getItemsByID(10001);
		tList.get(0).settDate(Date.valueOf("1992-03-24"));
		System.out.println(t.modifyItem(tList.get(0)));
		
	}

}
