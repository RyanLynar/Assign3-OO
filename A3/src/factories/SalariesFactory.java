package factories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import builders.SalariesBuilder;
import transferobj.Salaries;

public class SalariesFactory extends AbstractFactory<Salaries> {
	SalariesBuilder builder;
	public SalariesFactory() {
		builder = new SalariesBuilder();
	}
	@Override
	public Salaries createFromResults(ResultSet rs) {
		builder.build(rs);
		return builder.returnList().get(0);
	}

	@Override
	public Salaries createFromInput(String[] input) {
		builder.build(input);
		return builder.returnList().get(0);
	}

	@Override
	public ArrayList<Salaries> createListFromResults(ResultSet r) {
		builder.build(r);
		return builder.returnList();
	}

}
