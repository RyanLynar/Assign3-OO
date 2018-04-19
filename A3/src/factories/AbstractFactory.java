package factories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
/**
 * 
 * @author Ryan Lynar
 *
 * @param <T> Specifies the Type of object that will be created by the given factory.
 */
public abstract class AbstractFactory<T> implements Factory<T>{

	/**
	 * @param rs The given {@link java.sql.ResultSetk} from a database query
	 */
	public abstract T createFromResults(ResultSet rs);
	/**
	 *@param input The input sent in from the client 
	 */
	public abstract T createFromInput(String[] input);

	/**
	 * @Param r The given ResultSet
	 */
	public abstract ArrayList<T> createListFromResults(ResultSet r);
	
}
