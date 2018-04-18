package transferobj;

import java.util.ArrayList;

public abstract class TransferObject {
	public abstract ArrayList<TransferObject> getTransferList();
	public abstract ArrayList<TransferObject> getTransferObjectByID(int id);
	public abstract boolean addItem(TransferObject toAdd);
	public abstract boolean removeItem(TransferObject toRemove);
	public abstract boolean modifyItem(TransferObject toModify);
}
