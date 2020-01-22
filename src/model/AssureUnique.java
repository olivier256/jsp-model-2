package model;

public class AssureUnique {

	private Long itAssureUnique;

	private String nir;

	private String nomNaissance;

	private String prenomNaissance;

	private String sexe;

	private Integer jourNaissance;

	private Integer moisNaissance;

	private Integer anneeNaissance;

	public Long getItAssureUnique() {
		return itAssureUnique;
	}

	public void setItAssureUnique(final Long itAssureUnique) {
		this.itAssureUnique = itAssureUnique;
	}

	public String getNir() {
		return nir;
	}

	public void setNir(final String nir) {
		this.nir = nir;
	}

	public String getNomNaissance() {
		return nomNaissance;
	}

	public void setNomNaissance(final String nomNaissance) {
		this.nomNaissance = nomNaissance;
	}

	public String getPrenomNaissance() {
		return prenomNaissance;
	}

	public void setPrenomNaissance(final String prenomNaissance) {
		this.prenomNaissance = prenomNaissance;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(final String sexe) {
		this.sexe = sexe;
	}

	public Integer getJourNaissance() {
		return jourNaissance;
	}

	public void setJourNaissance(final Integer jourNaissance) {
		this.jourNaissance = jourNaissance;
	}

	public Integer getMoisNaissance() {
		return moisNaissance;
	}

	public void setMoisNaissance(final Integer moisNaissance) {
		this.moisNaissance = moisNaissance;
	}

	public Integer getAnneeNaissance() {
		return anneeNaissance;
	}

	public void setAnneeNaissance(final Integer anneeNaissance) {
		this.anneeNaissance = anneeNaissance;
	}

	@Override
	public String toString() {
		return "AssureUnique [itAssureUnique=" + itAssureUnique + ", nir=" + nir + ", nomNaissance=" + nomNaissance + ", prenomNaissance="
				+ prenomNaissance + ", sexe=" + sexe + ", jourNaissance=" + jourNaissance + ", moisNaissance=" + moisNaissance + ", anneeNaissance="
				+ anneeNaissance + "]";
	}

}
