package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Pays;

public class PaysDao extends JdbcDao<String, Pays> {
	public PaysDao(final Connection connection) {
		super(connection);
	}

	@Override
	protected String findAllRequest() {
		return "SELECT co_pays_iso2, libelle_court, libelle_long FROM T_PAR_DV_PAYS_ISO2";
	}

	@Override
	protected Pays resultSetToObject(final ResultSet rs) throws SQLException {
		String code = rs.getString("co_pays_iso2");
		String libelleCourt = rs.getString("libelle_court");
		String libelleLong = rs.getString("libelle_long");
		Pays pays = new Pays();
		pays.setCode(code);
		pays.setLibelleCourt(libelleCourt);
		pays.setLibelleLong(libelleLong);
		return pays;
	}
}
