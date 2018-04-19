package builders;

import java.sql.ResultSet;
import java.util.ArrayList;
/**
 * 
 * @author Ryan Lynar
 *
 * @param <T> Defines the subtype of the builder, which is to say the TransferObject that this class will be building
 * 
 * 
 */
public interface AbstractBuilder<T> {
	/**
	 * 
	 * @param r Represents the ResultSet of a given Database Query
	 */
	public abstract void build(ResultSet r);
	/**
	 * 
	 * @param input Represents the input from the user on the client side
	 */
	public abstract void build(String[] input);
	
	/**
	 * 
	 * @return ArrayList<T> returns a list of items created throughout the build process
	 */
	public ArrayList<T> returnList();
}
