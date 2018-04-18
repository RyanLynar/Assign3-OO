package databaseaccess;

import java.util.ArrayList;

import transferobj.TransferObject;

public abstract interface DAO<T> {
	public abstract boolean addItem(TransferObject item);

	public abstract boolean removeItem(TransferObject item);

	public abstract boolean modifyItem(TransferObject item);

	public abstract ArrayList<T> createList(int numRows);

	public abstract ArrayList<T> getItemsByID(int id);

}
