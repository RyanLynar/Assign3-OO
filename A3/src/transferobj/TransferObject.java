package transferobj;
/**
 * 
 * @author Ryan
 *
 * @param <T> Type of Transfer object this is
 */
public abstract interface TransferObject <T> {
	
	@Deprecated
	public abstract String[] getValues();
	@Deprecated
	public abstract void printAll();
}
