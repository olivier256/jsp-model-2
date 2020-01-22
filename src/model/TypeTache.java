package model;

public class TypeTache {

	private String typeTache;

	private String libelleCourt;

	private String libelleLong;

	public String getTypeTache() {
		return typeTache;
	}

	public void setTypeTache(final String typeTache) {
		this.typeTache = typeTache;
	}

	public String getLibelleCourt() {
		return libelleCourt;
	}

	public void setLibelleCourt(final String libelleCourt) {
		this.libelleCourt = libelleCourt;
	}

	public String getLibelleLong() {
		return libelleLong;
	}

	public void setLibelleLong(final String libelleLong) {
		this.libelleLong = libelleLong;
	}

	@Override
	public String toString() {
		return "TypeTache [typeTache=" + typeTache + ", libelleCourt=" + libelleCourt + ", libelleLong=" + libelleLong + "]";
	}

}
