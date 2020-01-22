package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;
import javax.sql.StatementEventListener;

public class AutoCloseablePooledConnection implements PooledConnection, AutoCloseable {
	private final Connection connection;

	private final List<ConnectionEventListener> connectionEventListeners;

	private final List<StatementEventListener> statementEventListeners;

	public AutoCloseablePooledConnection(final Connection connection) {
		this.connection = connection;
		connectionEventListeners = new LinkedList<>();
		statementEventListeners = new LinkedList<>();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return connection;
	}

	@Override
	public void close() throws SQLException {
		for (ConnectionEventListener connectionEventListener : connectionEventListeners) {
			connectionEventListener.connectionClosed(new ConnectionEvent(this));
		}
	}

	@Override
	public void addConnectionEventListener(final ConnectionEventListener listener) {
		connectionEventListeners.add(listener);
	}

	@Override
	public void removeConnectionEventListener(final ConnectionEventListener listener) {
		connectionEventListeners.remove(listener);
	}

	@Override
	public void addStatementEventListener(final StatementEventListener listener) {
		statementEventListeners.add(listener);
	}

	@Override
	public void removeStatementEventListener(final StatementEventListener listener) {
		statementEventListeners.remove(listener);
	}

}
