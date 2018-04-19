package builders;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import databaseaccess.DAOTitles;
import transferobj.Titles;

public class TitlesBuilder implements AbstractBuilder<Titles> {
	private ArrayList<Titles> eList;

	public TitlesBuilder() {
		eList = new ArrayList<>();
	}

	private void setString(Titles newEntry, String data, String cName) {
		if (cName.equals("title")) {
			newEntry.setTitle(data);
		}

	}

	private void setInt(Titles newEntry, int data, String cName) {
		if (cName.equals("emp_no")) {
			newEntry.setEmpNo(data);
		}
	}

	private void setDate(Titles newEntry, Date data, String cName) {
		if (cName.equals("from_date'")) {
			newEntry.setfDate(data);
		} else if (cName.equals("start_date")) {
			newEntry.settDate(data);
		}
	}

	@Override
	public void build(ResultSet r) {
		eList.clear();
		try {

			 r.first();
				while (!r.isAfterLast()) {
					Titles entry = new Titles();
					for (int i = 0; i < r.getMetaData().getColumnCount(); i++) {
						if (DAOTitles.COLUMNS[i] == r.getMetaData().getColumnName(i + 1)) {
							if (r.getMetaData().getColumnType(i + 1) == Types.VARCHAR) {
								setString(entry, r.getString(i + 1), r.getMetaData().getColumnName(i + 1));
							}
						} else if (DAOTitles.COLUMNS[i] == r.getMetaData().getColumnName(i + 1)) {
							if (r.getMetaData().getColumnType(i + 1) == Types.INTEGER) {
								setInt(entry, r.getInt(i + 1), r.getMetaData().getColumnName(i + 1));
							} else if (DAOTitles.COLUMNS[i] == r.getMetaData().getColumnName(i + 1)) {
								if (r.getMetaData().getColumnType(i + 1) == Types.DATE) {
									setDate(entry, r.getDate(i + 1), r.getMetaData().getColumnName(i + 1));
								}
							}
						}
					}
					eList.add(entry);
					r.next();
				}

			
			System.out.println("After loop");
			r.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Titles> returnList() {
		return eList;
	}

	@Override
	public void build(String[] input) {
		eList.clear();
		Titles item = new Titles();
		item.setEmpNo(Integer.parseInt(input[0]));
		item.setTitle(input[1]);
		item.setfDate(Date.valueOf(input[2]));
		item.settDate(Date.valueOf(input[3]));
		eList.add(item);
	}

}
