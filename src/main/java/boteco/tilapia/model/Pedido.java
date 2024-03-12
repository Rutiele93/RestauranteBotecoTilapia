package boteco.tilapia.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import boteco.tilapia.enums.CategoriaPedido;
import boteco.tilapia.enums.Pagamento;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate dataAbertura = LocalDate.now();
	private LocalDate dataFechamento;
	private String observacao;
	private Float valorFinal;
	private CategoriaPedido status;
	private Pagamento pagamento;
	

	@ManyToOne
	@JoinColumn(name = "cliente_id_fk")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "vendedor_id_fk")
	private Vendedor vendedor;
	
	@ManyToOne
	@JoinColumn(name = "produto_id_fk")
	private Produto produto;

	public Pedido() {

	}

	public Pedido(Integer id, LocalDate dataAbertura, LocalDate dataFechamento, String observacao,
			Float valorFinal, CategoriaPedido status, Pagamento pagamento) {
		this.id = id;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.observacao = observacao;
		this.valorFinal = valorFinal;
		this.status = status;
		this.pagamento = pagamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Float getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Float valorFinal) {
		this.valorFinal = valorFinal;
	}

	public CategoriaPedido getStatus() {
		return status;
	}

	public void setStatus(CategoriaPedido status) {
		this.status = status;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
//	
//	private Float calcularTotalPedido(Pedido pedido) {
//	    Float total = 0.0f;
//	    
//	    // Itera sobre os produtos associados ao pedido
//	    for (Produto produto : pedido.getProdutos()) {
//	        // Obtém o preço do produto e adiciona ao total
//	        total += produto.getPreco().floatValue();
//	    }
//	    
//	    return total;
//	}

}