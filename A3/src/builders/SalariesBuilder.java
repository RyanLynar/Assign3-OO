package builders;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import databaseaccess.DAOSalaries;
import transferobj.Salaries;

public class SalariesBuilder implements AbstractBuilder<Salaries> {
	ArrayList<Salaries> eList;

	public SalariesBuilder() {
		eList = new ArrayList<>();
	}

	private void setDate(Salaries newEntry, Date data, String cName) {
		if (cName.equals("to_date")) {
			newEntry.settDate(data);
		} else if (cName.equals("from_date")) {
			newEntry.settDate(data);
		}
	}

	private void setInt(Salaries newEntry, int data, String cName) {
		if (cName.equals("emp_no")) {
			newEntry.setEmpNo(data);
		} else if (cName.equals("salary")) {
			newEntry.setSalary(data);
		}
	}

	@Override
	public void build(ResultSet r) {
		eList.clear();
		try {
			r.first();
			while (!r.isAfterLast()) {
				Salaries entry = new Salaries();
				for (int i = 0; i < r.getMetaData().getColumnCount(); i++) {
					if (r.getMetaData().getColumnType(i + 1) == Types.INTEGER) {
						if (DAOSalaries.COLUMNS[i].equals(r.getMetaData().getColumnName(i + 1))) {
							setInt(entry, r.getInt(i + 1), r.getMetaData().getColumnName(i + 1));
						}
					} else if (r.getMetaData().getColumnType(i + 1) == Types.DATE) {
						if (DAOSalaries.COLUMNS[i].equals(r.getMetaData().getColumnName(i + 1))) {
							setDate(entry, r.getDate(i + 1), r.getMetaData().getColumnName(i + 1));
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
	public ArrayList<Salaries> returnList() {
		return eList;
	}

	@Override
	public void build(String[] input) {
		Salaries entry = new Salaries();
		entry.setEmpNo(Integer.parseInt(input[0]));
		entry.setSalary(Integer.parseInt(input[1]));
		entry.setfDate(Date.valueOf(input[2]));
		entry.settDate(Date.valueOf(input[3]));
	}

}
