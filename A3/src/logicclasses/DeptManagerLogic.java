package logicclasses;

import java.util.ArrayList;

import databaseaccess.DAODeptManager;
import factories.DeptManagerFactory;
import factories.TransferFactoryCreator;
import transferobj.DeptManager;

public class DeptManagerLogic implements Logic<DeptManager> {
	private DAODeptManager daoDeptManager;
	private DeptManagerFactory dmFact;

	public DeptManagerLogic() {
		daoDeptManager = new DAODeptManager();
		dmFact = (DeptManagerFactory) TransferFactoryCreator.createBuilder(DeptManager.class);
	}

	@Override
	public boolean add(String[] itemToAdd) {
		DeptManager item = dmFact.createFromInput(itemToAdd);
		return daoDeptManager.addItem(item);
	}

	@Override
	public boolean remove(String[] itemToRemove) {
		DeptManager item = dmFact.createFromInput(itemToRemove);
		return daoDeptManager.removeItem(item);
	}

	@Override
	public boolean modify(String[] itemToModify) {
		DeptManager item = dmFact.createFromInput(itemToModify);
		return daoDeptManager.modifyItem(item);
	}

	@Override
	public <U> ArrayList<DeptManager> getByID(U id) {
		return daoDeptManager.getItemsByID(id);
	}

	@Override
	public ArrayList<DeptManager> getObjects(int rowNumber) {
		return daoDeptManager.createList(rowNumber);
	}

}
