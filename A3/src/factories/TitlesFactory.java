package factories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import builders.TitlesBuilder;
import transferobj.Titles;

public class TitlesFactory extends AbstractFactory<Titles>{
	TitlesBuilder builder;

	@Override
	public Titles createFromResults(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Titles createFromMap(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Titles> createListFromResults(ResultSet r) {
		builder.build(r);
		return builder.returnList();
	}
	

}
