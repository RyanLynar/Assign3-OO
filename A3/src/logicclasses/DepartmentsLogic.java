package logicclasses;

import java.util.ArrayList;
import databaseaccess.DAODepartments;
import transferobj.Departments;

public class DepartmentsLogic implements Logic<Departments>{
	private DAODepartments daoDept;
	
	public DepartmentsLogic() {
		daoDept = new DAODepartments();
	}

	@Override
	public boolean add(Departments itemToAdd) {
		return daoDept.addItem(itemToAdd);
	}

	@Override
	public boolean remove(Departments deptToRemove) {
		return daoDept.removeItem(deptToRemove);
	}

	@Override
	public boolean modify(Departments itemToModify) {
		return daoDept.modifyItem(itemToModify);
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
