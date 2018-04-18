package transferobj;

import java.util.ArrayList;

public abstract interface TransferObject {
	public abstract ArrayList<TransferObject> getTransferList();
	public abstract ArrayList<TransferObject> getTransferObjectByID(int id);
	public abstract boolean addItem(TransferObject toAdd);
	public abstract boolean removeItem(TransferObject toRemove);
	public abstract boolean modifyItem(TransferObject toModify);
	public abstract String[] getValues();
	public abstract void printAll();
}
