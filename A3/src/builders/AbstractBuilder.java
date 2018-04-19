package builders;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface AbstractBuilder<T> {
	public abstract void build(ResultSet r);
	public abstract void build(String[] input);
	public ArrayList<T> returnList();
}
