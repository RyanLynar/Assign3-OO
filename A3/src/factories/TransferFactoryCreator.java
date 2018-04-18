package factories;

public final class TransferFactoryCreator {
	@SuppressWarnings("unchecked")
	public static <T> Factory<T> getFactory(String factoryName) {
		Factory<T> factory = null;
		try {
			factory = (Factory<T>) Class.forName("factories" + factoryName + "Factory").newInstance();
		} catch (Exception e) {
			System.out.println("Null factory");
		}
		return factory;
	}

	public static <T> Factory<T> createBuilder(Class<T> type) {
		return getFactory(type.getSimpleName());
	}
}
