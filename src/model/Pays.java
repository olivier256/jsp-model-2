package model;

public class Pays {

	private String code;

	private String libelleCourt;

	private String libelleLong;

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
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
		return "Pays [code=" + code + ", libelleCourt=" + libelleCourt + ", libelleLong=" + libelleLong + "]";
	}

}
