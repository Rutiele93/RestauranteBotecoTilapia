package boteco.tilapia.enums;


public enum CategoriaProduto {

	BEBIDAS("Bebidas"), COMIDAS("Prato Principal"), PETICOS("Petiscos");

	private String categoriaProduto;

	private CategoriaProduto(String categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	public String getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(String categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	

}