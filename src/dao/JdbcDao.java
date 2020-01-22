package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public abstract class JdbcDao<K, T> implements Dao<K, T> {
	private static final int MAX_NB_ROWS = 30;

	private final Logger log;

	private Connection connection;

	public JdbcDao(final Connection connection) {
		this.connection = connection;
		log = Logger.getLogger(getClass().getName());
	}

	@Override
	public List<T> findAll() {
		List<T> list = new LinkedList<>();
		String req = findAllRequest();
		int nbRows = 0;
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(req)) {
			while (rs.next() && nbRows < MAX_NB_ROWS) {
				T t = resultSetToObject(rs);
				list.add(t);
				nbRows++;
			}
		} catch (SQLException e) {
			log.warning(e.getMessage());
			return Collections.emptyList();
		}
		return list;
	}

	protected abstract String findAllRequest();

	protected abstract T resultSetToObject(ResultSet rs) throws SQLException;

}
