package logicclasses;

import java.util.ArrayList;

import databaseaccess.DAOTitles;
import transferobj.Titles;

public class TitlesLogic implements Logic<Titles> {
	private DAOTitles daoTitle = null;
	public TitlesLogic() {
		daoTitle = new DAOTitles();
	}
	@Override
	public boolean add(Titles itemToAdd) {
		return daoTitle.addItem(itemToAdd);
	}

	@Override
	public boolean remove(Titles itemToRemove) {
		return daoTitle.removeItem(itemToRemove);
	}

	@Override
	public boolean modify(Titles itemToModify) {
		return daoTitle.modifyItem(itemToModify);
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
