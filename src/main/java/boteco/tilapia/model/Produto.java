package boteco.tilapia.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import boteco.tilapia.enums.CategoriaProduto;
import boteco.tilapia.enums.StatusProduto;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;

@Entity
public class Produto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao; 
    
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    private BigDecimal preco;
    
    private String imagem;
    private StatusProduto statusProduto;
    private CategoriaProduto categoriaProduto;
    
    public Produto() {
    	
    }
    
	
    @OneToMany(mappedBy = "produto") //OneToMany sginifica que são varios pedidos para um clientes
    private List<Pedido> Pedido = new ArrayList<>();

    public List<Pedido> getPedido() {
        return Pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        Pedido = pedido;
    }



	public Produto(Integer id, String nome, String descricao, BigDecimal preco, String imagem,
			StatusProduto statusProduto, CategoriaProduto categoriaProduto, List<boteco.tilapia.model.Pedido> pedido) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.imagem = imagem;
		this.statusProduto = statusProduto;
		this.categoriaProduto = categoriaProduto;
		Pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public StatusProduto getStatusProduto() {
		return statusProduto;
	}

	public void setStatusProduto(StatusProduto statusProduto) {
		this.statusProduto = statusProduto;
	}

	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}
    
    
}