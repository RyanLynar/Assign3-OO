package databaseaccess;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import transferobj.Employee;
import transferobj.TransferObject;

public class DAOEmployee implements DAO {
	static String tName = "employees";

	@Override
	public String[] getColumnNames() {
		return new String[] { "emp_no", "birth_date", "first_name", "last_name", "gender", "hire_date" };
	}

	public void setString(Employee newEmp, String data, String cName) {
		if (cName.equals("first_name")) {
			newEmp.setEmpFName(data);
		} else if (cName.equals("last_name")) {
			newEmp.setEmpLName(data);
		}
	}

	public void setChar(Employee newEmp, String data, String cName) {
		if (cName.equals("gender")) {
			newEmp.setEmpGender(data);
		}
	}

	public void setInt(Employee newEmp, int data, String cName) {
		if (cName.equals("emp_no")) {
			newEmp.setEmpNumber(data);
		}

	}

	public void setDate(Employee newEmp, Date data, String cName) {
		if (cName.equals("birth_date")) {
			newEmp.setEmpBDate(data);
		} else if (cName.equals("hire_date")) {
			newEmp.setEmpHDate(data);
		}

	}

	@Override
	public boolean addItem(TransferObject itemToAdd) {
		boolean result = false;
		PreparedStatement s = null;
		try {

			if (itemToAdd instanceof Employee) {
				s = DatabaseAccess.access.getConnection()
						.prepareStatement("INSERT INTO " + DAOEmployee.tName + " VALUES(?,?,?,?,?,?);");
				PreparedStatement maxKey = DatabaseAccess.access.getConnection().prepareStatement(
						"SELECT MAX(" + this.getColumnNames()[0] + ") FROM " + DAOEmployee.tName + ";");
				ResultSet res = maxKey.executeQuery();
				res.first();
				s.setInt(1, res.getInt(1) + 1);
				for (int i = 1; i < this.getColumnNames().length; i++) {
					s.setString(i + 1, itemToAdd.getValues()[i]);
				}
				res.close();
			}
			System.out.println(s.toString());
			if (s != null) {
				DatabaseAccess.access.closeConnection();
				return s.executeUpdate() == 1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.access.closeConnection();
		return result;

	}

	public boolean removeItem(TransferObject itemToRemove) {
		boolean result = false;
		PreparedStatement s = null;
		try {

			if (itemToRemove instanceof Employee) {
				s = DatabaseAccess.access.getConnection().prepareStatement("DELETE FROM " + DAOEmployee.tName
						+ " WHERE " + this.getColumnNames()[0] + " = " + ((Employee) itemToRemove).getEmpNumber());
			}
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

	public boolean modifyItem(TransferObject itemToModify) {
		PreparedStatement s = null;
		try {
			s = DatabaseAccess.access.getConnection()
					.prepareStatement("UPDATE " + DAOEmployee.tName + " SET " + this.getColumnNames()[1] + "=?,"
							+ this.getColumnNames()[2] + "=?," + this.getColumnNames()[3] + "=?,"
							+ this.getColumnNames()[4] + "=?," + this.getColumnNames()[5] + "=? WHERE "
							+ this.getColumnNames()[0] + " = ?;");

			for (int i = 1; i < this.getColumnNames().length; i++) {
				s.setString(i, itemToModify.getValues()[i]);
			}
			s.setString(6, itemToModify.getValues()[0]);

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

	public ArrayList<TransferObject> createList(int numRows) {
		ArrayList<TransferObject> entryList = new ArrayList<>();
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
					TransferObject entry = (TransferObject) new Employee();
					for (int i = 0; i < r.getMetaData().getColumnCount(); i++) {
						if (r.getMetaData().getColumnType(i + 1) == Types.VARCHAR) {
							if (this.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
								this.setString((Employee) entry, r.getString(i + 1),
										r.getMetaData().getColumnName(i + 1));
							}
						} else if (r.getMetaData().getColumnType(i + 1) == Types.CHAR) {
							if (this.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
								this.setChar((Employee) entry, r.getString(i + 1),
										r.getMetaData().getColumnName(i + 1));
							}
						} else if (r.getMetaData().getColumnType(i + 1) == Types.INTEGER) {
							if (this.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
								this.setInt((Employee) entry, r.getInt(i + 1), r.getMetaData().getColumnName(i + 1));
							}
						} else if (r.getMetaData().getColumnType(i + 1) == Types.DATE) {
							if (this.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
								this.setDate((Employee) entry, r.getDate(i + 1), r.getMetaData().getColumnName(i + 1));
							}
						}
					}
					entryList.add(entry);
					r.next();
				}
			}
			r.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.access.closeConnection();
		return entryList;

	}

	@Override
	public ArrayList<TransferObject> getItemsByID(int id) {
		ArrayList<TransferObject> result = new ArrayList<>();
		try {
			PreparedStatement s = DatabaseAccess.access.getConnection().prepareStatement(
					"SELECT * FROM " + DAOEmployee.tName + " WHERE " + this.getColumnNames()[0] + " = ?");
			s.setInt(1, id);
			ResultSet r = s.executeQuery();
			r.first();
			while (!r.isAfterLast()) {
				Employee entry = new Employee();
				for (int i = 0; i < r.getMetaData().getColumnCount(); i++) {
					if (this.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
						this.setString(entry, r.getString(i + 1), r.getMetaData().getColumnName(i + 1));
					} else if (r.getMetaData().getColumnType(i + 1) == Types.CHAR) {
						if (this.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
							this.setChar(entry, r.getString(i + 1), r.getMetaData().getColumnName(i + 1));
						}
					} else if (r.getMetaData().getColumnType(i + 1) == Types.INTEGER) {
						if (this.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
							this.setInt(entry, r.getInt(i + 1), r.getMetaData().getColumnName(i + 1));
						}
					} else if (r.getMetaData().getColumnType(i + 1) == Types.DATE) {
						if (this.getColumnNames()[i].equals(r.getMetaData().getColumnName(i + 1))) {
							this.setDate(entry, r.getDate(i + 1), r.getMetaData().getColumnName(i + 1));
						}
					}
				}
				result.add((TransferObject) entry);
				r.next();
			}
			r.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		DatabaseAccess.access.closeConnection();
		return result;
	}

}
