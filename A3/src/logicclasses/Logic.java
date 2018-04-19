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
	 * @param id
	 * @return
	 */
	public ArrayList<T> getByID(int id);
	/**
	 * 
	 * @param rowNumber
	 * @return
	 */
	public ArrayList<T> getObjects(int rowNumber);

}
