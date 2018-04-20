package logicclasses;

import java.util.ArrayList;

import databaseaccess.DAODeptEmployee;
import factories.DeptEmployeeFactory;
import factories.TransferFactoryCreator;
import transferobj.DeptEmployee;

public class DeptEmployeeLogic implements Logic<DeptEmployee> {
	private DAODeptEmployee daoDE;
	private DeptEmployeeFactory deFact;

	public DeptEmployeeLogic() {
		daoDE = new DAODeptEmployee();
		deFact =(DeptEmployeeFactory) TransferFactoryCreator.createBuilder(DeptEmployee.class);
	}
	@Override
	public ArrayList<DeptEmployee> getObjects(int numRows) {
		return daoDE.createList(numRows);
	}

	@Override
	public <U> ArrayList<DeptEmployee> getByID(U id) {
		return daoDE.getItemsByID(id);
	}

	@Override
	public boolean add(String[] toAdd) {
		DeptEmployee item = deFact.createFromInput(toAdd);
		return daoDE.addItem(item);
	}

	@Override
	public boolean remove(String[] toRemove) {
		DeptEmployee item = deFact.createFromInput(toRemove);
		return daoDE.removeItem(item);
	}

	@Override
	public boolean modify(String[] toModify) {
		DeptEmployee item = deFact.createFromInput(toModify);
		return daoDE.modifyItem(item);
	}
}
