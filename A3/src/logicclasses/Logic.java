package logicclasses;

import java.util.ArrayList;

public interface Logic<T> {
	public abstract boolean add(String[] itemToAdd);

	public abstract boolean remove(String[] itemToRemove);

	public abstract boolean modify(String[] itemToModify);

	public ArrayList<T> getByID(int id);

	public ArrayList<T> getObjects(int rowNumber);

}
