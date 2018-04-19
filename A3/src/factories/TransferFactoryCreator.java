package factories;

/**
 *
 * @author Shariar (Thanks for providing this class) 
 */
public final class TransferFactoryCreator {
	/**
	 * 
	 * @param factoryName 
	 * @return a factory of the given Transfer object types name
	 */
	@SuppressWarnings("unchecked")
	public static <T> Factory<T> getFactory(String factoryName) {
		Factory<T> factory = null;
		try {
			factory = (Factory<T>) Class.forName("factories." + factoryName + "Factory").newInstance();
		} catch (Exception e) {
			System.out.println("Null factory");
		}
		return factory;
	}
	/**
	 * 
	 * @param type
	 * @return a factory of the given Transfer object type.
	 */
	public static <T> Factory<T> createBuilder(Class<T> type) {
		return getFactory(type.getSimpleName());
	}
}
