package factories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

public interface Factory<T> {
	public T createFromResults(ResultSet rs);

	public T createFromMap(Map<String, String[]> map);

	public ArrayList<T> createListFromResults(ResultSet r);

}
