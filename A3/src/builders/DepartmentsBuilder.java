package builders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import transferobj.Departments;
import transferobj.Employee;

public class DepartmentsBuilder implements AbstractBuilder<Departments>{
	private ArrayList<Departments> eList;
	
	public DepartmentsBuilder() {
		eList = new ArrayList<Departments>();
	}
	
	public void setString(Departments newEmp, String data, String cName) {
		if (cName.equals("dept_no")) {
			newEmp.setDeptNumber(data);
		} else if (cName.equals("dept_name")) {
			newEmp.setDeptName(data);
		}
	}
	
	

	@Override
	public void build(ResultSet r) {
		eList.clear();
		try {
			r.first();
			while(!r.isAfterLast()) {
				Departments entry = new Departments();
				
				for(int i = 0;i < r.getMetaData().getColumnCount(); i++) {
					this.setString(entry, r.getString(i + 1), r.getMetaData().getColumnName(i + 1));
				}
				eList.add(entry);
				r.next();
			}
			r.close();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public ArrayList<Departments> returnList() {
		return eList;
	}

}
