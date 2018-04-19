package factories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import builders.TitlesBuilder;
import transferobj.Titles;

public class TitlesFactory extends AbstractFactory<Titles>{
	TitlesBuilder builder;
	
	public TitlesFactory() {
		builder = new TitlesBuilder();
	}
	@Override
	public Titles createFromResults(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Titles createFromInput(String[] item) {
		builder.build(item);
		return builder.returnList().get(0);
	}

	@Override
	public ArrayList<Titles> createListFromResults(ResultSet r) {
		System.out.println("Create Result");
		builder.build(r);
		return builder.returnList();
	}
	

}
