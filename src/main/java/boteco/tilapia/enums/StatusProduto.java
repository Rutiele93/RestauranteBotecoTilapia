package boteco.tilapia.enums;

public enum StatusProduto {
	
	DISPONIVEL ("Disponível"), 
	INDISPONIVEL ("Indisponível"), 
	INATIVO ("Inativo");
	
	private String statusProduto;

	private StatusProduto(String statusProduto) {
		this.statusProduto = statusProduto;
	}

	public String getStatusProduto() {
		return statusProduto;
	}

	public void setStatusProduto(String statusProduto) {
		this.statusProduto = statusProduto;
	}
	
	
}