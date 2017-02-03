package bg.tilchev.hibernate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateServlet implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			HibernateUtil.initialize();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
}