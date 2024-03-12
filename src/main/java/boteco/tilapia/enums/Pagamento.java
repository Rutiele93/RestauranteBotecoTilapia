package boteco.tilapia.enums;

public enum Pagamento {

	CARTÃO("Cartão"), DINHEIRO("Dinheiro"), PIX("Pix");

	private String pagamento;

	private Pagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
}
