<<<<<<< HEAD
package databaseaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import transferobj.Departments;

public class DAODepartments implements DAO<Departments>{
	public static String TABLE_NAME = "departments";
	public static String[] COLUMNS = new String[] {"dept_no", "dept_name"};

	@Override
	public boolean addItem(Departments item) {
		boolean result = false;
		PreparedStatement s = null;
		
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("INSERT INTO "+ DAODepartments.TABLE_NAME + " VALES(?,?);");
			PreparedStatement maxKey = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("SELECT MAX(" +DAODepartments.COLUMNS+ ") FROM " +DAODepartments.TABLE_NAME+";");
			ResultSet res = maxKey.executeQuery();
			res.first();
			s.setInt(1, res.getInt(1) + 1);
			for(int i = 1; 1 < DAODepartments.COLUMNS.length; i++) {
				s.setString(i+1, item.getValues()[i]);
			}
			res.close();
			System.out.println(s.toString());
			if(s != null) {
				result = s.executeUpdate() == 1;
				DatabaseAccess.getInstance().closeConnection();
				return result;
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.getInstance().closeConnection();
		return result;		
	}

	@Override
	public boolean removeItem(Departments item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection().prepareStatement(
					"DELETE FROM " + DAODepartments.TABLE_NAME + " WHERE " + DAODepartments.COLUMNS[0] + " = ?;");
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
	public boolean modifyItem(Departments item) {
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("UPDATE " + DAODepartments.TABLE_NAME + " SET " + DAODepartments.COLUMNS[1] + "=?"
							+ " WHERE " + DAODepartments.COLUMNS[0] + " = ?;");

			for (int i = 1; i < DAOEmployee.COLUMNS.length; i++) {
				s.setString(i, item.getValues()[i]);
			}
			s.setString(2, item.getValues()[0]);

			if (s != null) {
				DatabaseAccess.getInstance().closeConnection();
				return 0 < s.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.getInstance().closeConnection();
		return false;
	}

	@Override
	public ArrayList<Departments> createList(int numRows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Departments> getItemsByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
=======
package databaseaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import transferobj.Departments;

public class DAODepartments implements DAO<Departments>{
	public static String TABLE_NAME = "departments";
	public static String[] COLUMNS = new String[] {"dept_no", "dept_name"};

	@Override
	public boolean addItem(Departments item) {
		boolean result = false;
		PreparedStatement s = null;
		
		try {
			s = DatabaseAccess.access.getConnection()
					.prepareStatement("INSERT INTO "+ DAODepartments.TABLE_NAME + " VALUES(?,?);");
			PreparedStatement maxKey = DatabaseAccess.access.getConnection()
					.prepareStatement("SELECT MAX(" +DAODepartments.COLUMNS[0]+ ") FROM " +DAODepartments.TABLE_NAME+";");
			ResultSet res = maxKey.executeQuery();
			res.first();
			s.setInt(1, res.getInt(1) + 1);
			s.setInt(2, item.getDeptName());
			res.close();
			
			System.out.println(s.toString());
			if(s != null) {
				result = s.executeUpdate() == 1;
				DatabaseAccess.access.closeConnection();
				return result;
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.access.closeConnection();
		return result;		
	}

	@Override
	public boolean removeItem(Departments item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.access.getConnection().prepareStatement(
					"DELETE FROM " + DAODepartments.TABLE_NAME + " WHERE " + DAODepartments.COLUMNS[0] + " = ?;");
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
	public boolean modifyItem(Departments item) {
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.access.getConnection()
					.prepareStatement("UPDATE " + DAODepartments.TABLE_NAME + " SET " + DAODepartments.COLUMNS[1] + "=?"
							+ " WHERE " + DAODepartments.COLUMNS[0] + " = ?;");
			
			s.setInt(1, item.getDeptName());
			s.setString(2, item.getValues()[0]);

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

	@Override
	public ArrayList<Departments> createList(int numRows) {
		ArrayList<Departments> entryList = new ArrayList<>();
		try {
			ResultSet r = null;
			if (numRows == -1) {
				r = DatabaseAccess.access.getConnection().prepareStatement("SELECT * FROM " + DAODepartments.TABLE_NAME + ";")
						.executeQuery();
			} else {
				r = DatabaseAccess.access.getConnection().prepareStatement("SELECT * FROM " + DAODepartments.TABLE_NAME + ";")
						.executeQuery();
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

	@Override
	public ArrayList<Departments> getItemsByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
>>>>>>> BetCuh
