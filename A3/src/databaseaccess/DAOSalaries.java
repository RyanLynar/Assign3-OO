package databaseaccess;

import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import transferobj.Salaries;

public class DAOSalaries implements DAO<Salaries> {

	@Override
	public boolean addItem(Salaries item) {
		boolean result = false;
		PreparedStatement s = null;
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
