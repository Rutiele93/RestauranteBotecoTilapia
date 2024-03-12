package boteco.tilapia.model;

import java.util.ArrayList;
import java.util.List;

import boteco.tilapia.enums.Perfil;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Vendedor extends Pessoa {
	
	public Vendedor(){
		
	}
		
	public Vendedor(Integer id, String nome, String email, String senha, String imagem, Perfil perfil) {
		super(id, nome, email, senha, imagem, perfil);
		
	}

	@OneToMany(mappedBy = "vendedor")
	private List<Pedido> Pedido = new ArrayList<>();

	public List<Pedido> getPedido() {
		return Pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		Pedido = pedido;
	}


}
