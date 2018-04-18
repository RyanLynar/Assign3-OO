package logicclasses;

import java.util.ArrayList;

import databaseaccess.DAODeptEmployee;
import transferobj.DeptEmployee;

public class DeptEmployeeLogic implements Logic<DeptEmployee> {
	private DAODeptEmployee daoDE;

	public DeptEmployeeLogic() {
		daoDE = new DAODeptEmployee();
	}
	@Override
	public ArrayList<DeptEmployee> getObjects(int numRows) {
		return daoDE.createList(numRows);
	}

	@Override
	public ArrayList<DeptEmployee> getByID(int id) {
		return daoDE.getItemsByID(id);
	}

	@Override
	public boolean add(DeptEmployee toAdd) {
		return daoDE.addItem(toAdd);
	}

	@Override
	public boolean remove(DeptEmployee toRemove) {
		return daoDE.removeItem(toRemove);
	}

	@Override
	public boolean modify(DeptEmployee toModify) {
		return daoDE.modifyItem(toModify);
	}

}
