package business;

public enum PlayerStatus {

	SEARCHING_TEAM("Buscando Equipe"), IN_TEAM("Em Equipe"), UNAVAILABLE("Indisponivel");

	private final String name;

	private PlayerStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}