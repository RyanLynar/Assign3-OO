package builders;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface AbstractBuilder<T> {
	public abstract void build(ResultSet r);
	public ArrayList<T> returnList();
}
