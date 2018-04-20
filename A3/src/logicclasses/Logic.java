package logicclasses;

import java.util.ArrayList;

/**
 * 
 * @author Ryan Lynar
 *
 * @param <T>
 *            The type of TransferObject this Logic manipulates
 */
public interface Logic<T> {
	/**
	 * 
	 * @param itemToAdd
	 *            a String[] of the results of the form the user submitted
	 * @return Returns the success of the Database Query
	 */
	public abstract boolean add(String[] itemToAdd);

	/**
	 * 
	 * @param itemToRemove a String[] of the results of the form the user submitted
	 * @return Returns the success of the Database Query
	 */
	public abstract boolean remove(String[] itemToRemove);
	/**
	 * 
	 * @param itemToModify a String[] of the results of the form the user submitted;
	 * @return Returns the success or failure of a Database Query
	 */
	public abstract boolean modify(String[] itemToModify);
	/**
	 * 
	 * @param <U>
	 * @param id returns an ArrayList of items found with the given id
	 * @return
	 */
	public <U> ArrayList<T> getByID(U id);
	/**
	 * 
	 * @param rowNumber would be used to limit number of rows accessed
	 * @return an arraylist of the items in the database
	 */
	public ArrayList<T> getObjects(int rowNumber);

}
