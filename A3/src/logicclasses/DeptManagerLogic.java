package logicclasses;

import java.util.ArrayList;

import databaseaccess.DAODeptManager;
import transferobj.DeptManager;

public class DeptManagerLogic implements Logic<DeptManager>{
	private DAODeptManager daoDeptManager;
	
	public DeptManagerLogic() {
		daoDeptManager = new DAODeptManager();
	}

	@Override
	public boolean add(DeptManager itemToAdd) {
		return daoDeptManager.addItem(itemToAdd);
	}

	@Override
	public boolean remove(DeptManager itemToRemove) {
		return daoDeptManager.removeItem(itemToRemove);
	}

	@Override
	public boolean modify(DeptManager itemToModify) {
		return daoDeptManager.modifyItem(itemToModify);
	}

	@Override
	public ArrayList<DeptManager> getByID(int id) {
		return daoDeptManager.getItemsByID(id);
	}

	@Override
	public ArrayList<DeptManager> getObjects(int rowNumber) {
		return daoDeptManager.createList(rowNumber);
	}

}
