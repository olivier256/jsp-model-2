package dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class PostgresDataSource implements DataSource {
	private static final Logger log = Logger.getLogger("PostgresDataSource");

	private static final String USERNAME = "postgres";

	private static final String PASSWORD = "";

	private PrintWriter logWriter;

	private int loginTimeout;

	public PostgresDataSource() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			log.warning(e.getMessage());
			throw new IllegalStateException(e);
		}
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return logWriter;
	}

	@Override
	public void setLogWriter(final PrintWriter out) throws SQLException {
		logWriter = out;
	}

	@Override
	public void setLoginTimeout(final int seconds) throws SQLException {
		loginTimeout = seconds;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return loginTimeout;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return log;
	}

	@Override
	public <T> T unwrap(final Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(final Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getConnection(USERNAME, PASSWORD);
	}

	@Override
	public Connection getConnection(final String username, final String password) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/db_mce_dev01", username, password);
		conn.createStatement().executeUpdate("SET SCHEMA 'mce_dev01'");
		return conn;
	}

}
