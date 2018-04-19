package factories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

public interface Factory<T> {
	public T createFromResults(ResultSet rs);

	public T createFromInput(String[] input);

	public ArrayList<T> createListFromResults(ResultSet r);

}
