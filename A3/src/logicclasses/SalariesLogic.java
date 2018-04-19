package logicclasses;

import java.util.ArrayList;

import databaseaccess.DAOSalaries;
import factories.SalariesFactory;
import factories.TransferFactoryCreator;
import transferobj.Salaries;

public class SalariesLogic implements Logic<Salaries> {
	private DAOSalaries daoSal;
	private SalariesFactory sFact;

	public SalariesLogic() {
		daoSal  = new DAOSalaries();
		sFact = (SalariesFactory) TransferFactoryCreator.createBuilder(Salaries.class);
	}
	@Override
	public boolean add(String[] itemToAdd) {
		Salaries item = sFact.createFromInput(itemToAdd);
		return daoSal.addItem(item);
	}

	@Override
	public boolean remove(String[] itemToRemove) {
		Salaries item = sFact.createFromInput(itemToRemove);
		return daoSal.removeItem(item);
	}

	@Override
	public boolean modify(String[]  itemToModify) {
		Salaries item = sFact.createFromInput(itemToModify);
		return daoSal.modifyItem(item);
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
