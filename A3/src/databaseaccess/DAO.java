package databaseaccess;

import java.sql.Date;
import java.util.ArrayList;

import transferobj.TransferObject;

public abstract interface DAO {
	public abstract String[] getColumnNames();
	public abstract boolean addItem(TransferObject item);
	public abstract boolean removeItem(TransferObject item);
	public abstract boolean modifyItem(TransferObject item);
	public abstract ArrayList<TransferObject> createList(int numRows);
	public abstract ArrayList<TransferObject> getItemsByID(int id);

}
