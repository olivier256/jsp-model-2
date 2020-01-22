package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.ConnectionPoolDataSource;

import dao.SimpleConnectionPoolDataSource;

@WebListener
public class SimpleServletContextListener implements ServletContextListener {
	private static final Logger log = Logger.getLogger("SimpleServletContextListener");

	public static final String CONNECTION_POOL_DATA_SOURCE = "connectionPoolDataSource";

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		// dummy
	}

	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		final ServletContext servletContext = sce.getServletContext();
		final InputStream resource = servletContext.getResourceAsStream("/datasource.conf");
		Properties prop = readPropertiesFromInputStream(resource);
		ConnectionPoolDataSource connectionPoolDataSource = new SimpleConnectionPoolDataSource(Integer.parseInt(prop.getProperty("depth")),
				prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("schema"));
		servletContext.setAttribute(CONNECTION_POOL_DATA_SOURCE, connectionPoolDataSource);
	}

	private Properties readPropertiesFromInputStream(final InputStream inputStream) {
		Properties prop = new Properties();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			prop.load(reader);
		} catch (IOException e) {
			log.warning(e.getMessage());
		}
		return prop;
	}

}
