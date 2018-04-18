package transferobj;

import java.util.ArrayList;

public abstract interface TransferObject <T> {
	public abstract ArrayList<T> getTransferList();
	public abstract ArrayList<T> getTransferObjectByID(int id);
	public abstract boolean addItem(T toAdd);
	public abstract boolean removeItem(T toRemove);
	public abstract boolean modifyItem(T toModify);
	public abstract String[] getValues();
	public abstract void printAll();
}
