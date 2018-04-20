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
		DAOSalaries salaries = new DAOSalaries();
		try {
			ArrayList<DeptEmployee> deleteDEList = dEmp.getItemsByID(Integer.getInteger(toRemove[0]));
			ArrayList<DeptManager> deleteDMList = dMan.getItemsByID(Integer.getInteger(toRemove[0]));
			ArrayList<Titles> deleteTList = titles.getItemsByID(Integer.getInteger(toRemove[0]));
			ArrayList<Salaries> deleteSList = salaries.getItemsByID(Integer.getInteger(toRemove[0]));
			if (deleteDEList.size() != 0) {
				dEmp.removeItem(deleteDEList.get(0));
			}
			if (deleteDMList.size() != 0) {
				dMan.removeItem(deleteDMList.get(0));
				;
			}
			if (deleteTList.size() != 0) {
				titles.removeItem(deleteTList.get(0));
			}
			if (deleteSList.size() != 0) {
				salaries.removeItem(deleteSList.get(0));
			}
		} catch (IllegalArgumentException e) {
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