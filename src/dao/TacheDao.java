package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import model.Tache;

public class TacheDao extends JdbcDao<Long, Tache> {
	public TacheDao(final Connection connection) {
		super(connection);
	}

	@Override
	protected String findAllRequest() {
		return "SELECT it_tache, co_responsable, co_statut_tache, co_typtache, co_pays_iso2 FROM T_GTC_TACHE";
	}

	@Override
	protected Tache resultSetToObject(final ResultSet rs) throws SQLException {
		Long itTache = rs.getLong("it_tache");
		String responsable = rs.getString("co_responsable");
		Integer statut = rs.getInt("co_statut_tache");
		Integer type = rs.getInt("co_typtache");
		String pays = rs.getString("co_pays_iso2");
		Tache tache = new Tache();
		tache.setItTache(itTache);
		tache.setResponsable(responsable);
		tache.setStatut(statut);
		tache.setType(type);
		tache.setPays(pays);
		return tache;
	}

	public static void main(final String[] args) throws SQLException {
		DataSource dataSource = new PostgresDataSource();
		Connection conn = dataSource.getConnection();
		TacheDao tacheDao = new TacheDao(conn);
		List<Tache> findAll = tacheDao.findAll();
		for (Tache tache : findAll) {
			System.out.println(tache);
		}
	}

}
