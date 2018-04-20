package logicclasses;

import java.util.ArrayList;
import databaseaccess.DAODepartments;
import databaseaccess.DAODeptEmployee;
import databaseaccess.DAODeptManager;
import factories.DepartmentsFactory;
import factories.TransferFactoryCreator;
import transferobj.Departments;
import transferobj.DeptEmployee;
import transferobj.DeptManager;

public class DepartmentsLogic implements Logic<Departments> {
	private DAODepartments daoDept;
	private DepartmentsFactory dFact;

	public DepartmentsLogic() {
		daoDept = new DAODepartments();
		dFact = (DepartmentsFactory) TransferFactoryCreator.createBuilder(Departments.class);
	}

	@Override
	public boolean add(String[] itemToAdd) {
		Departments item = dFact.createFromInput(itemToAdd);
		return daoDept.addItem(item);
	}

	@Override
	public boolean remove(String[] deptToRemove) {
		DAODeptEmployee dEmp = new DAODeptEmployee();
		DAODeptManager dMan = new DAODeptManager();

		try {
			ArrayList<DeptEmployee> deList = new ArrayList<>();
			ArrayList<DeptManager> dmList = new ArrayList<>();
			deList = dEmp.getItemsByID(deptToRemove[0]);
			dmList = dMan.getItemsByID(deptToRemove[0]);
		} catch (IllegalArgumentException e) {
			return false;
		}
		Departments item = dFact.createFromInput(deptToRemove);
		return daoDept.removeItem(item);
	}

	@Override
	public boolean modify(String[] itemToModify) {

		Departments item = dFact.createFromInput(itemToModify);
		return daoDept.modifyItem(item);
	}

	@Override
	public ArrayList<Departments> getByID(int id) {
		return daoDept.getItemsByID(id);
	}

	@Override
	public ArrayList<Departments> getObjects(int rowNumber) {
		return daoDept.createList(rowNumber);
	}

}
