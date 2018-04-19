package factories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
/**
 * 
 * @author Ryan
 *
 * @param <T> Transfer Object type that this factory will be handling
 */
public interface Factory<T> {
	
	public T createFromResults(ResultSet rs);

	public T createFromInput(String[] input);

	public ArrayList<T> createListFromResults(ResultSet r);

}
