package factories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import builders.DeptManagerBuilder;
import transferobj.DeptManager;

public class DeptManagerFactory extends AbstractFactory<DeptManager>{
	DeptManagerBuilder builder;
	
	public DeptManagerFactory() {
		builder = new DeptManagerBuilder();
	}

	@Override
	public DeptManager createFromResults(ResultSet rs) {
		builder.build(rs);
		return builder.returnList().get(0);
	}

	@Override
	public DeptManager createFromMap(Map<String, String[]> map) {
		DeptManager result = new DeptManager();
		return null;
	}

	@Override
	public ArrayList<DeptManager> createListFromResults(ResultSet r) {
		builder.build(r);
		return builder.returnList();
	}

}
