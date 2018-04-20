package transferobj;
/**
 * 
 * @author Ryan
 *
 * @param <T> Type of Transfer object this is
 */
public abstract interface TransferObject <T> {
	/**
	 * 
	 * @return a string array of the values of a given Transfer Object
	 */
	public abstract String[] getValues();
	/**
	 * Implemented for bug testing during early phases of development, now no longer useful
	 */
	@Deprecated
	public abstract void printAll();
}
