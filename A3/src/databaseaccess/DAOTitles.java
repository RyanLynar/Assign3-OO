package databaseaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factories.TitlesFactory;
import factories.TransferFactoryCreator;
import transferobj.Titles;

public class DAOTitles implements DAO<Titles> {
	public static String tName = "titles";
	public static String[] COLUMNS = new String[] { "emp_no", "title", "from_date", "to_date" };

	@Override
	public boolean addItem(Titles item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("INSERT INTO " + DAOTitles.tName + " VALUE(?,?,?);");
			ResultSet maxKey = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("SELECT MAX(" + DAOTitles.COLUMNS[0] + ") FROM " + DAOTitles.tName)
					.executeQuery();
			maxKey.first();
			s.setInt(1, maxKey.getInt(1) + 1);
			s.setString(2, item.getTitle());
			s.setDate(3, item.getfDate());
			s.setDate(4, item.gettDate());
			maxKey.close();
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
					"UPDATE " + DAOTitles.tName + " SET " + DAOTitles.COLUMNS[1] + " =?" + DAOTitles.COLUMNS[2] + " =?"
							+ DAOTitles.COLUMNS[3] + " =? WHERE " + DAOTitles.COLUMNS[0] + "=?;");
			s.setString(1, item.getTitle());
			s.setDate(2, item.getfDate());
			s.setDate(3, item.gettDate());
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

}
