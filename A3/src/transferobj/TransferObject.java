package transferobj;
/**
 * 
 * @author Ryan
 *
 * @param <T> Type of Transfer object this is
 */
public abstract interface TransferObject <T> {
	public abstract String[] getValues();
	
	public abstract void printAll();
}
