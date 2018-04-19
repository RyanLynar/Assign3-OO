package builders;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import databaseaccess.DAOEmployee;
import transferobj.Employee;

public class EmployeeBuilder implements AbstractBuilder<Employee> {
	private ArrayList<Employee> eList;

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

	public EmployeeBuilder() {
		eList = new ArrayList<Employee>();
	}

	@Override
	public void build(ResultSet r) {
		eList.clear();
		try {
			r.first();
			while (!r.isAfterLast()) {
				Employee entry = new Employee();
				for (int i = 0; i < r.getMetaData().getColumnCount(); i++) {
					if (r.getMetaData().getColumnType(i + 1) == Types.VARCHAR) {
						if (DAOEmployee.COLUMNS[i].equals(r.getMetaData().getColumnName(i + 1))) {
							setString(entry, r.getString(i + 1), r.getMetaData().getColumnName(i + 1));
						}
					} else if (r.getMetaData().getColumnType(i + 1) == Types.CHAR) {
						if (DAOEmployee.COLUMNS[i].equals(r.getMetaData().getColumnName(i + 1))) {
							this.setChar((Employee) entry, r.getString(i + 1), r.getMetaData().getColumnName(i + 1));
						}
					} else if (r.getMetaData().getColumnType(i + 1) == Types.INTEGER) {
						if (DAOEmployee.COLUMNS[i].equals(r.getMetaData().getColumnName(i + 1))) {
							this.setInt((Employee) entry, r.getInt(i + 1), r.getMetaData().getColumnName(i + 1));
						}
					} else if (r.getMetaData().getColumnType(i + 1) == Types.DATE) {
						if (DAOEmployee.COLUMNS[i].equals(r.getMetaData().getColumnName(i + 1))) {
							this.setDate((Employee) entry, r.getDate(i + 1), r.getMetaData().getColumnName(i + 1));
						}
					}
				}
				eList.add(entry);
				r.next();
			}
			r.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public ArrayList<Employee> returnList() {
		return eList;
	}

	@Override
	public void build(String[] input) {
		eList.clear();
		Employee entry = new Employee();
		entry.setEmpNumber(Integer.parseInt(input[0]));
		entry.setEmpBDate(Date.valueOf(input[1]));
		entry.setEmpFName(input[2]);
		entry.setEmpLName(input[3]);
		entry.setEmpGender(input[4]);
		entry.setEmpHDate(Date.valueOf(input[5]));
		eList.add(entry);
	}

}
