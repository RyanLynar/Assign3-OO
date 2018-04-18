package logicclasses;

import java.util.ArrayList;

import databaseaccess.DAOEmployee;
import transferobj.Employee;

public class EmployeeLogic implements Logic<Employee> {
	private DAOEmployee daoEmp = null;
	public EmployeeLogic() {
		daoEmp = new DAOEmployee();
	}
	@Override
	public ArrayList<Employee> getObjects(int numRows) {
		return daoEmp.createList(numRows);
	}

	@Override
	public ArrayList<Employee> getByID(int id) {
		return daoEmp.getItemsByID(id);
	}

	@Override
	public boolean add(Employee toAdd) {
		return daoEmp.addItem(toAdd);
	}

	@Override
	public boolean remove(Employee toRemove) {
		return daoEmp.removeItem(toRemove);
	}

	@Override
	public boolean modify(Employee toModify) {
		return daoEmp.modifyItem(toModify);
	}


}
