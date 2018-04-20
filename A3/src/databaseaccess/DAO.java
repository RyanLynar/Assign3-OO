package databaseaccess;

import java.util.ArrayList;



public abstract interface DAO<T> {
	public static String tName = null;
	public static String[] COLUMNS = null;
	public abstract boolean addItem(T item);

	public abstract boolean removeItem(T item);

	public abstract boolean modifyItem(T item);

	public abstract ArrayList<T> createList(int numRows);

	public abstract <U> ArrayList<T> getItemsByID(U id) throws IllegalArgumentException;

}
