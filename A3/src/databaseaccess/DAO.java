package databaseaccess;

import java.util.ArrayList;


/**
 * 
 * @author ryan
 *
 * @param <T> Refers to the type of Transfer Object the data access object will work with
 */
public abstract interface DAO<T> {
	/**
	 * A Static reference to the table name
	 */
	public static String tName = null;
	/**
	 * a static reference to the columns in the Database
	 */
	public static String[] COLUMNS = null;
	/**
	 * 
	 * @param item the item to add to the database
	 * @return if the add or removal was successful (Operates on all tables that the item takes part in as a foreign key)
	 */
	public abstract boolean addItem(T item);

	/**
	 * 
	 * @param item the itme to remove from the database
	 * @return if the removal of the item was successful (Operates on all tables that the item has a foreign key requirement)
	 */
	public abstract boolean removeItem(T item);
	/**
	 * 
	 * @param item the item to modify in the database
	 * @return if the modification of the item was successful
	 */
	public abstract boolean modifyItem(T item);
	/**
	 * 
	 * @param numRows depreceated, would limit the amount of rows returned by the query
	 * @return an arrayList of the TransferObject that this gets back from the database
	 */
	public abstract ArrayList<T> createList(int numRows);
	/**
	 * 
	 * @param id of the item that we are looking for in the database
	 * @return an arraylist of items which match the given id, it's important to note that id is of type U, because for items like the DepartmentManager, and DepartmentEmployee the id may be the departmentID rather than a numeric id
	 * 
	 * @throws IllegalArgumentException if you try to pass a key that does not fit into the key types of the database, in this case an integer, or a string.
	 */
	public abstract <U> ArrayList<T> getItemsByID(U id) throws IllegalArgumentException;

}
