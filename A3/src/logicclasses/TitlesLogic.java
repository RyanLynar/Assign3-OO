package logicclasses;

import java.util.ArrayList;

import databaseaccess.DAOTitles;
import factories.TitlesFactory;
import factories.TransferFactoryCreator;
import transferobj.Titles;

public class TitlesLogic implements Logic<Titles> {
	private DAOTitles daoTitle = null;
	private TitlesFactory tFact;

	public TitlesLogic() {
		daoTitle = new DAOTitles();
		tFact = (TitlesFactory) TransferFactoryCreator.createBuilder(Titles.class);
	}

	@Override
	public boolean add(String[] itemToAdd) {
		Titles item = tFact.createFromInput(itemToAdd);
		return daoTitle.addItem(item);
	}

	@Override
	public boolean remove(String[] itemToRemove) {
		Titles item = tFact.createFromInput(itemToRemove);
		return daoTitle.removeItem(item);
	}

	@Override
	public boolean modify(String[] itemToModify) {
		Titles item = tFact.createFromInput(itemToModify);
		return daoTitle.modifyItem(item);
	}

	@Override
	public ArrayList<Titles> getByID(int id) {
		return daoTitle.getItemsByID(id);
	}

	@Override
	public ArrayList<Titles> getObjects(int rowNumber) {
		return daoTitle.createList(rowNumber);
	}

}
