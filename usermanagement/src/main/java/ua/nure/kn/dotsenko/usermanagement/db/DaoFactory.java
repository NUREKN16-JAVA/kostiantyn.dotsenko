package ua.nure.kn.dotsenko.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public abstract class DaoFactory {
	
	private static final String DAO_FACTORY = "dao.factory";
	protected final static String USER_DAO = "dao.ua.nure.kn.dotsenko.usermanagement.db.UserDAO";
	protected static Properties properties;
	
	private static DaoFactory instance;
	
	static {
		properties = new Properties();
		try {
			properties.load(DaoFactory.class.getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static synchronized DaoFactory getInstance() {
		if(instance == null) {
			try {
				Class factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
				instance = (DaoFactory) factoryClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
		
	}
	
	protected DaoFactory() {}
	
	protected ConnectionFactory getConnectionFactory() {
		return new ConnectionFactoryImpl(properties);
	}
	
	public abstract UserDAO getUserDAO();

	public static void init(Properties prop) {
		properties = prop;
		instance = null;
	}
}
