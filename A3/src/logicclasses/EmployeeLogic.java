package logicclasses;

import java.util.ArrayList;

import databaseaccess.DAODeptEmployee;
import databaseaccess.DAODeptManager;
import databaseaccess.DAOEmployee;
import databaseaccess.DAOSalaries;
import databaseaccess.DAOTitles;
import factories.EmployeeFactory;
import factories.TransferFactoryCreator;
import transferobj.Employee;

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
	public ArrayList<Employee> getByID(int id) {
		return daoEmp.getItemsByID(id);
	}

	@Override
	public boolean add(String[] toAdd) {
		Employee item = eFact.createFromInput(toAdd);
		return daoEmp.addItem(item);
	}

	@Override
	public boolean remove(String[] toRemove) {
		DAODeptEmployee dEmp = new DAODeptEmployee();
		DAODeptManager dMan = new DAODeptManager();
		DAOTitles titles = new DAOTitles();
		DAOSalaries salaries= new DAOSalaries();
		if(dEmp.getItemsByID(Integer.parseInt(toRemove[0])).size() != 0) {
			return false;
		}
		if(dMan.getItemsByID(Integer.parseInt(toRemove[0])).size() != 0) {
			return false;
		}
		if(titles.getItemsByID(Integer.parseInt(toRemove[0])).size()!=0) {
			return false;
		}
		if(salaries.getItemsByID(Integer.parseInt(toRemove[0])).size()!=0) {
			return false;
		}
		Employee item = eFact.createFromInput(toRemove);
		return daoEmp.removeItem(item);
	}

	@Override
	public boolean modify(String[] toModify) {
		Employee item = eFact.createFromInput(toModify);
		return daoEmp.modifyItem(item);
	}


}