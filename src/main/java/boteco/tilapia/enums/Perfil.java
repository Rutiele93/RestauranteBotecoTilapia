package boteco.tilapia.enums;

public enum Perfil {
	
	ADMIN("Administrador"), VENDEDOR("Vendendor"), CLIENTE("Cliente");

	private String perfil;

	private Perfil(String perfil) {
		this.perfil = perfil;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
}


