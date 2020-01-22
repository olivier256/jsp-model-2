package model;

public class Tache {

	private Long itTache;

	private String responsable;

	private Integer statut;

	private Integer type;

	private String pays;

	public Long getItTache() {
		return itTache;
	}

	public void setItTache(final Long itTache) {
		this.itTache = itTache;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(final String responsable) {
		this.responsable = responsable;
	}

	public Integer getStatut() {
		return statut;
	}

	public void setStatut(final Integer statut) {
		this.statut = statut;
	}

	public Integer getType() {
		return type;
	}

	public void setType(final Integer type) {
		this.type = type;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(final String pays) {
		this.pays = pays;
	}

	@Override
	public String toString() {
		return "Tache [itTache=" + itTache + ", responsable=" + responsable + ", statut=" + statut + ", type=" + type + ", pays=" + pays + "]";
	}

}
