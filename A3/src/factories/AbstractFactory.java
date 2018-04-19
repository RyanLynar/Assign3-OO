package factories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

public abstract class AbstractFactory<T> implements Factory<T>{

	
	public abstract T createFromResults(ResultSet rs);

	public abstract T createFromInput(String[] input);

	public abstract ArrayList<T> createListFromResults(ResultSet r);
	
}
