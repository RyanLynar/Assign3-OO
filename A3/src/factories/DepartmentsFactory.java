package factories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import builders.DepartmentsBuilder;
import builders.EmployeeBuilder;
import transferobj.Departments;

public class DepartmentsFactory extends AbstractFactory<Departments>{
	DepartmentsBuilder builder;
	
	public DepartmentsFactory() {
		builder = new DepartmentsBuilder();
	}

	@Override
	public Departments createFromResults(ResultSet rs) {
		builder.build(rs);
		return builder.returnList().get(0);
	}

	@Override
	public Departments createFromMap(Map<String, String[]> map) {
		Departments result = new Departments();
		for (String item : map.get("departments")) {

		}
		return result;
	}

	@Override
	public ArrayList<Departments> createListFromResults(ResultSet r) {
		builder.build(r);
		return builder.returnList();
	}

}
