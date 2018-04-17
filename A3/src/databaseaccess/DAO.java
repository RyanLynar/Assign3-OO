package databaseaccess;

import java.sql.Date;

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

}
