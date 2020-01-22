package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import model.TypeTache;

public class TypeTacheDao implements Dao<String, TypeTache> {
	private static final Logger log = Logger.getLogger("TypeTacheDao");

	private final Connection conn;

	public TypeTacheDao(final Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public List<TypeTache> findAll() {
		List<TypeTache> list = new LinkedList<>();
		String req = "SELECT co_typtache, libelle_court, libelle_long FROM T_GTC_DV_TYPE_TACHE";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(req)) {
			while (rs.next()) {
				String _typeTache = rs.getString("co_typtache");
				String libelleCourt = rs.getString("libelle_court");
				String libelleLong = rs.getString("libelle_long");
				TypeTache typeTache = new TypeTache();
				typeTache.setTypeTache(_typeTache);
				typeTache.setLibelleCourt(libelleCourt);
				typeTache.setLibelleLong(libelleLong);
				list.add(typeTache);
			}
		} catch (SQLException e) {
			log.warning(e.getMessage());
			return Collections.emptyList();
		}
		return list;
	}

}
