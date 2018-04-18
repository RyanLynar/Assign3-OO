package databaseaccess;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;


public class DAOEmployee extends DAO {
	protected static String tName = "employees";
	private int empNumber;
	private String empFName, empLName, empGender;
	private Date empBDate, empHDate;
	private ArrayList<DAO> empList;
 
	public DAOEmployee() {
		empList = new ArrayList<DAO>();
	}

	public int getEmpNumber() {
		return empNumber;
	}

	public String getEmpFName() {
		return empFName;
	}

	public String getEmpLName() {
		return empLName;
	}

	public String getEmpName() {
		return getEmpFName() + " " + getEmpLName();
	}

	public void setEmpNumber(int empNumber) {
		this.empNumber = empNumber;
	}

	public void setEmpFName(String empFName) {
		this.empFName = empFName;
	}

	public void setEmpLName(String empLName) {
		this.empLName = empLName;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public void setEmpBDate(Date empBDate) {
		this.empBDate = empBDate;
	}

	public void setEmpHDate(Date empHDate) {
		this.empHDate = empHDate;
	}

	public String getEmpGender() {
		return empGender;
	}

	public Date getEmpBDate() {
		return empBDate;
	}

	public Date getEmpHDate() {
		return empHDate;
	}
	public ArrayList<DAO> getEmpList(){
		return empList;
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "emp_no", "birth_date", "first_name", "last_name", "gender", "hire_date" };
	}

	public void printAll() {
		System.out.println(getEmpNumber() + " " + getEmpFName() + " " + getEmpLName() + " " + getEmpGender() + " "
				+ getEmpBDate() + " " + getEmpHDate());
	}

	@Override
	public void setString(String data, String cName) {
		if (cName.equals("first_name")) {
			setEmpFName(data);
		} else if (cName.equals("last_name")) {
			setEmpLName(data);
		}
	}

	@Override
	public void setChar(String data, String cName) {
		if (cName.equals("gender")) {
			setEmpGender(data);
		}
	}

	@Override
	public void setInt(int data, String cName) {
		if (cName.equals("emp_no")) {
			setEmpNumber(data);
		}

	}

	@Override
	public void setDate(Date data, String cName) {
		if (cName.equals("birth_date")) {
			setEmpBDate(data);
		} else if (cName.equals("hire_date")) {
			setEmpHDate(data);
		}

	}

	public String[] getColumnValues() {
		return new String[] { "" + getEmpNumber(), getEmpBDate().toString(), getEmpFName(), getEmpLName(),
				getEmpGender(), getEmpHDate().toString() };
	}

	@Override
	public boolean addItem(DAO itemToAdd) {
		boolean result = false;
		PreparedStatement s = null;
		try {

			if (itemToAdd instanceof DAOEmployee) {
				s = DatabaseAccess.access.getConnection()
						.prepareStatement("INSERT INTO " + DAOEmployee.tName + " VALUES(?,?,?,?,?,?);");
				PreparedStatement maxKey = DatabaseAccess.access.getConnection().prepareStatement(
						"SELECT MAX(" + itemToAdd.getColumnNames()[0] + ") FROM " + DAOEmployee.tName + ";");
				ResultSet res = maxKey.executeQuery();
				res.first();
				s.setInt(1, res.getInt(1) + 1);
				for (int i = 1; i < itemToAdd.getColumnNames().length; i++) {
					s.setString(i + 1, itemToAdd.getColumnValues()[i]);
				}
			}
			System.out.println(s.toString());
			if (s != null) {
				return s.executeUpdate() == 1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;

	}

	@Override
	public boolean removeItem(DAO itemToRemove) {
		boolean result = false;
		PreparedStatement s = null;
		try {

			if (itemToRemove instanceof DAOEmployee) {
				s = DatabaseAccess.access.getConnection().prepareStatement(
						"DELETE FROM " + DAOEmployee.tName + " WHERE " + itemToRemove.getColumnNames()[0] + " = "
								+ ((DAOEmployee) itemToRemove).getEmpNumber());
			}
			if (s != null) {
				return s.executeUpdate() == 1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override

	public boolean modifyItem(DAO itemToChange) {
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.access.getConnection()
					.prepareStatement("UPDATE " + DAOEmployee.tName + " SET " + itemToChange.getColumnNames()[1] + "=?,"
							+ itemToChange.getColumnNames()[2] + "=?," + itemToChange.getColumnNames()[3] + "=?,"
							+ itemToChange.getColumnNames()[4] + "=?," + itemToChange.getColumnNames()[5] + "=? WHERE "
							+ itemToChange.getColumnNames()[0] + " = ?;");

			for (int i = 1; i < itemToChange.getColumnNames().length; i++) {
				s.setString(i, itemToChange.getColumnValues()[i]);
			}
			s.setString(6, itemToChange.getColumnValues()[0]);

			if (s != null) {
				return 0 < s.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public void createList(ArrayList<DAO> entryList, int numRows) {
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
				r.first();
				while (!r.isAfterLast()) {
					DAO entry = new DAOEmployee();
					for (int i = 0; i < r.getMetaData().getColumnCount(); i++) {
						if (r.getMetaData().getColumnType(i + 1) == Types.VARCHAR) {
							if (entry.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
								entry.setString(r.getString(i + 1), r.getMetaData().getColumnName(i + 1));
							}
						} else if (r.getMetaData().getColumnType(i + 1) == Types.CHAR) {
							if (entry.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
								entry.setChar(r.getString(i + 1), r.getMetaData().getColumnName(i + 1));
							}
						} else if (r.getMetaData().getColumnType(i + 1) == Types.INTEGER) {
							if (entry.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
								entry.setInt(r.getInt(i + 1), r.getMetaData().getColumnName(i + 1));
							}
						} else if (r.getMetaData().getColumnType(i + 1) == Types.DATE) {
							if (entry.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
								entry.setDate(r.getDate(i + 1), r.getMetaData().getColumnName(i + 1));
							}
						}

					}
					entryList.add(entry);
					r.next();
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}
