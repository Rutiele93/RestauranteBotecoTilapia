package boteco.tilapia.enums;


public enum CategoriaPedido {

	ABERTO("Aberto"), 
	ANDAMENTO("Em Andamento"), 
	FECHADO("Fechado");

	private String statusPedido;

	private CategoriaPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}

	public String getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}

	


}