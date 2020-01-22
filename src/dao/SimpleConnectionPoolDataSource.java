package dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;

/**
 * <PRE>
 * Sans bassin
 * # cli    resp (ms)
 * 10       325
 * 100      2376
 *  
 * Avec bassin
 * # cli    resp (ms)
 * 10       8
 * 100      22
 * </PRE>
 */
public class SimpleConnectionPoolDataSource implements ConnectionPoolDataSource, ConnectionEventListener {
	private static final Logger log = Logger.getLogger("SimpleConnectionPoolDataSource");

	private PrintWriter logWriter;

	private int loginTimeout;

	private final int depth;

	private PooledConnection[] pool;

	private boolean[] avail;

	public SimpleConnectionPoolDataSource(final int depth, final String url, final String username, final String password, final String schema) {
		this.depth = depth;
		pool = new PooledConnection[depth];
		avail = new boolean[depth];
		for (int i = 0; i < depth; i++) {
			Statement statement = null;
			try {
				Connection connection = DriverManager.getConnection(url, username, password);
				statement = connection.createStatement();
				statement.executeUpdate("SET SCHEMA '" + schema + "'");
				@SuppressWarnings("resource")
				PooledConnection pooledConnection = new AutoCloseablePooledConnection(connection);
				pooledConnection.addConnectionEventListener(this);
				pool[i] = pooledConnection;
				avail[i] = true;
			} catch (SQLException e) {
				log.warning(e.getMessage());
				throw new IllegalStateException(e);
			}

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
	public PooledConnection getPooledConnection() {
		PooledConnection pooledConnection = null;
		while ((pooledConnection = _getPooledConnection()) == null) {
			sleep(10);
		}
		return pooledConnection;
	}

	private synchronized PooledConnection _getPooledConnection() {
		for (int i = 0; i < depth; i++) {
			if (avail[i]) {
				avail[i] = false;
				return pool[i];
			}

		}
		return null;
	}

	private static void sleep(final int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			log.warning(e.getMessage());
		}
	}

	@Override
	public PooledConnection getPooledConnection(final String user, final String password) throws SQLException {
		return null;
	}

	@Override
	public synchronized void connectionClosed(final ConnectionEvent event) {
		Object source = event.getSource();
		for (int i = 0; i < depth; i++) {
			PooledConnection mpc = pool[i];
			if (mpc == source) {
				avail[i] = true;
				return;
			}
		}
	}

	@Override
	public void connectionErrorOccurred(final ConnectionEvent event) {
		final String string = event.toString();
		log.warning(string);
	}

}
