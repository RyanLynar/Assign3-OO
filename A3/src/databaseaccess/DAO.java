package databaseaccess;

import java.sql.Date;
import java.util.ArrayList;

public abstract class DAO {
	protected static String tName;
	
	public String getTName() {
		return tName;
	}
	public abstract String[] getColumnNames();
	public abstract void printAll();
	public abstract void setString(String data, String cName);
	public abstract void setInt(int data, String cName);
	public abstract void setDate(Date data, String cName);
	public abstract void setChar(String data, String cName);
	public abstract String[] getColumnValues();
	public abstract boolean addItem(DAO item);
	public abstract boolean removeItem(DAO item);
	public abstract boolean modifyItem(DAO item);
	public abstract void createList(ArrayList<DAO> entryList,int numRows);
	public abstract ArrayList<DAO> getItemsByID(int id);

}
