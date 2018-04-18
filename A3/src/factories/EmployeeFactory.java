package factories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import builders.EmployeeBuilder;
import transferobj.Employee;

public class EmployeeFactory extends AbstractFactory<Employee> {
	EmployeeBuilder builder;

	public EmployeeFactory() {
		builder = new EmployeeBuilder();
	}

	@Override
	public Employee createFromResults(ResultSet r) {
		builder.build(rs);
		return builder.returnList().get(0);
	}

	@Override
	public Employee createFromMap(Map<String, String[]> map) {
		Employee result = new Employee();
		for (String item : map.get("employee")) {

		}
		return result;
	}

	@Override
	public ArrayList<Employee> createListFromResults(ResultSet r) {
		builder.build(r);
		return builder.returnList();
	}

}
