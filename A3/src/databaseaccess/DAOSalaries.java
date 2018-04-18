package databaseaccess;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


import transferobj.Salaries;

public class DAOSalaries implements DAO<Salaries> {
	public static String tName="salaries";
	public static String[] COLUMNS = new String[] {"emp_no","salary","from_date","to_date"};
	@Override
	public boolean addItem(Salaries item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection().prepareStatement("INSERT INTO " +DAOSalaries.tName + " VALUES(?,?,?,?);");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean removeItem(Salaries item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyItem(Salaries item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Salaries> createList(int numRows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Salaries> getItemsByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
