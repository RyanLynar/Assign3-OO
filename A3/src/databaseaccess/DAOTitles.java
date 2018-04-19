package databaseaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import transferobj.Titles;

public class DAOTitles implements DAO<Titles> {
	public static String tName = "titles";
	public static String[] COLUMNS = new String[] { "emp_no", "title", "from_date", "to_date" };

	@Override
	public boolean addItem(Titles item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection().prepareStatement("INSERT INTO " +DAOTitles.tName + " VALUE(?,?,?);");
			ResultSet maxKey = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT MAX(" + DAOTitles.COLUMNS[0] + ") FROM " +DAOTitles.tName).executeQuery();
			maxKey.first();
			s.setInt(1, maxKey.getInt(1)+1);
			s.setString(2, item.getTitle());
			s.setDate(3, item.getfDate());
			s.setDate(4, item.gettDate());
			maxKey.close();
			if(s!=null) {
				result = s.executeUpdate() == 1;
				DatabaseAccess.getInstance().closeConnection();
				return result;
			}
		}catch(SQLException e) {
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
			s= DatabaseAccess.getInstance().getConnection().prepareStatement(
					"DELETE FROM " + DAOTitles.tName + " WHERE " +DAOTitles.COLUMNS[0] + " =?;");
			if(s!=null) {
				result = s.executeUpdate() >0;
				DatabaseAccess.getInstance().closeConnection();
				return result;
			}
		}
		DatabaseAccess.getInstance().closeConnection();
		return result;
	}

	@Override
	public boolean modifyItem(Titles item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Titles> createList(int numRows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Titles> getItemsByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
