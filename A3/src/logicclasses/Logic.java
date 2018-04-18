package logicclasses;

import java.util.ArrayList;

public interface Logic<T> {
	public abstract boolean add(T itemToAdd);

	public abstract boolean remove(T itemToRemove);

	public abstract boolean modify(T itemToModify);

	public ArrayList<T> getByID(int id);

	public ArrayList<T> getObjects(int rowNumber);

}
