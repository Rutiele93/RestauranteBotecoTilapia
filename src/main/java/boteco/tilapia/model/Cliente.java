package boteco.tilapia.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import boteco.tilapia.enums.Perfil;
//import jakarta.persistence.Entity;
//import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa{
	
	public Cliente() {
		
	}
	
	public Cliente(Integer id, String nome, String email, String senha, String imagem, Perfil perfil) {
		super(id, nome, email, senha, imagem, perfil);
	}

	@OneToMany(mappedBy = "cliente") //OneToMany sginifica que são varios pedidos para um clientes
    private List<Pedido> Pedido = new ArrayList<>();

    public List<Pedido> getPedido() {
        return Pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        Pedido = pedido;
    }
}
