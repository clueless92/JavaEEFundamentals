package bg.tilchev.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URL;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static void initialize() {
		try {
			URL url = HibernateUtil.class.getResource("/hibernate.cfg.xml");
			Configuration config = new Configuration();
			config.configure(url);
			sessionFactory = config.buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		sessionFactory.close();
	}

}
