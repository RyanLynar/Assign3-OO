package databaseaccess;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import transferobj.DeptEmployee;

public class DAODeptEmployee implements DAO<DeptEmployee> {
	public static String tName = "dept_emp";
	public static String[] COLUMNS = new String[] { "emp_no", "dept_no", "from_date", "to_date" };

	@Override
	public boolean addItem(DeptEmployee item) {
		boolean result = false;
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.access.getConnection()
					.prepareStatement("INSERT INTO " + DAODeptEmployee.tName + " VALUES(?,?,?,?);");
			for (int i = 0; i < DAODeptEmployee.COLUMNS.length; i++) {
				s.setString(i, item.getValues()[i]);
			}
			result = s.executeUpdate() == 1;
			DatabaseAccess.access.closeConnection();
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
					s = DatabaseAccess.access.getConnection().prepareStatement("DELETE FROM " + DAODeptEmployee.tName +
							" WHERE " + DAODeptEmployee.COLUMNS[0] + " = ? AND "+DAODeptEmployee.COLUMNS[1]+" =?;");
					s.setInt(1, item.getEmpID());		
					s.setString(2, item.getDeptID());
					
					result = s.executeUpdate() >0;
					DatabaseAccess.access.closeConnection();
					return result;
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
		return result;
	}

	@Override
	public boolean modifyItem(DeptEmployee item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<DeptEmployee> createList(int numRows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DeptEmployee> getItemsByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
