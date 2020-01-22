package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import model.AssureUnique;

public class AssureUniqueDao implements Dao<Long, AssureUnique> {
	private static final Logger log = Logger.getLogger("AssureUniqueDao");

	private final Connection conn;

	public AssureUniqueDao(final Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<AssureUnique> findAll() {
		String req = "SELECT it_assure_unique, jour_naissance, mois_naissance, an_naissance, co_nir, "
				+ "libelle_nom_naissance_indiv, libelle_prenom_naissance, co_sexe FROM T_ANN_ASSURE_UNIQUE";
		List<AssureUnique> list = new LinkedList<>();
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(req)) {
			while (rs.next()) {
				Long itAssureUnique = rs.getLong("it_assure_unique");
				Integer jourNaissance = rs.getInt("jour_naissance");
				Integer moisNaissance = rs.getInt("mois_naissance");
				Integer anneeNaissance = rs.getInt("an_naissance");
				String nir = rs.getString("co_nir");
				String nomNaissance = rs.getString("libelle_nom_naissance_indiv");
				String prenomNaissance = rs.getString("libelle_prenom_naissance");
				String sexe = rs.getString("co_sexe");
				AssureUnique assureUnique = new AssureUnique();
				assureUnique.setItAssureUnique(itAssureUnique);
				assureUnique.setNomNaissance(nomNaissance);
				assureUnique.setNir(nir);
				assureUnique.setPrenomNaissance(prenomNaissance);
				assureUnique.setJourNaissance(jourNaissance);
				assureUnique.setMoisNaissance(moisNaissance);
				assureUnique.setAnneeNaissance(anneeNaissance);
				assureUnique.setSexe(sexe);
				list.add(assureUnique);
			}
		} catch (SQLException e) {
			log.warning(e.getMessage());
		}
		return list;
	}

	public static void main(final String[] args) throws SQLException {
		DataSource dataSource = new PostgresDataSource();
		Connection conn = dataSource.getConnection();
		AssureUniqueDao assureUniqueDao = new AssureUniqueDao(conn);
		List<AssureUnique> findAll = assureUniqueDao.findAll();
		for (AssureUnique assureUnique : findAll) {
			System.out.println(assureUnique);
		}
	}

}
