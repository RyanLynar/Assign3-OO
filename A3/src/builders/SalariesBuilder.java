package builders;

import java.sql.ResultSet;
import java.util.ArrayList;

import transferobj.Salaries;

public class SalariesBuilder implements AbstractBuilder<Salaries> {
	ArrayList<Salaries> eList;

	public SalariesBuilder() {
		eList = new ArrayList<>();
	}

	@Override
	public void build(ResultSet r) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Salaries> returnList() {
		return eList;
	}

}
