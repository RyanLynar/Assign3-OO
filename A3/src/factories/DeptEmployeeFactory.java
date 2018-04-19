package factories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import builders.DeptEmployeeBuilder;
import transferobj.DeptEmployee;

public class DeptEmployeeFactory extends AbstractFactory<DeptEmployee> {
	private DeptEmployeeBuilder deBuilder;
	public DeptEmployeeFactory() {
		deBuilder = new DeptEmployeeBuilder();
	}

	@Override
	public DeptEmployee createFromResults(ResultSet rs) {
		deBuilder.build(rs);
		return deBuilder.returnList().get(0);
		//TODO may remove this function
	}

	@Override
	public DeptEmployee createFromInput(String[] input) {
		deBuilder.build(input);
		return deBuilder.returnList().get(0);
	}

	@Override
	public ArrayList<DeptEmployee> createListFromResults(ResultSet r) {
		deBuilder.build(r);
		return deBuilder.returnList();
	}

}
