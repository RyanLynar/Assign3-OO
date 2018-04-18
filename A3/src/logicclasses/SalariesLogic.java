package logicclasses;

import java.util.ArrayList;

import databaseaccess.DAOSalaries;
import transferobj.Salaries;

public class SalariesLogic implements Logic<Salaries> {
	private DAOSalaries daoSal;

	public SalariesLogic() {
		daoSal  = new DAOSalaries();
	}
	@Override
	public boolean add(Salaries itemToAdd) {
		return daoSal.addItem(itemToAdd);
	}

	@Override
	public boolean remove(Salaries itemToRemove) {
		return daoSal.removeItem(itemToRemove);
	}

	@Override
	public boolean modify(Salaries itemToModify) {
		return daoSal.modifyItem(itemToModify);
	}

	@Override
	public ArrayList<Salaries> getByID(int id) {
		return daoSal.getItemsByID(id);
	}

	@Override
	public ArrayList<Salaries> getObjects(int rowNumber) {
		return daoSal.createList(rowNumber);
	}

}
