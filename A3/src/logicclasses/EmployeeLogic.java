package logicclasses;

import java.util.ArrayList;

import databaseaccess.DAODeptEmployee;
import databaseaccess.DAODeptManager;
import databaseaccess.DAOEmployee;
import databaseaccess.DAOSalaries;
import databaseaccess.DAOTitles;
import factories.EmployeeFactory;
import factories.TransferFactoryCreator;
import transferobj.DeptEmployee;
import transferobj.DeptManager;
import transferobj.Employee;
import transferobj.Salaries;
import transferobj.Titles;

public class EmployeeLogic implements Logic<Employee> {
	private DAOEmployee daoEmp = null;
	private EmployeeFactory eFact;

	public EmployeeLogic() {
		daoEmp = new DAOEmployee();
		eFact = (EmployeeFactory) TransferFactoryCreator.createBuilder(Employee.class);
	}

	@Override
	public ArrayList<Employee> getObjects(int numRows) {
		return daoEmp.createList(numRows);
	}

	@Override
	public <U> ArrayList<Employee> getByID(U id) {
		return daoEmp.getItemsByID(id);
	}

	@Override
	public boolean add(String[] toAdd) {
		Employee item = eFact.createFromInput(toAdd);
		return daoEmp.addItem(item);
	}

	@Override
	public boolean remove(String[] toRemove) {
		Employee item = eFact.createFromInput(toRemove);
		return daoEmp.removeItem(item);

	}

	@Override
	public boolean modify(String[] toModify) {
		Employee item = eFact.createFromInput(toModify);
		return daoEmp.modifyItem(item);
	}
}
