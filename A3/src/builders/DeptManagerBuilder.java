package builders;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import databaseaccess.DAODeptManager;
import databaseaccess.DAOEmployee;
import transferobj.DeptManager;
import transferobj.Employee;

public class DeptManagerBuilder implements AbstractBuilder<DeptManager> {
	private ArrayList<DeptManager> eList;

	public DeptManagerBuilder() {
		eList = new ArrayList<>();
	}

	private void setString(DeptManager newDM, String data, String cName) {
		newDM.setDeptNumber(data);
	}

	private void setInt(DeptManager newDM, int data, String cName) {
		newDM.setEmployeeNumber(data);
	}

	private void setDate(DeptManager newDM, Date data, String cName) {
		if (cName.equals("from_date")) {
			newDM.setFromDate(data);
		} else if (cName.equals("to_date")) {
			newDM.setToDate(data);
		}

	}

	@Override
	public void build(ResultSet r) {
		eList.clear();
		try {
			if (r.first()) {
				while (!r.isAfterLast()) {
					DeptManager entry = new DeptManager();
					for (int i = 0; i < r.getMetaData().getColumnCount(); i++) {
						if (r.getMetaData().getColumnType(i + 1) == Types.DATE) {
							if (DAODeptManager.COLUMNS[i].equals(r.getMetaData().getColumnName(i + 1))) {
								this.setDate((DeptManager) entry, r.getDate(i + 1),
										r.getMetaData().getColumnName(i + 1));
							}

						} else if (r.getMetaData().getColumnType(i + 1) == Types.CHAR) {
							if (DAODeptManager.COLUMNS[i].equals(r.getMetaData().getColumnName(i + 1))) {
								this.setString((DeptManager) entry, r.getString(i + 1),
										r.getMetaData().getColumnName(i + 1));
							}

						} else if (r.getMetaData().getColumnType(i + 1) == Types.INTEGER) {
							if (DAODeptManager.COLUMNS[i].equals(r.getMetaData().getColumnName(i + 1))) {
								this.setInt((DeptManager) entry, r.getInt(i + 1), r.getMetaData().getColumnName(i + 1));
							}
						}
					}
					eList.add(entry);
					r.next();
				}
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public ArrayList<DeptManager> returnList() {
		return eList;
	}

	@Override
	public void build(String[] input) {
		eList.clear();
		DeptManager entry = new DeptManager();
		entry.setEmployeeNumber(Integer.parseInt(input[0]));
		entry.setDeptNumber(input[1]);
		entry.setFromDate(Date.valueOf(input[2]));
		entry.setFromDate(Date.valueOf(input[3]));
		eList.add(entry);
	}

}