package builders;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import databaseaccess.DAODeptEmployee;
import transferobj.DeptEmployee;

public class DeptEmployeeBuilder implements AbstractBuilder<DeptEmployee> {
	ArrayList<DeptEmployee> eList;

	private void setString(DeptEmployee newEntry, String data, String cName) {
		if (cName.equals("dept_no")) {
			newEntry.setDeptID(data);
		}
	}

	private void setInt(DeptEmployee newEntry, int data, String cName) {
		if (cName.equals("emp_no")) {
			newEntry.setEmpID(data);
		}
	}

	private void setDate(DeptEmployee newEntry, Date data, String cName) {
		if (cName.equals("from_date")) {
			newEntry.setfDate(data);
		} else if (cName.equals("to_date")) {
			newEntry.settDate(data);
		}
	}

	public DeptEmployeeBuilder() {
		eList = new ArrayList<>();
	}

	@Override
	public void build(ResultSet r) {
		eList.clear();
		try {
			while (!r.isAfterLast()) {
				DeptEmployee entry = new DeptEmployee();
				for (int i = 0; i < r.getMetaData().getColumnCount(); i++) {
					if (r.getMetaData().getColumnType(i + 1) == Types.VARCHAR) {
						if (DAODeptEmployee.COLUMNS[i].equals(r.getMetaData().getColumnName(i + 1))) {
							setString(entry, r.getString(i + 1), r.getMetaData().getColumnName(i + 1));
						}
					} else if (r.getMetaData().getColumnType(i + 1) == Types.INTEGER) {
						if (DAODeptEmployee.COLUMNS[i].equals(r.getMetaData().getColumnName(i + 1))) {
							setInt(entry, r.getInt(i + 1), r.getMetaData().getColumnName(i + 1));
						}
					} else if (r.getMetaData().getColumnType(i + 1) == Types.DATE) {
						if (DAODeptEmployee.COLUMNS[i].equals(r.getMetaData().getColumnName(i + 1))) {
							setDate(entry, r.getDate(i + 1), r.getMetaData().getColumnName(i + 1));
						}
					}
					
				}
			eList.add(entry);
			r.next();
			}
		} catch (

		SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	


	@Override
	public ArrayList<DeptEmployee> returnList() {
		return eList;
	}

	@Override
	public void build(String[] input) {
		eList.clear();
		DeptEmployee entry = new DeptEmployee();
		entry.setEmpID(Integer.parseInt(input[0]));
		entry.setDeptID(input[1]);
		entry.setfDate(Date.valueOf(input[2]));
		entry.settDate(Date.valueOf(input[3]));
		
	}
	
}
